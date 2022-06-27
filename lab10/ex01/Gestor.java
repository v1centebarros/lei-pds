package lab10.Ex01;

import java.util.ArrayList;
import java.util.List;

public class Gestor implements Observer {
    private String nome;
    private List<Produto> stock;
    private List<Produto> leilao;
    private List<Produto> vendidos;

    //Construtor
    public Gestor(String nome) {
        this.nome = nome;
        this.stock=new ArrayList<>();
        this.leilao=new ArrayList<>();
        this.vendidos=new ArrayList<>();
    }

    //getters
    public String getNome() {
        return nome;
    }

    public void addProdutoStock(Produto p) {
        stock.add(p);
    }
    public void addProdutoLeilao(Produto p) {
        leilao.add(p);
    }
    public void addProdutoVendidos(Produto p) {
        vendidos.add(p);
    }

    public void removeProdutoStock(Produto p) {
        stock.remove(p);
    }
    public void removeProdutoLeilao(Produto p) {
        leilao.remove(p);
    }

    @Override
    public void update(String notification) {
        System.out.println(String.format("(%s) Recebeu uma notificação: %s", nome, notification));
    }

    public String toString() {
        return String.format(
                "Gestor called %s",
                nome
        );
    }

    public void getStock() {
        System.out.println(stock);
    }

    public void getLeilao() {
        System.out.println(leilao);
    }

    public void  getVendidos() {
        System.out.println(vendidos);
    }
}
