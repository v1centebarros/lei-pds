package lab03.ex02;

public class Aviao {

    private int[][] executive;
    private int[][] turistica;

    //construtor
    public Aviao(int[][] executive, int[][] turistica) {
        this.executive = executive;
        this.turistica = turistica;
    }

    //----funções get e set---------------------------
    public int[][] getExecutive() {
        return executive;
    }
    public int[][] getTuristica() {
        return turistica;
    }
    public void setExecutive(int[][] executive) {
        this.executive = executive;
    }
    public void setTuristica(int[][] turistica) {
        this.turistica = turistica;
    }
    //-------------------------------------------------------

    //--- funções de retorno da capacidade de cada classe--------
    public int CapacityTotalExecutive(){
        return executive.length==0 ? 0 : executive.length*executive[0].length;
    }
    public int CapacityTotalTuristica(){
        return turistica.length*turistica[0].length;
    }
    //----------------------------------------------------------------------

    //---Função de retorno dos número de lugares ocupados----------
    public int turisticaOccupied(){
        int counter = 0;
        for (int[] ints : turistica) {
            for (int j = 0; j < turistica[0].length; j++) {
                if (ints[j] != '\0') counter++;
            }
        }
        return counter;
    }

    public int executiveOccupied(){
        int counter = 0;
        for (int[] ints : executive) {
            for (int j = 0; j < executive[0].length; j++) {
                if (ints[j] != '\0') counter++;
            }
        }
        return counter;
    }
    //----------------------------------------------------------------

}
