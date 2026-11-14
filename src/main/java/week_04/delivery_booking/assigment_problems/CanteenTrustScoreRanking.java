package week_04.delivery_booking.assigment_problems;

public class CanteenTrustScoreRanking {
    static class Canteen {
        private final String canteenCode, canteenName;
        private final int trustScore;
        Canteen(String canteenCode, String canteenName, int trustScore) {
            if (canteenCode == null || canteenName == null || canteenCode.trim().isEmpty() || canteenName.trim().isEmpty()) throw new IllegalArgumentException("Canteen details required");
            this.canteenCode = canteenCode; this.canteenName = canteenName; this.trustScore = trustScore;
        }
        Canteen(String code, String name) { this(code, name, 3); }
        int compareTo(Canteen other) {
            int c = Integer.compare(other.trustScore, trustScore);
            if (c == 0) c = canteenCode.compareToIgnoreCase(other.canteenCode);
            if (c == 0) c = Integer.compare(canteenName.length(), other.canteenName.length());
            return c;
        }
        String getCode() { return canteenCode; }
    }
    static Canteen[] rankCanteens(Canteen[] canteens) {
        Canteen[] sorted = canteens.clone();
        for (int i = 0; i < sorted.length - 1; i++) for (int j = 0; j < sorted.length - 1 - i; j++)
            if (sorted[j].compareTo(sorted[j + 1]) > 0) { Canteen temp = sorted[j]; sorted[j] = sorted[j + 1]; sorted[j + 1] = temp; }
        return sorted;
    }
    public static void main(String[] args) {
        Canteen[] ranked = rankCanteens(new Canteen[]{new Canteen("HB3-C", "Spice Junction", 3), new Canteen("hb1-c", "Grand Mess", 5), new Canteen("HB2-C", "Southern Treats")});
        for (Canteen c : ranked) System.out.println(c.getCode());
    }
}