package lab03.ex02;

public class Voos {


    private final String codigo;
    private final Aviao aviao;
    private final int executiveCapacity;
    private final int turisticaCapacity;
    private int reserva;

    //construtor---------
    public Voos(String codigo, Aviao aviao) {
        this.codigo = codigo;
        this.aviao = aviao;
        executiveCapacity = aviao.CapacityTotalExecutive();
        turisticaCapacity = aviao.CapacityTotalTuristica();
        reserva = 0;
    }
    //---------

    public int getReserva() {
        return reserva ;
    }
    //-------------------


    //função adicionar reservas — retorna falso se não funcionar
    public boolean reservaAdd(int numLug, Classe classe) {
        int[][] lugares;
        if (classe == Classe.EXECUTIVA) {
            //verificar se tem lugar Suficientes
            if (aviao.CapacityTotalExecutive()- aviao.executiveOccupied() < numLug){
                return false;
            }
            lugares = aviao.getExecutive();
        } else {
            //verificar se tem lugar Suficientes
            if (aviao.CapacityTotalTuristica()- aviao.turisticaOccupied()< numLug){
                return false;
            }
            lugares = aviao.getTuristica();
        }

        int nColunas = lugares[0].length; //nºcolunas do avião
        int nFilas = lugares.length; //nºfilas do avião

        int counter = 0;
        for (int i = 0; i < nColunas; i++) {

            //Para a fila estar vazia, é necessario que a primeira linha esteja vazia
            if (lugares[0][i] == '\0') {
                //colocar a reversa no lugar
                for (int j = 0; j < numLug; j++) {
                    // distribuir os lugares pelas filas
                    if (i + j / nFilas < nColunas) {
                        lugares[j % nFilas][i + j / nFilas] = reserva + 1;
                        counter++;
                    }
                }
                break;
            }
        }
        boolean colocado = (counter == numLug); //  reserva foi feita se counter  for igual ao lugares ocupados
        // se não estiver sido colocado, entao temos de preencher sequencialmente
        if (!colocado) {
            for (int i=0; i<nColunas && !colocado; i++) {
                for (int j=0; j<lugares.length && !colocado; j++) {
                    if (lugares[i][j]== '\0') {
                        lugares[i][j] = reserva + 1;
                        counter++;
                        if (counter == numLug) {
                            colocado = true;
                        }
                    }
                }
            }
        }

        //colocar os dados da reserva no avião
        if (colocado) {
            if (classe==Classe.EXECUTIVA) {
                aviao.setExecutive(lugares);
            }
            else {
                aviao.setTuristica(lugares);
            }
            reserva++;
        }
        return colocado;
    }

    //Função que inprime a informação do voo
    public void printInfo() { // imprimir toda a informação do voo
        System.out.print("Código de voo " + codigo + ". Lugares disponíveis: ");
        if (executiveCapacity>0) {
            System.out.print((executiveCapacity- aviao.executiveOccupied()) + " lugares em classe Executiva; ");
        }
        System.out.print((turisticaCapacity- aviao.turisticaOccupied()) + " lugares em classe Turística.\n");
        if (executiveCapacity==0) {
            System.out.println("Classe executiva não disponível neste voo.");
        }
    }

    //funçao imprime os lugares do voo
    public void printMap() {
        int[][] executive = aviao.getExecutive();
        int[][] turistica = aviao.getTuristica();
        int nFilas = 0;
        int nColunas = 0;
        if(executiveCapacity != 0){
            if (turistica.length >= executive.length) {
                nFilas = turistica.length;
                nColunas=executive[0].length + turistica[0].length;
            }
        }else{
            nFilas = turistica.length;
            nColunas =turistica[0].length;
        }
        //colocar a numeração das colunas
        System.out.print("\t");
        for (int i = 1; i <= nColunas; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();

        int letra = 65; //valor ascii de "A"
        for (int i = 0; i < nFilas; i++) {
            System.out.print((char) letra + "\t");//colocar a letras das filas
            letra++;

                for (int j = 0; j < nColunas; j++) {
                    if(executiveCapacity!=0 && j<executive[0].length ){
                        if (i < executive.length) {
                            if (executive[i][j] != '\0') {
                                System.out.print(executive[i][j] + "\t");
                            } else {
                                System.out.print("0\t");
                            }
                        }else{
                            System.out.print("\t");
                        }
                    }else{
                        int x;
                        if(executiveCapacity!=0){
                            x=j-1;
                        }else{
                            x=j;
                        }
                        if (turistica[i][x- executive.length] != '\0') {
                            System.out.print(turistica[i][x - executive.length]);
                        } else {
                            System.out.print("0");
                        }
                        System.out.print("\t");
                    }
                }
                System.out.println();
            }
    }

    //funçao que da o lugar de cada reserva
    public void refBanco(int numReserva, Classe classe, int numLug){
        int[][] lugares;
        if (classe == Classe.EXECUTIVA) {
            lugares = aviao.getExecutive();
        } else {
            lugares = aviao.getTuristica();
        }
        if (lugares==null) {
            return;
        }
        int x;
        if(executiveCapacity != 0){
            x =aviao.getExecutive()[0].length;
        }else{
            x = 0;
        }
        for (int j = 0; j<lugares[0].length; j++){
            for(int i = 0; i<lugares.length; i++){
                if (lugares[i][j] == numReserva) {
                    System.out.print(" " + (j+1+x) + (char) (65 + i) + " |");
                }
            }
        }
    }

    //função de remover uma reserva
    public Boolean removeReserva(int numReserva){
        boolean found = false;
        if(executiveCapacity != 0){
            int[][] exec = aviao.getExecutive();
            for (int j = 0; j<exec[0].length; j++){
                for(int i = 0; i<exec.length; i++){
                    if (exec[i][j] == numReserva) {
                        exec[i][j] = '\0';
                        found = true;
                    }
                }
            }
            aviao.setExecutive(exec);
        }
        if(!found){
            int[][] tur = aviao.getTuristica();
            for (int j = 0; j<tur[0].length; j++){
                for(int i = 0; i<tur.length; i++){
                    if (tur[i][j] == numReserva) {
                        found = true;
                        tur[i][j] = '\0';
                    }
                }
            }
            aviao.setTuristica(tur);
        }
        return found;
    }
}
