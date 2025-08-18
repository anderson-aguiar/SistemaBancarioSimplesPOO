package model;

public abstract class Account implements BankOperations{
    private String number;
    private double balance;
    private Client client;


    public Account(String number, double balance, Client client) {
        this.number = number;
        this.balance = balance;
        this.client = client;
    }

    //metodo abstrato para add juros
    public abstract void addInterest();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void deposit(double value) {
        if (value > 0.0) {
            this.balance += value;
        } else {
            System.out.println("Must be greater than O");
        }
    }

    public void withdraw(double value) {
        if (value <= 0.0) {
            System.out.println("Must be greater than 0");
        } else if (value > this.balance) {
            System.out.println("Balance isn't enough");
        } else {
            this.balance -= value;
        }
    }

}
