package lab09.ex03;

import java.util.ArrayList;
import java.util.Collection;

public class Client{
    public static void main(String[] args) {
        System.out.println("----------Usar um ARRAY LIST de Inteiros-----------");
        Collection<Integer> ourList = new ArrayList<Integer>();
        Command<Integer> addElementos = new AddCommand<Integer>(ourList);
        Command<Integer> removeElementos = new RemoveCommand<Integer>(ourList);

        int num1 = 10, num2 = 3, num3 = 0, num4 = 40;

        Menu<Integer> client = new Menu<Integer>();
        client.undo(); // erro, escolha um opção
        client.setCommand(addElementos);
        client.pressButon(num3); // 0
        client.pressButon(num1); // 10
        client.pressButon(num2); // 3
        addElementos.execute(num4); // 40
        System.out.println(ourList); //[0,10,3,40]
        addElementos.undo();//40
        addElementos.undo(); //3
        addElementos.undo();//10
        client.undo(); //0
        client.undo(); //stack vazia
        System.out.println(ourList); //[0,10,3,40,40,3,10,0]

        System.out.println();
        client.setCommand(removeElementos);
        client.pressButon(num3); //0
        client.pressButon(num3); //0
        client.pressButon(num4); //40
        client.pressButon(num1); //10
        client.pressButon(num3); //nao esta contido na colletion
        removeElementos.execute(num1); //10
        System.out.println(ourList); //[3,40,3]

        removeElementos.undo(); //10
        removeElementos.undo(); // 10
        removeElementos.undo(); // 40
        removeElementos.undo(); //0
        removeElementos.undo(); //0
        removeElementos.undo(); //stack vazia
        System.out.println(ourList); //[3,40,3,10,10,40,0,0]




    }
}
