package week_04.delivery_booking.assigment_problems;

public final class ExamWeekSurgeFeeCalculator {
    private final double minimumSurgePercent;
    public ExamWeekSurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0) throw new IllegalArgumentException("Minimum surge percent cannot be negative");
        this.minimumSurgePercent = minimumSurgePercent;
    }
    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) throw new IllegalArgumentException("Order value and delay cannot be negative");
        if (delayMinutes == 0) return 0.0;
        int first = Math.min(delayMinutes, 5);
        int second = Math.min(Math.max(delayMinutes - 5, 0), 10);
        int third = Math.max(delayMinutes - 15, 0);
        double tiered = orderValue * (first * .005 + second * .01 + third * .02);
        return Math.max(tiered, orderValue * minimumSurgePercent / 100.0);
    }
    public static void main(String[] args) {
        ExamWeekSurgeFeeCalculator c = new ExamWeekSurgeFeeCalculator(1.0);
        System.out.println(c.calculateSurgeFee(500, 0));
        System.out.println(c.calculateSurgeFee(500, 1));
        System.out.println(c.calculateSurgeFee(500, 16));
    }
}