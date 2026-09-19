package week_04.delivery_booking.assigment_problems;

public class GhostOrderValidator {
    static class FoodOrder {
        private final String studentName, dishName;
        private boolean delivered;
        FoodOrder(String studentName, String dishName) {
            if (!valid(studentName) || !valid(dishName)) throw new IllegalArgumentException("Name and dish are required");
            this.studentName = studentName.trim(); this.dishName = dishName.trim();
        }
        private static boolean valid(String value) { return value != null && !value.trim().isEmpty(); }
        void markDelivered() {
            if (delivered) System.out.println("Order already marked delivered.");
            else { delivered = true; System.out.println("Order marked delivered."); }
        }
    }
    static void processBatch(String[][] rawOrders) {
        int valid = 0, rejected = 0;
        if (rawOrders != null) for (String[] row : rawOrders) {
            try { if (row == null || row.length < 2) throw new IllegalArgumentException(); new FoodOrder(row[0], row[1]); valid++; }
            catch (IllegalArgumentException ex) { rejected++; }
        }
        System.out.printf("Valid: %d | Rejected: %d%n", valid, rejected);
    }
    public static void main(String[] args) {
        processBatch(new String[][]{{"Ravi", "Paneer Butter Masala"}, {"", "Chole Bhature"}, {"Meera", " "}, {"Divya", "Veg Biryani"}});
    }
}