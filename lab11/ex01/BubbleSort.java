package lab11.ex01;
import java.util.Date;

public class BubbleSort implements SortStrategy{
    @Override
    public void sort(Telemovel[] telemoveis, String atributo) {
        System.out.println("\t\tsorting array using bubble sort strategy");
        long timeStart = new Date().getTime();
        boolean changed = false;
        do {
            changed = false;
            for (int a = 0; a < telemoveis.length - 1; a++) {
                String attr1 = telemoveis[a].getAtributo(atributo);
                String attr2 = telemoveis[a + 1].getAtributo(atributo);
                if (attr1.compareTo(attr2) > 0) {
                    Telemovel tmp = telemoveis[a];
                    telemoveis[a] = telemoveis[a+1];
                    telemoveis[a + 1] = tmp;
                    changed = true;
                }
            }
        } while (changed);

        long timeEnd = new Date().getTime();
        System.out.printf("\t\tProcess time: %d ms\n", (timeEnd-timeStart));
    }
}
