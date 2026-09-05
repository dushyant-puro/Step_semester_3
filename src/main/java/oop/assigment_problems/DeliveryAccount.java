package oop.assignment_problems;

public class DeliveryAccount {
    private final String accountId;
    private double balance;
    private boolean active;

    public DeliveryAccount(String accountId, double openingBalance) {
        if (accountId == null || accountId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid account ID");
        }

        if (openingBalance < 0) {
            throw new IllegalArgumentException("Invalid opening balance");
        }

        this.accountId = accountId;
        this.balance = openingBalance;
        this.active = true;
    }

    public void credit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid credit amount");
        }

        balance += amount;
    }

    public void debit(double amount) {
        if (amount < 0 || amount > balance) {
            throw new IllegalArgumentException("Invalid debit amount");
        }

        balance -= amount;
    }

    public boolean isActive() {
        return active;
    }

    public static void reconcile(DeliveryAccount[] accounts, double[][] transactions) {
        int success = 0;
        int failed = 0;

        for (int i = 0; i < accounts.length; i++) {
            try {
                for (double amount : transactions[i]) {
                    if (amount >= 0) {
                        accounts[i].credit(amount);
                    } else {
                        accounts[i].debit(-amount);
                    }
                }

                success++;
            } catch (Exception e) {
                failed++;
            }
        }

        System.out.println("Successful: " + success + " | Failed: " + failed);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new DeliveryAccount("DA101", 1000),
            new DeliveryAccount("DA102", 500),
            new DeliveryAccount("DA103", 200)
        };

        double[][] transactions = {
            {500, -200},
            {300, -100},
            {100, -500}
        };

        reconcile(accounts, transactions);
    }
}
