package model;

public class SaveAccount extends Account {
    public SaveAccount(String number, double balance, Client client) {
        super(number, balance, client);
    }

    @Override
    public void addInterest() {
        double value = getBalance() * 0.01;
        deposit(value);
    }

    @Override
    public String toString() {
        return "Conta poupança\n"
                + "-".repeat(15)
                + "\nNome: " + this.getClient().getName()
                + "\nConta: " + this.getNumber()
                + "\nSaldo: R$ " + this.getBalance();
    }
}
