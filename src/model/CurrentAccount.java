package model;

public class CurrentAccount extends Account{

    public CurrentAccount(String number, double balance, Client client) {
        super(number, balance, client);
    }

    @Override
    public void addInterest() {
        System.out.println("Current account doesn't add interest!!");
    }

    @Override
    public String toString() {
        return "Current Account\n"
                +"-".repeat(15)
                +"\nNome: " + this.getClient().getName()
                +"\nConta: " + this.getNumber()
                +"\nSaldo: R$ " + this.getBalance();
    }
}
