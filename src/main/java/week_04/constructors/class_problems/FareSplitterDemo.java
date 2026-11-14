package week_04.constructors.class_problems;

import java.util.Arrays;

public class FareSplitterDemo {
    static class FareSplitter {
        private final String tripId;
        private final double totalFare;
        private final int passengerCount;

        FareSplitter(String tripId, double totalFare, int passengerCount) {
            if (tripId == null || tripId.trim().isEmpty() || totalFare < 0 || passengerCount <= 0)
                throw new IllegalArgumentException("Invalid trip details.");
            this.tripId = tripId;
            this.totalFare = Math.round(totalFare * 100.0) / 100.0;
            this.passengerCount = passengerCount;
        }
        FareSplitter(String tripId, double totalFare) { this(tripId, totalFare, 2); }
        FareSplitter(String tripId) { this(tripId, 0.0, 2); }

        double[] fareBreakdown() {
            long totalPaise = Math.round(totalFare * 100);
            long base = totalPaise / passengerCount;
            long remainder = totalPaise % passengerCount;
            double[] shares = new double[passengerCount];
            for (int i = 0; i < passengerCount; i++) shares[i] = (base + (i >= passengerCount - remainder ? 1 : 0)) / 100.0;
            return shares;
        }
        boolean isConfirmationOverdue(int confirmed, int expected) { return confirmed < expected; }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FareSplitter("TRIP001", 100000, 3).fareBreakdown()));
        System.out.println(Arrays.toString(new FareSplitter("TRIP003").fareBreakdown()));
    }
}