package lab11.ex01;

import java.util.Date;

public class QuickSort implements SortStrategy{
    @Override
    public void sort(Telemovel[] telemoveis, String atributo) {
        //System.out.println("\t\tsorting array using quick sort strategy");
        long timeStart = new Date().getTime();
        Telemovel pivot = telemoveis[0];

        Telemovel[] less = new Telemovel[telemoveis.length];
        Telemovel[] pivotList = new Telemovel[telemoveis.length];
        Telemovel[] more = new Telemovel[telemoveis.length];

        // Partition
        int less_ind = 0;
        int pivot_ind = 0;
        int more_ind = 0;

        String pivot_attr;
        if (pivot == null)
            return ;
        pivot_attr = pivot.getAtributo(atributo);

        for (Telemovel i: telemoveis) {
            if (i == null)
                return;
            String attr1 = i.getAtributo(atributo);
            if (attr1.compareTo(pivot_attr) < 0)
                less[less_ind++] = i;
            else if (attr1.compareTo(pivot_attr) > 0)
                more[more_ind++] = i;
            else
                pivotList[pivot_ind++] = i;
        }
        // Recursively sort sublists
        this.sort(less, atributo);
        this.sort(more, atributo);

        // Concatenate results
        int index = 0;
        for (Telemovel i: less) {
            if (i != null)
                telemoveis[index++] = i;
        }
        for (Telemovel i: pivotList) {
            if (i != null)
                telemoveis[index++] = i;
        }
        for (Telemovel i: more) {
            if (i != null)
                telemoveis[index++] = i;
        }
        long timeEnd = new Date().getTime();
        System.out.printf("\t\tProcess time: %d ms\n", (timeEnd-timeStart));
    }
}
