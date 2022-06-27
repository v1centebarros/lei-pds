package lab09.ex03;

import java.util.Collection;
import java.util.Stack;

public class RemoveCommand<E> implements Command<E>{
    private Collection<E> collection;
    private Stack<E> actions = new Stack<E>();

    public RemoveCommand(Collection<E> collection){
        this.collection = collection;
    }

    @Override
    public void execute(E elemento) {
        System.out.print("Remover elemento :");
        if(collection.contains(elemento)){
            boolean sucess = collection.remove(elemento); //remover da collection
            if(sucess){
                System.out.println(elemento);
                actions.push(elemento); //remove da stack
            } else {
                System.out.println("erro na remocao do elemento");
            }

        }else{
            System.out.println("Não está contido na colletion");
        }

    }

    @Override
    public void undo() {
        if (!actions.isEmpty()) {
            System.out.println("Undoing...");
            collection.add(actions.pop());
        }else{
            System.out.println("stack vazia");
        }

    }
}
