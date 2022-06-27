package lab07.ex01;

import java.util.Date;

public class Employee implements InterfaceEmployee{
    private String nome;
    private Date dataStart = null;
    private Date dataTerminante = null;
    Employee(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void start(Date data) {
        if (work(0)){
            System.out.println(this.nome + ", destacado num trabalho!");
        }else{
            this.dataStart = data;
            System.out.println(this.nome + ", começou no dia: " + data.toString() + "!");
        }
    }

    @Override
    public boolean terminate(Date data) {
        if (work(0)){
            this.dataTerminante = data;
            System.out.print(this.nome + ", terminou no dia:" + data.toString() + "!\n");
            this.dataStart = null;
            return true;
        }
        System.out.print(this.nome + " não tem trabalho atribuido!");
        return false;
    }

    @Override
    public boolean work(int num) {
        if (dataStart != null) {
            if (num == 1){
                System.out.println( this.nome + ", continua a trabalhar!");
            }
            return true;
        }
        if (num == 1){
            System.out.println(this.nome + ", não tem nenhum trabalho atribuido!");
        }
        return false;
    }
}
