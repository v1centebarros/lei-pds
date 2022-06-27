package lab11.ex01;

import lab10.EX02.NullDemo;

public class Telemovel {
    //atributos do telemovel
    private String nome;
    private String processador;
    private Double preco;
    private int ramMemoria;
    private int camera;


    //construtor
    public Telemovel(String nome, String processador, Double preco, int ramMemoria, int camera) {
        this.nome = nome;
        this.processador = processador;
        this.preco = preco;
        this.ramMemoria = ramMemoria;
        this.camera = camera;
    }
    //getters
    public String getNome() {
        return nome;
    }

    public String getProcessador() {
        return processador;
    }

    public Double getPreco() {
        return preco;
    }

    public int getRamMemoria() {
        return ramMemoria;
    }

    public int getCamera() {
        return camera;
    }

    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setRamMemoria(int ramMemoria) {
        this.ramMemoria = ramMemoria;
    }

    public void setCamera(int camera) {
        this.camera = camera;
    }

    public String getAtributo(String atributo){
        switch (atributo) {
            case "camera":
                return String.valueOf(this.getCamera());
            case "nome":
                return this.getNome();
            case "preco":
                return String.valueOf(this.getPreco());
            case "memoria":
                return String.valueOf(this.getRamMemoria());
            case "processador":
                return this.getProcessador();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Telemovel [nome: " + nome + ", preco:" + preco + ", camera: " + camera + ", memoria:" + ramMemoria + ", processador:" + processador + "]";
    }
}
