package week_04.constructors.class_problems;

public class NightlyFleetReconciliationEngine {
    static class BusTicketAccount {
        private static final double DEFAULT_PENALTY_PERCENT;
        static { DEFAULT_PENALTY_PERCENT = 1.0; }
        protected final String bookingId;
        protected final double ticketFare;
        BusTicketAccount(String bookingId, double ticketFare) {
            if (bookingId == null || bookingId.trim().isEmpty() || ticketFare < 0) throw new IllegalArgumentException("Invalid account");
            this.bookingId = bookingId; this.ticketFare = ticketFare;
        }
        BusTicketAccount(String bookingId) { this(bookingId, 0.0); }
        final double calculatePenalty(int minutesLate) {
            if (minutesLate < 0) throw new IllegalArgumentException("Minutes late cannot be negative");
            if (minutesLate == 0) return 0;
            double tiered = ticketFare * (Math.min(minutesLate, 5) * .005 + Math.min(Math.max(minutesLate - 5, 0), 10) * .01 + Math.max(minutesLate - 15, 0) * .02);
            return Math.max(tiered, ticketFare * DEFAULT_PENALTY_PERCENT / 100.0);
        }
    }
    static class SleeperAccount extends BusTicketAccount {
        SleeperAccount(String id, double fare) { super(id, fare); }
        double settle(double amount) { return amount * 0.9; }
    }
    static void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account instanceof SleeperAccount) amount = ((SleeperAccount) account).settle(amount);
        System.out.printf("%s | settled Rs %.2f | penalty Rs %.2f%n", account.bookingId, amount, account.calculatePenalty(minutesLate));
    }
    static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null || accounts.length != amounts.length || accounts.length != minutesLateArray.length)
            throw new IllegalArgumentException("All input arrays must have equal lengths");
        int processed = 0, skipped = 0, sleeper = 0, regular = 0;
        double total = 0;
        for (int i = 0; i < accounts.length; i++) {
            BusTicketAccount account = accounts[i];
            if (account == null) { skipped++; continue; }
            double penalty = account.calculatePenalty(minutesLateArray[i]);
            processAccount(account, amounts[i], minutesLateArray[i]);
            total += penalty; processed++;
            if (account instanceof SleeperAccount) sleeper++; else regular++;
        }
        System.out.printf("%d processed | %d null skipped | %d sleeper | %d regular | grand total penalties = Rs %.2f%n", processed, skipped, sleeper, regular, total);
    }
    public static void main(String[] args) {
        processBatch(new BusTicketAccount[]{new SleeperAccount("BK001", 2000), null, new BusTicketAccount("BK002", 1200)}, new double[]{1200, 900, 700}, new int[]{10, 5, 0});
    }
}