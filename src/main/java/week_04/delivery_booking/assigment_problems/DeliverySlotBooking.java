package week_04.delivery_booking.assigment_problems;

public class DeliverySlotBooking {
    static class DeliverySlot {
        private final String orderId, timeSlot;
        DeliverySlot(String orderId, String timeSlot) {
            if (orderId == null || orderId.trim().isEmpty() || timeSlot == null || timeSlot.trim().isEmpty()) throw new IllegalArgumentException("Order ID and slot required");
            this.orderId = orderId; this.timeSlot = timeSlot;
        }
        DeliverySlot(String orderId) { this(orderId, "ASAP"); }
        boolean isPeakHour() { return timeSlot.equals("12:00-13:00") || timeSlot.equals("13:00-14:00") || timeSlot.equals("19:00-20:00") || timeSlot.equals("20:00-21:00"); }
    }
    public static void main(String[] args) {
        System.out.println(new DeliverySlot("ORD101", "13:00-14:00").isPeakHour());
        System.out.println(new DeliverySlot("ORD102").isPeakHour());
    }
}