package lab06.ex02;

public class Contact {
    private int number;
    private String name;
    //construtor
    public Contact(String name, int number){
        this.name = name;
        this.number = number;
    }
    //metodos getters e setters
    public void setNumber(int number){
        this.number = number;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getNumber(){
        return this.number;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return this.name + ": " + this.number+ ";";
    }

}
