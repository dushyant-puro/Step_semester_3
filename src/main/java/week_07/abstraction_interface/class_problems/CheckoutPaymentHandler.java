package week_07.abstraction_interface.class_problems;

abstract class PaymentMethod {
    private static int nextId = 1000;
    private final String transactionId;

    protected PaymentMethod() {
        transactionId = "TXN-" + (++nextId);
    }

    public abstract String processPayment(double amount);

    public String processPayment(double amount, String note) {
        return processPayment(amount) + " (" + note + ")";
    }

    public String getTransactionId() { return transactionId; }
}

class CreditCardPayment extends PaymentMethod {
    private final String cardNumberLastFour;

    public CreditCardPayment(String cardNumberLastFour) {
        this.cardNumberLastFour = cardNumberLastFour;
    }

    @Override
    public String processPayment(double amount) {
        return "Charged $" + amount + " to card ending " + cardNumberLastFour + " - Txn " + getTransactionId();
    }
}

class CashPayment extends PaymentMethod {
    public CashPayment() { super(); }

    @Override
    public String processPayment(double amount) {
        return "Received $" + amount + " in cash - Txn " + getTransactionId();
    }
}

public class CheckoutPaymentHandler {
    static void printConfirmation(PaymentMethod payment, double amount) {
        System.out.println(payment.processPayment(amount));
    }

    public static void main(String[] args) {
        CreditCardPayment cc = new CreditCardPayment("4471");
        PaymentMethod ref = cc; // Upcasting: child reference stored as its parent type.
        printConfirmation(ref, 250.0);
        System.out.println(cc.processPayment(250.0, "Birthday gift"));
        CashPayment cash = new CashPayment();
        System.out.println(cash.processPayment(40.0));
        // new PaymentMethod(); // Does not compile: PaymentMethod is abstract.
    }
}