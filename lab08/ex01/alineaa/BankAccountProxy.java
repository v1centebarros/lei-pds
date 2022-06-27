package lab08.ex01.alineaa;

class BankAccountProxy implements BankAccount {
    private BankAccountImpl bankAccount;

    BankAccountProxy(BankAccountImpl bk){
        this.bankAccount = bk;
    }
    /*public BankAccountProxy(String bank, double initialDeposit) {

        this.bankAccount = new BankAccountImpl(bank, initialDeposit);

    }*/
    public String getBank(){
        return this.bankAccount.getBank();
    }
    @Override
    public void deposit(double amount) {
        this.bankAccount.deposit(amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if (this.IsUser()){
            return this.bankAccount.withdraw(amount);
        } else {
            System.out.println("Balance operation not allowed");
            return false;
        }
    }

    @Override
    public double balance() {
        if (this.IsUser()) {
            return this.bankAccount.balance();
        } else {
            return Double.NaN;
        }
    }

    private boolean IsUser(){
        return (Company.user == User.OWNER);
    }
}
