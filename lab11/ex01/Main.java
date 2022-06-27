package lab11.ex01;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Telemovel t1 = new Telemovel("Samsung", " Exynos 990 da Samsung (7 nm)", 349.99, 128, 16);
        Telemovel t2 = new Telemovel("huawei", "Kirin 9000 da Huawei (5 nm)", 500.99, 256, 16);
        Telemovel t3 = new Telemovel("iphone", "Apple A14 Bionic (5 nm)", 899.99, 256, 24);
        Telemovel t4 = new Telemovel("Asus", "Qualcomm Snapdragon™ 680", 320.99, 256, 32);
        Telemovel t5 = new Telemovel("nokia", " Snapdragon 865 da Qualcomm (7 nm)", 150.79, 56, 8);
        Telemovel t6 = new Telemovel("huawei 2.0", "Snapdragon 888 da Qualcomm (5 nm)", 850.76, 128, 32);


        Telemovel[] array0 = {t1, t2, t3};
        Telemovel[] array1 = {t4, t5, t6};
        Telemovel[] array2 = {t1, t3, t5};
        Telemovel[] array3 = {t2, t4, t6};

        Revista r1= new Revista(new BubbleSort(), array0);
        Revista r2= new Revista(new InsertionSort(), array1);
        Revista r4= new Revista(new QuickSort(), array3);

        System.out.println("----------BubbleSort-----------------------------------------");
        System.out.println("\t Nome: ");
        r1.sort("nome");
        for (Telemovel tel: array0)
            System.out.println("\t\t\t" + tel);
        System.out.println("\t Preco: ");
        r1.sort("preco");
        for (Telemovel tel: array0)
            System.out.println("\t\t\t" + tel);
        System.out.println("\t Processador: ");
        r1.sort("processador");
        for (Telemovel tel: array0)
            System.out.println("\t\t\t" + tel);
        System.out.println("\t Memoria: ");
        r1.sort("memoria");
        for (Telemovel tel: array0)
            System.out.println("\t\t\t" + tel);
        System.out.println("----------------------------------------------------------");

        System.out.println("----------InsertionSort-----------------------------------------");
        System.out.println("\t Nome: ");
        r2.sort("nome");
        for (Telemovel tel: array1)
            System.out.println("\t\t\t" + tel);
        System.out.println("\t Preco: ");
        r2.sort("preco");
        for (Telemovel tel: array1)
            System.out.println("\t\t\t" + tel);
        System.out.println("\t Processador: ");
        r2.sort("processador");
        for (Telemovel tel: array1)
            System.out.println("\t\t\t" + tel);
        System.out.println("\t Memoria: ");
        r2.sort("memoria");
        for (Telemovel tel: array1)
            System.out.println("\t\t\t" + tel);
        System.out.println("----------------------------------------------------------");

        System.out.println("----------QuickSort-----------------------------------------");
        System.out.println("\t Nome: ");
        r4.sort("nome");
        System.out.println("\t\tsorting array using quick sort strategy");
        for (Telemovel tel: array3)
            System.out.println("\t\t\t" + tel);
        System.out.println("\t Preco: ");
        r4.sort("preco");
        System.out.println("\t\tsorting array using quick sort strategy");
        for (Telemovel tel: array3)
            System.out.println("\t\t\t" + tel);
        System.out.println("\t Processador: ");
        r4.sort("processador");
        System.out.println("\t\tsorting array using quick sort strategy");
        for (Telemovel tel: array3)
            System.out.println("\t\t\t" + tel);
        System.out.println("\t Memoria: ");
        r4.sort("memoria");
        System.out.println("\t\tsorting array using quick sort strategy");
        for (Telemovel tel: array3)
            System.out.println("\t\t\t" + tel);
        System.out.println("----------------------------------------------------------");
    }
}
