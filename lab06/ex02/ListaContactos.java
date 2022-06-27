package lab06.ex02;

import java.util.ArrayList;
import java.util.List;

public class ListaContactos implements ContactsInterface {
    private ContactsStorageInterface contacts;
    private List<Contact> listcont;


    public ListaContactos(ContactsStorageInterface store) {
        this.contacts = store;
        listcont = new ArrayList<Contact>();
    }

    public void openAndLoad(ContactsStorageInterface store) {
        this.contacts = store;
        listcont = store.loadContacts();
    }

    public void saveAndClose() {
        this.contacts.saveContacts(listcont);
    }

    public void saveAndClose(ContactsStorageInterface store) {
        store.saveContacts(listcont);
    }

    public boolean exist(Contact contact) {
        for(Contact x: listcont) {
            if (x.getName().equals(contact.getName())&& x.getNumber() == contact.getNumber())
                return true;
        }
        return false;
    }

    public Contact getByName(String nome) {
        for(Contact x: listcont) {
            if (x.getName().equals(nome))
                return x;
        }
        return null;
    }

    public boolean add(Contact contact) {
        if(exist(contact)){
            System.out.println("O Contacto já existe!");
            return false;
        }else{
            listcont.add(contact);
            System.out.println("O Contacto Adicionado com Sucesso!");
            return true;
        }
    }

    public boolean remove(Contact contact) {
        for (Contact cont : listcont) {
            if (cont.getName().equals(contact.getName()) && cont.getNumber() == contact.getNumber()) {
                listcont.remove(cont);
                System.out.println("Removido com Sucesso!");
                return true;
            }
        }
        return false;
    }
    public void listagem() {
        System.out.println("----------------------");
        if (listcont == null) {
            System.out.println("Lista de contactos vazia");
            return;
        }
        for (Contact cont : listcont) {
            System.out.println(cont.getName() + ": " + cont.getNumber()+ ";");
        }
        System.out.println("----------------------");
    }
}
