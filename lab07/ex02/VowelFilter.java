package lab07.ex02;

import java.util.Scanner;

public class VowelFilter extends TextDecorator{
    public VowelFilter(TextReaderInterface t) { super(t); }

    private Scanner sc;

    @Override public boolean hasNext() {
        if(sc!=null && sc.hasNext())
            return true;
        return t.hasNext();
    }

    @Override public String next() {
        if(sc==null || sc!=null && !sc.hasNext())
            sc = new Scanner(t.next());
        return this.hasNext() ? sc.next().replaceAll("[AEIOUaeiou]", "") : null;
    }


}
