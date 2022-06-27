package lab09.ex01;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        System.out.println("IX.1 | Iterator implementation");
        VectorGeneric<Integer> vc = new VectorGeneric<>();

        System.out.println("Adicionar elementos...........");
        for (Integer m : Arrays.asList(10, 20, 30, 40, 50)) {
            vc.addElem(m);
        }

        Iterator<Integer> iterator = vc.iterator();

        // While cycle that while hasNext() prints
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Creating two other iterators for same VectoGeneric....
        Iterator<Integer> iterator2 = vc.iterator();
        Iterator<Integer> iterator3 = vc.iterator();

        System.out.println("\nIterating hover them, visiting two elements of one foreach one of the other...");
        while (iterator2.hasNext() || iterator3.hasNext()) {
            if (iterator2.hasNext())
                System.out.println(String.format("%10s %s", "it2", iterator2.next()));
            if (iterator2.hasNext())
                System.out.println(String.format("%10s %s", "it2", iterator2.next()));
            if (iterator3.hasNext())
                System.out.println(String.format("%10s %s", "it3", iterator3.next()));
        }

        System.out.println("\n");
        ListIterator<Integer> lt = vc.listIterator();

        while (lt.hasNext()) {
            System.out.print(String.format("%15s %d |", "nextIndex", lt.nextIndex()));
            System.out.print(String.format("%20s %d |", "previousIndex", lt.previousIndex()));
            System.out.println(String.format("%10s %s", "next", lt.next()));
        }
        System.out.println();
        System.out.println(String.format("%15s %d", "nextIndex", lt.nextIndex()));
        System.out.println(String.format("%15s %d", "previousIndex", lt.previousIndex()));

        System.out.println("\nCreating one ListIterator starting at index 5 (50)...");
        ListIterator<Integer> lt2 = vc.listIterator(3);

        System.out.println("\nWhile cycle that while hasNext() prints");
        while (lt2.hasNext()) {
            System.out.print(String.format("%15s %d|", "nextIndex", lt2.nextIndex()));
            System.out.print(String.format("%15s %d|", "previousIndex", lt2.previousIndex()));
            System.out.println(String.format("%10s %s|", "next", lt2.next()));
        }
        System.out.println();
        System.out.println(String.format("%15s %d", "nextIndex", lt2.nextIndex()));
        System.out.println(String.format("%15s %d", "previousIndex", lt2.previousIndex()));

        System.out.println("\nIterating it on reverse...");
        while (lt2.hasPrevious()) {
            System.out.print(String.format("%15s %d|", "nextIndex", lt2.nextIndex()));
            System.out.print(String.format("%20s %d|", "previousIndex", lt2.previousIndex()));
            System.out.println(String.format("%10s %s", "next", lt2.previous()));
        }
        System.out.println();
        System.out.println(String.format("%15s %d", "nextIndex", lt2.nextIndex()));
        System.out.println(String.format("%15s %d", "previousIndex", lt2.previousIndex()));
    }
}
