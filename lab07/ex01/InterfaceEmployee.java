package lab07.ex01;

import java.util.Date;

public interface InterfaceEmployee {
    public void start (Date data );
    public boolean terminate (Date data);
    public boolean work(int num);

    String getNome();
}
