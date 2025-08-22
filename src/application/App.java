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
                boolean hasCurrentAccount = hasCurrentAccount(client, accountSet);
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
                boolean hasSaveAccount = hasSaveAccount(client, accountSet);
                if (hasSaveAccount) {
                    System.out.print("\n>>Cliente com conta já cadastrada\n");
                } else {
                    System.out.print("Deposito inicial: R$ ");
                    double initialDeposit = sc.nextDouble();
                    String accountNumber = String.format("%03d", random.nextInt(1000));
                    SaveAccount saveAccount = new SaveAccount(accountNumber, initialDeposit, client);
                    accountSet.add(saveAccount);
                }
            } else if (option == 3) {
                for (Account acc : accountSet) {
                    System.out.println(acc);
                    System.out.println("-".repeat(30));
                }

            } else if (option == 4) {
                Account c = getCurrentAccount(accountSet, client);
                if (c != null) {
                    System.out.print("Informe o valor: R$ ");
                    double v = sc.nextDouble();
                    c.withdraw(v);
                } else {
                    System.out.println(">>Cliente não tem conta corrente cadastrada!!");
                }

            } else if (option == 5) {
                Account c = getCurrentAccount(accountSet, client);
                if (c != null) {
                    System.out.print("Informe o valor: R$ ");
                    double v = sc.nextDouble();
                    c.deposit(v);
                } else {
                    System.out.println(">>Cliente não tem conta corrente cadastrada!!");
                }
            } else if (option == 6) {
                Account c = getSaveAccount(accountSet, client);
                if (c != null) {
                    c.addInterest();
                } else {
                    System.out.println(">>Cliente não tem conta poupança cadastrada!!");
                }
            } else if (option == 7) {
                Account c = getSaveAccount(accountSet, client);
                if (c != null) {
                    System.out.print("Informe o valor: R$ ");
                    double v = sc.nextDouble();
                    c.withdraw(v);
                } else {
                    System.out.println(">>Cliente não tem conta poupança cadastrada!!");
                }
            } else if (option == 8) {
                Account c = getSaveAccount(accountSet, client);
                if (c != null) {
                    System.out.print("Informe o valor: R$ ");
                    double v = sc.nextDouble();
                    c.deposit(v);
                } else {
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

    public static boolean hasCurrentAccount(Client client, Set<Account> accounts) {
        for (Account acc : accounts) {
            if (acc instanceof CurrentAccount && acc.getClient().equals(client)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSaveAccount(Client client, Set<Account> accounts) {
        for (Account acc : accounts) {
            if (acc instanceof SaveAccount && acc.getClient().equals(client)) {
                return true;
            }
        }
        return false;
    }

    public static Account getSaveAccount(Set<Account> accounts, Client client) {
        for (Account acc : accounts) {
            if (acc instanceof SaveAccount && acc.getClient().equals(client)) {
                return acc;
            }
        }
        return null;
    }
    public static Account getCurrentAccount(Set<Account> accounts, Client client) {
        for (Account acc : accounts) {
            if (acc instanceof CurrentAccount && acc.getClient().equals(client)) {
                return acc;
            }
        }
        return null;
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
                + "\n8 - Depositar poupança: "
                + "\n0 - Sair\n");
        option = sc.nextInt();
        return option;
    }
}
