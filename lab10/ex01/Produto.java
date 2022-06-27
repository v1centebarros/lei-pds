package lab10.Ex01;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Produto {
    private static int contador = 1 ;
    private int  codigo;
    private String descricao;
    private double precoBase;
    private Gestor gestor;

    //estado
    private Estado estado;
    private double tempoLeilao; //em segundos
    private long inicioLeilao; //inicio do leilao
    private double novoPreco; //alterado sempre que é feita nova solicitacao

    private HashMap<Cliente , Double> subscribe;  //key = cliente, value = licitação feita pelo produto

    public Produto(String descricao, double precoBase, Gestor gestor) {
        this.descricao = descricao;
        this.precoBase = precoBase;
        this.estado = Estado.stock;
        this.tempoLeilao= 0;
        this.subscribe = new HashMap<>();
        this.codigo=contador++;
        this.novoPreco = precoBase;
        this.gestor= gestor;
        gestor.addProdutoStock(this);
    }


    public void ComecoLeilao(double tempoLeilao){
        this.estado=estado.leilao;
        //System.out.printf("(%s) Novo estado: %s.\n", descricao,this.estado);
        this.tempoLeilao = tempoLeilao;
        this.inicioLeilao = System.currentTimeMillis();
        gestor.addProdutoLeilao(this);
        gestor.removeProdutoStock(this);
        gestor.update(String.format("O produto %s entrou no leilao",descricao));
    }

    //getters
    public double getPrecoBase() {
        return precoBase;
    }

    public double getNovoPreco() {
        return novoPreco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setNovoPreco(double novoPreco) {
        this.novoPreco = novoPreco;
    }
    public boolean verificaLeilaoTerminou(){
        long tempoCorrente =  System.currentTimeMillis();
        double timeLicitacao = (tempoCorrente-inicioLeilao);
        if (timeLicitacao > tempoLeilao) {
            if(this.estado == estado.leilao) {
                terminarLeilao();
                return true;
            }
            return true;
        }
        return false;
    }

    public int ofertaLicitacao(Cliente cliente, double preco) {
        long tempoCorrente =  System.currentTimeMillis();
        double timeLicitacao = (tempoCorrente-inicioLeilao);
        if(this.estado == estado.leilao){
            if(timeLicitacao>tempoLeilao){
                terminarLeilao();
                return 1;
            }else{
                if(preco>novoPreco){
                    subscribe.put(cliente,preco);
                    setNovoPreco(preco);
                    gestor.update(String.format("Foi feita uma nova oferta de %.2f€ no produto %s pelo cliente %s", preco, descricao,cliente.getNome()));
                    for( Map.Entry<Cliente, Double> x : subscribe.entrySet()){
                        x.getKey().update(String.format("Foi feita uma nova oferta de %.2f€ no produto %s", preco, descricao));
                    }
                    return 2;
                }else{
                    gestor.update(String.format("Foi feita uma nova oferta de %.2f€ no produto %s pelo cliente %s, mas não aceite.", preco, descricao,cliente.getNome()));
                    cliente.update(String.format("ALERTA: solicitação baixa para produto %s, seu valor está em %.2f€", descricao,novoPreco));
                    return 1;
                }
            }
        }
        return 0;
    }

    public void terminarLeilao(){
        if(subscribe.size()==0){
            estado=estado.stock;
            //System.out.printf("(%s) Novo estado: %s.\n", descricao,this.estado);
            gestor.addProdutoStock(this);
            gestor.removeProdutoLeilao(this);
            gestor.update(String.format("O produto %s não foi vendido no leilao, volta ao seu stock",descricao));

        }else{
            if(novoPreco>precoBase){
                estado=estado.vendido;
                gestor.removeProdutoLeilao(this);
                gestor.addProdutoVendidos(this);
                gestor.update(String.format("Foi Vendido o produto %s.", descricao));
            }
            estado=estado.stock;
            gestor.addProdutoStock(this);
            gestor.removeProdutoLeilao(this);
            gestor.update(String.format("O produto %s não foi vendido no leilao, volta ao seu stock",descricao));
            //System.out.printf("(%s) Novo estado: %s.\n", descricao,this.estado);
        }
        for(Map.Entry<Cliente, Double> x : subscribe.entrySet()){
            subscribe.put(x.getKey(),x.getValue());
            System.out.println("removido");
        }
    }


    public String toString() {
        return String.format(
                "Produto designado %s, com preçoBase %.2f€, gerido por %s, no estado %s, com uma licitação maxima de %.2f€ e com %d clientes interessados.\n",
                descricao,
                getPrecoBase(),
                gestor.toString(),
                getEstado(),
                getNovoPreco(),
                subscribe.size());
    }


}
