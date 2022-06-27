package lab09.ex02;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList <String> foodList = new ArrayList<>();
        foodList.add("veggie burger");
        foodList.add("Pasta Carbonara");
        foodList.add("PLAIN pizza, no toppings!");
        foodList.add("sushi nigiri and sashimi");
        foodList.add("salad with tuna");
        foodList.add("strawberry ice cream and waffles dessert");

        Chef foodChef = new SushiChef().setSuccessor(
                new PastaChef().setSuccessor(
                        new BurgerChef().setSuccessor(
                                new PizzaChef().setSuccessor(
                                        new DessertChef()))));
        // sushi, pasta, burger, pizza, dessert

        for (String request : foodList) {
            System.out.println("Can I please get a " + request + "?");
            foodChef.parse(request);
        }
    }

}