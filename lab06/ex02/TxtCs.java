package lab06.ex02;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtCs implements ContactsStorageInterface {
    @Override
    public List<Contact> loadContacts() {
        List<Contact> listcont = new ArrayList<>();
        try{
            FileReader file = new FileReader("src/lab06/ex02/contactos_text.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String [] line_partida = line.split("[:;]");
                Contact contact = new Contact(line_partida[0],Integer.parseInt(line_partida[1].strip()));
                listcont.add(contact);
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error! " + e.toString());
            return null;
        }
        System.out.println("Lista de contactos adiciona com Sucesso!");
        return listcont;
    }

    @Override
    public boolean saveContacts(List<Contact> listcontact) {
        try {
            File file = new File("src/lab06/ex02/contacts_txtsave.txt");
            FileWriter printWriter = new FileWriter(file);
            if (listcontact == null) {
                printWriter.close();
                System.out.println("lista de contactos vazia");
                return false;
            }
            for (Contact x : listcontact)
                printWriter.write(x.toString()+"\n");
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error! " + e.toString());
            return false;
        }
        System.out.println("Lista de contactos guarda com Sucesso");
        return true;
    }
}
