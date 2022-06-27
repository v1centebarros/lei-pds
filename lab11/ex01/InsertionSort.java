package lab11.ex01;

import java.util.Date;

public class InsertionSort implements SortStrategy{
    @Override
    public void sort(Telemovel[] telemoveis, String atributo) {
        System.out.println("\t\tsorting array using insertion sort strategy");
        long timeStart = new Date().getTime();
        int n = telemoveis.length;
        for (int j = 1; j < n; j++) {
            Telemovel key = telemoveis[j];
            int i = j-1;
            while ( (i > -1) && (telemoveis[i].getAtributo(atributo).compareTo(key.getAtributo(atributo)) > 0)) {
                telemoveis[i+1] = telemoveis[i];
                i--;
            }
            telemoveis[i+1] = key;
        }

        long timeEnd = new Date().getTime();
        System.out.printf("\t\tProcess time: %d ms\n", (timeEnd-timeStart));
    }
}
