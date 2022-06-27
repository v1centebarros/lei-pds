package lab07.ex02;

import java.util.Scanner;

public class TermFilter extends TextDecorator{
    private Scanner sc;
    public TermFilter(TextReaderInterface t) { super(t); }
    @Override public boolean hasNext() {
        if(sc!=null && sc.hasNext())
            return true;
        return t.hasNext();
    }

    @Override public String next() {
        if(sc==null || sc!=null && !sc.hasNext())
            sc = new Scanner(t.next());
        return sc.next();
    }
}
