package lab07.ex02;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TextReader implements TextReaderInterface {
    private Scanner sc;

    public TextReader(String filename) {
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.err.println("ERROR! File Not Found!");
            System.exit(1);
        }
    }

    @Override
    public boolean hasNext() {
        return sc.hasNextLine();
    }

    @Override
    public String next() {
        if (hasNext()) {
            return sc.nextLine();
        }
        return null;
    }
}
