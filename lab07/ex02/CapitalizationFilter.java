package lab07.ex02;

import java.util.Locale;
import java.util.Scanner;

public class CapitalizationFilter extends TextDecorator {
    private Scanner sc;
    public CapitalizationFilter(TextReaderInterface t) {
        super(t);
    }

    @Override
    public boolean hasNext() {
        if(sc!=null && sc.hasNext())
            return true;
        return t.hasNext();
    }

    @Override
    public String next() {
        if(sc==null || sc!=null && !sc.hasNext())
            sc = new Scanner(t.next());
        String text = sc.next();
        String output = "";
        if (hasNext()) {
            if (text.length() > 2) {
                output += text.substring(0, 1).toUpperCase() +
                        text.substring(1, text.length() - 1).toLowerCase() +
                        text.substring(text.length() - 1).toUpperCase() + " ";
            } else {
                output += text.toUpperCase() + " ";
            }
            return output;
        }
        return null;
    }
}
