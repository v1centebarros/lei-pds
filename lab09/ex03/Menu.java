package lab09.ex03;

import java.util.Stack;

public class Menu<E> {
    private Command<E> command;
    private Stack<E> actions = new Stack<E>();

    public void setCommand(Command<E> command) {
        this.command = command;
        System.out.println("Option selected: " + command.getClass().getSimpleName());
    }

    public void pressButon(E el) {
        if (command != null)
            command.execute(el);
    }

   public void undo() {
        if (command != null)
            command.undo();
        else System.out.println("erro, escolha um opção");
    }


}
