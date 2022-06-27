package lab11.ex01;

import java.util.List;

public class Revista {
    private final SortStrategy typeSort;
    private Telemovel[] telemoveis;

    public Revista(SortStrategy typeSort, Telemovel[] telemoveis) {
        this.typeSort = typeSort;
        this.telemoveis = telemoveis;
    }

    public void sort(String atributo) {
       typeSort.sort(this.telemoveis, atributo);
    }
}
