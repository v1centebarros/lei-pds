package lab06.ex02;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------TESTE TXT-------");

       ContactsStorageInterface storageTxt = new TxtCs();
       ListaContactos listaContactos = new ListaContactos(storageTxt);

       listaContactos.openAndLoad(storageTxt);
       listaContactos.listagem();
       Contact contact1= new Contact("Joao", 15175451);
       Contact contact2= new Contact("Maria", 15568194);
       listaContactos.add(contact1);
       listaContactos.add(new Contact("Edgar Dias",911518));
       System.out.println("Contacto: "+ listaContactos.getByName("Edgar Dias"));
       System.out.println("Contacto: "+ listaContactos.getByName("Maria"));
       System.out.println("Maria existe na lista de contactos? " + listaContactos.exist(contact2));
       listaContactos.listagem();
       listaContactos.remove(listaContactos.getByName("Filipe"));
       listaContactos.listagem();
       listaContactos.saveAndClose(storageTxt);
    System.out.println("-------------------------------\n");

       System.out.println("--------TESTE Binary -------");
       ContactsStorageInterface storageBin = new BinCs();
       ListaContactos listaContactos2 = new ListaContactos(storageBin);
       listaContactos2.openAndLoad(storageBin);
       listaContactos2.listagem();
       System.out.println("Contacto: "+ listaContactos2.getByName("Edgar Dias"));
       System.out.println("Contacto: "+ listaContactos2.getByName("Maria"));
       listaContactos2.add(contact2);
       System.out.println("Maria existe na lista de contactos? " + listaContactos2.exist(contact2));
       listaContactos2.add(new Contact("Marcio", 155546246));
       System.out.println("Contacto: "+ listaContactos2.getByName("Marcio"));
       listaContactos2.saveAndClose(storageBin);
       listaContactos2.listagem();
    }
}
