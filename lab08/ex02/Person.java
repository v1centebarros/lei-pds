package lab08.ex02;


class Person {
     private String name;
     private BankAccount bankAccount;

    public Person(String n) {
         name = n;
         bankAccount = new BankAccountProxy(new BankAccountImpl("PeDeMeia", 0));
         //bankAccount = new BankAccountProxy("PeDeMeia", 0);
     }

     public String getName() {
         return name;
     }

     public BankAccount getBankAccount() {
         return bankAccount;
     }


}
