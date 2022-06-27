package lab07.ex03;

import java.util.ArrayList;

public class Caixa extends Produto {
    private ArrayList<Produto> elementos = new ArrayList<>();
    private double totalWeight;

    public Caixa(String nome, double weight) {
        super(nome, weight);
    }

    public void add(Produto produto){
        elementos.add(produto);
    }

    public double getTotalWeight() {
        totalWeight = this.getWeight();
        for(Produto produto : elementos){
            if(produto instanceof Caixa){
                Caixa caixa2 = (Caixa) produto;
                totalWeight+= caixa2.getTotalWeight();
            }else{
                totalWeight+= produto.getWeight();
            }
        }
        return totalWeight;
    }

    @Override
    public String toString() {
        return "*Caixa '" + getNome() +
                "' [ Weight: " + getWeight() +
                " ; Total: " + getTotalWeight()+ "]" ;
    }

    @Override
    public void draw() {
        System.out.println(indent.toString() + toString());
        indent.append("     "); //colocar identação, dentro da caixa
        for (Produto produto: elementos){
            produto.draw();
        }
        indent.deleteCharAt(indent.length()-2); //reverter a identação
    }
}
