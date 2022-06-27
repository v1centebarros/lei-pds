package lab08.ex02;

import java.util.HashMap;

public class Insurance {
    private HashMap<Integer, Person> registo;
    private String nome;
    private static int count = 0;
    public Insurance(String nome) {
        this.nome = nome;
        this.registo = new HashMap<>();
    }

    public void register(Person person) {
        System.out.println("Employee registered to the Company's Insurance");
        this.registo.put(++Insurance.count, person);
    }

    public HashMap<Integer, Person> getRegisto() {
        return registo;
    }
}
