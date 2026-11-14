package week_04.delivery_booking.assigment_problems;

public class NightlyKitchenReconciliationEngine {
    static class DeliveryAccount {
        private static final double BASE_SURGE_PERCENT;
        static { BASE_SURGE_PERCENT = 1.0; }
        protected final String studentId;
        protected final double orderValue;
        DeliveryAccount(String studentId, double orderValue) {
            if (studentId == null || studentId.trim().isEmpty() || orderValue < 0) throw new IllegalArgumentException("Invalid delivery account");
            this.studentId = studentId; this.orderValue = orderValue;
        }
        DeliveryAccount(String studentId) { this(studentId, 0.0); }
        final double calculateSurgeFee(int delayMinutes) {
            if (delayMinutes < 0) throw new IllegalArgumentException("Delay cannot be negative");
            if (delayMinutes == 0) return 0;
            double bracketFee = orderValue * (Math.min(delayMinutes, 5) * .005 + Math.min(Math.max(delayMinutes - 5, 0), 10) * .01 + Math.max(delayMinutes - 15, 0) * .02);
            return Math.max(bracketFee, orderValue * BASE_SURGE_PERCENT / 100.0);
        }
    }
    static class PremiumAccount extends DeliveryAccount {
        PremiumAccount(String id, double value) { super(id, value); }
        double applyDiscount(double amount) { return amount * 0.90; }
    }
    static void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        double settledAmount = account instanceof PremiumAccount ? ((PremiumAccount) account).applyDiscount(amount) : amount;
        System.out.printf("%s | amount Rs %.2f | surge fee Rs %.2f%n", account.studentId, settledAmount, account.calculateSurgeFee(delayMinutes));
    }
    static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null || accounts.length != amounts.length || accounts.length != delayMinutesArray.length)
            throw new IllegalArgumentException("Input arrays must have equal lengths");
        int processed = 0, nullSkipped = 0, premium = 0, regular = 0;
        double total = 0;
        for (int i = 0; i < accounts.length; i++) {
            DeliveryAccount account = accounts[i];
            if (account == null) { nullSkipped++; continue; }
            double fee = account.calculateSurgeFee(delayMinutesArray[i]);
            processAccount(account, amounts[i], delayMinutesArray[i]);
            total += fee; processed++;
            if (account instanceof PremiumAccount) premium++; else regular++;
        }
        System.out.printf("%d processed | %d null skipped | %d premium | %d regular | grand total surge fees = Rs %.2f%n", processed, nullSkipped, premium, regular, total);
    }
    public static void main(String[] args) {
        processBatch(new DeliveryAccount[]{new PremiumAccount("STU001", 500), null, new DeliveryAccount("STU002", 300)}, new double[]{500, 400, 300}, new int[]{10, 5, 0});
    }
}