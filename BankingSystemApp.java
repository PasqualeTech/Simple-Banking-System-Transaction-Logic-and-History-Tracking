import java.util.*;

class Transaction {
    private String type;
    private double amount;
    private String date;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date().toString();
    }

    @Override
    public String toString() {
        return String.format("%s: €%.2f il %s", type, amount, date);
    }
}

class BankAccount {
    private String accountNumber;
    private double balance;
    private List<Transaction> history = new ArrayList<>();

    public BankAccount(String accountNumber, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
        history.add(new Transaction("Apertura Conto", initialDeposit));
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            history.add(new Transaction("Deposito", amount));
            System.out.println("Deposito eseguito.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            history.add(new Transaction("Prelievo", amount));
            return true;
        }
        return false;
    }

    public void showHistory() {
        System.out.println("\n--- Estratto Conto " + accountNumber + " ---");
        history.forEach(System.out::println);
        System.out.printf("SALDO ATTUALE: €%.2f\n", balance);
    }
}

public class BankingSystemApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Crea Numero Conto: ");
        BankAccount account = new BankAccount(sc.nextLine(), 500.0); // Saldo iniziale demo

        int choice;
        do {
            System.out.println("\n1.Deposito 2.Prelievo 3.Storico 4.Esci");
            System.out.print("> ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Importo: ");
                    account.deposit(sc.nextDouble());
                }
                case 2 -> {
                    System.out.print("Importo: ");
                    if (!account.withdraw(sc.nextDouble())) System.out.println("Saldo insufficiente!");
                    else System.out.println("Prelievo completato.");
                }
                case 3 -> account.showHistory();
            }
        } while (choice != 4);
        System.out.println("Arrivederci.");
    }
}
