package lab10.Ex01;

import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        Gestor gestor1 = new Gestor("XAVIER");
        Gestor gestor2 = new Gestor("MANUEL");
        Cliente cliente1= new Cliente("ANA");
        Cliente cliente2 = new Cliente("JOANA");
        Cliente cliente3 =new Cliente("MARTIN");
        Cliente clientes[]={cliente1,cliente2,cliente3};


        Produto p1 = new Produto("COLAR", 2.5,gestor1);
        Produto p2 = new Produto("ANEL", 2.5,gestor1);
        Produto p3 = new Produto("COROA", 70,gestor1);
        Produto p4 = new Produto("QUADRO", 25,gestor1);
        Produto p5 = new Produto("CUBO", 3.6,gestor1);

        Produto p6 = new Produto("CHINELOS", 3.0,gestor2);
        Produto p7 = new Produto("PULSEIRA", 1.8,gestor2);
        Produto p8 = new Produto("CADEIRA DE GAMER", 70,gestor2);
        Produto p9 = new Produto("MALA", 25,gestor2);
        Produto p10 = new Produto("LAPISGIGANTE", 5,gestor2);

        Produto produtosleilao[]={p1,p3,p4,p9,p10};

        System.out.println("-----------COMEÇAR LEILAO------------------------------------------------");
        for(Produto p : produtosleilao){
            p.ComecoLeilao(4);
            for (Cliente c: clientes){
                c.addProdutoLeilao(p);
            }
        }
        System.out.println("-------------------------------------------------------------------------");

        System.out.println("----------------Consulta de INFORMAÇÃO COMO GESTOR---------------------------");
        gestor2.getLeilao();
        gestor1.getStock();
        System.out.println("-----------------------------------------------------------------------");

        System.out.println("----------------Consulta de INFORMAÇÃO COMO Cliente---------------------------");
            cliente2.consulta();
        System.out.println("-----------------------------------------------------------------------");

        System.out.println("----------------Licitações---------------------------");
        cliente2.licitar(p6,6); //nao esta em leilao
        cliente3.licitar(p1,3);
        cliente1.licitar(p1,2.6); //solicitação nao aceite, mais baixa do que esta
        cliente1.licitar(p9,2.6); //solicitação nao aceite, mais baixa do que seu valor
        cliente2.licitar(p4, 30);
        System.out.println("-----------------------------------------------------------------------");


        System.out.println("---------------Verificação se o leilao terminou---------------------------");
        //so na verificação é que é possivel remover os produtos que nao tinham sido removidos da lista de leilao, do cliente, do gestor
        for(Produto p : produtosleilao){
            if(p.verificaLeilaoTerminou()){
                for (Cliente c: clientes){
                    c.removeProdutoLeilao(p);
                }
            }
        }
        System.out.println("-----------------------------------------------------------------------");
        gestor1.getLeilao();
        gestor2.getLeilao();
        cliente1.consulta();
    }
}
