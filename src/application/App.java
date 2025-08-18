package application;

import model.Client;
import model.CurrentAccount;
import model.SaveAccount;

public class App {
    public static void main(String[] args) {
        Client client = new Client("Anderson", "123456789-10");
        CurrentAccount currentAccount = new CurrentAccount("111",100.00, client);
        SaveAccount saveAccount = new SaveAccount("222", 200.00, client);

        System.out.println(currentAccount);
        System.out.println();
        System.out.println(saveAccount);
        saveAccount.addInterest();
        System.out.println(saveAccount);
        System.out.println("Sacando R$ 50 reais...");
        currentAccount.withdraw(50.0);
        System.out.println(currentAccount);
    }
}
