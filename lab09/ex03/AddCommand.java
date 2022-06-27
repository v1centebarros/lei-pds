package lab09.ex03;

import java.util.Collection;
import java.util.Stack;

public class AddCommand<E> implements Command<E>{
    private Collection<E> collection;
    private Stack<E> actions = new Stack<E>();

    AddCommand(Collection<E> collection){
        this.collection = collection;
    }

    @Override
    public void execute(E elemento) {

        boolean sucess = collection.add(elemento); //remover da collection
        if(sucess){
            System.out.println("Adicionar elemento: "+ elemento);
            actions.push(elemento); //remove da stack
        }else{
            System.out.println("Erro ao adicionar elemento");
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
