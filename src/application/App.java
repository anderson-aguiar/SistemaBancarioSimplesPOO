package application;

import model.Account;
import model.Client;
import model.CurrentAccount;
import model.SaveAccount;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
        Set<Account> accountSet = new HashSet<>();
        System.out.println("--- Informe seu dados ---");
        System.out.print("Nome: ");
        String name = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.next();
        sc.nextLine();
        while (!isCpfvalid(cpf)) {
            System.out.println("Informe um CPF no padrão xxx.xxx.xxx-xx");
            System.out.print("CPF: ");
            cpf = sc.next();
            sc.nextLine();
        }

        Client client = new Client(name, cpf);
        int option = -1;
        while (option != 0) {
            option = menu();
            if (option == 1) {
                boolean hasCurrentAccount = false;
                //Verifica se o cliente já tem conta corrente
                for (Account acc : accountSet) {
                    if (acc instanceof CurrentAccount && acc.getClient().equals(client)) {
                        hasCurrentAccount = true;
                        break;
                    }
                }
                if (hasCurrentAccount) {
                    System.out.print("\n>>Cliente com conta já cadastrada");
                } else {
                    System.out.print("Deposito inicial: R$ ");
                    double initialDeposit = sc.nextDouble();
                    int n = random.nextInt(999);
                    String accountNumber = String.valueOf(n);
                    CurrentAccount currentAccount =
                            new CurrentAccount(accountNumber, initialDeposit, client);
                    accountSet.add(currentAccount);
                }

            } else if (option == 2) {
                boolean hasSaveAccount = false;
                //Verifica se o cliente já tem conta poupança
                for (Account acc : accountSet) {
                    if (acc instanceof SaveAccount && acc.getClient().equals(client)) {
                        hasSaveAccount = true;
                        break;
                    }
                }
                if (hasSaveAccount) {
                    System.out.print("\n>>Cliente com conta já cadastrada\n");
                } else {
                    System.out.print("Deposito inicial: R$ ");
                    double initialDeposit = sc.nextDouble();
                    int n = random.nextInt(999);
                    String accountNumber = String.valueOf(n);
                    SaveAccount saveAccount = new SaveAccount(accountNumber, initialDeposit, client);
                    accountSet.add(saveAccount);
                }
            } else if (option == 3) {
                for (Account acc : accountSet) {
                    System.out.println(acc);
                    System.out.println("-".repeat(30));
                }

            } else if (option == 4) {
                boolean found = false;
                for (Account acc : accountSet) {
                    if (acc instanceof CurrentAccount && acc.getClient().equals(client)) {
                        System.out.print("Informe o valor: R$ ");
                        double v = sc.nextDouble();
                        acc.withdraw(v);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println(">>Cliente não tem conta corrente cadastrada!!");
                }
            } else if (option == 5) {
                boolean found = false;
                for (Account acc : accountSet) {
                    if (acc instanceof CurrentAccount && acc.getClient().equals(client)) {
                        System.out.print("Informe o valor: R$ ");
                        double v = sc.nextDouble();
                        acc.deposit(v);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println(">>Cliente não tem conta corrente cadastrada!!");
                }
            } else if (option == 6) {
                boolean found = false;
                for (Account acc : accountSet) {
                    if (acc instanceof SaveAccount && acc.getClient().equals(client)) {
                        acc.addInterest();
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println(">>Cliente não tem conta poupança cadastrada!!");
                }
            } else if (option == 7) {
                boolean found = false;
                for (Account acc : accountSet) {
                    if (acc instanceof SaveAccount && acc.getClient().equals(client)) {
                        System.out.print("Informe o valor: R$ ");
                        double v = sc.nextDouble();
                        acc.withdraw(v);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println(">>Cliente não tem conta poupança cadastrada!!");
                }
            } else if (option == 0) {
                System.out.println("---> Obrigado <---");
            } else {
                System.out.println("Opção inválida, tente novamente: ");
            }

        }
    }

    public static boolean isCpfvalid(String cpf) {
        //format XXX.XXX.XXX-XX
        String regex = "^(\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$";
        return cpf != null && cpf.matches(regex);
    }

    public static int menu() {
        int option;
        System.out.print("Escolha uma opção: ");

        System.out.print("\n1 - Criar conta corrente: "
                + "\n2 - Criar conta poupança: "
                + "\n3 - Verificar minhas contas: "
                + "\n4 - Sacar conta corrente: "
                + "\n5 - Depositar conta corrente: "
                + "\n6 - Adicionar juros conta poupança: "
                + "\n7 - Sacar conta poupança: "
                + "\n0 - Sair\n");
        option = sc.nextInt();
        return option;
    }
}
