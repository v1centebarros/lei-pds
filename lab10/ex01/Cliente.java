package lab10.Ex01;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements Observer{
    private String nome;
    private List<Produto>produtoslicitados; //lista com os produtos que o cliente licitou
    private List<Produto>leilao;
    //Construtor
    public Cliente(String nome) {
        this.nome = nome;
        this.produtoslicitados = new ArrayList<>();
        this.leilao=new ArrayList<>();
    }

    //getters
    public String getNome() {
        return nome;
    }

    public List<Produto> getProdutoslicitados() {
        return produtoslicitados;
    }

    public int licitar(Produto produto, double preco){
        int x=produto.ofertaLicitacao(this,preco);
        if(x== 2){
            produtoslicitados.add(produto);
            return 0;
        }else if(x == 0){
            System.out.println(String.format("(%s) ERRO: O produto, %s ,não está em leilão!", nome, produto.getDescricao()));
            return 0;
        }
        return 1;
    }

    public void consulta(){
        System.out.println("Lista de produtos em Leilão:");
        System.out.println(getLeilao());
    }

    public String toString() {
        return String.format(
                "Cliente called %s",
                nome
        );
    }

    @Override
    public void update(String notification) {
        System.out.println(String.format("(%s) Recebeu uma notificação: %s", nome, notification));
    }

    public void removeProdutoLeilao(Produto p) {
        leilao.remove(p);
    }
    public void addProdutoLeilao(Produto p) {
        leilao.add(p);
    }

    public List<Produto> getLeilao() {
        return leilao;
    }
}
