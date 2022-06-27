package lab06.ex02;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinCs implements ContactsStorageInterface {

    @Override
    public List<Contact> loadContacts(){
        
        List<Contact> listcont = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        try {
            FileInputStream scfile = new FileInputStream(new File("src/lab06/ex02/contactos_bin.bin"));

            int ch;
            while ((ch = scfile.read()) != -1) {
                str.append((char) ch);
            }

            scfile.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Scanner sc = new Scanner(str.toString());
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String [] line_partida = line.split("[:;]");
            Contact contact = new Contact(line_partida[0],Integer.parseInt(line_partida[1].strip()));
            listcont.add(contact);
        }
        return listcont;
    }



    @Override
    public boolean saveContacts(List<Contact> listcont) {
        
        File file = new File("src/lab06/ex02/contactos_bin2.bin");
        if(listcont == null){
            System.out.println("Lista de contactos vazia!");
            return false;
        }

        StringBuilder content = new StringBuilder();
        for(Contact c : listcont){
            content.append(String.format("%s:%d;\n", c.getName(), c.getNumber()));
        }

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
