package lab09.ex03;

public interface Command<E> {
    void execute(E e);
    void undo();
}
