package week_03.oop.assigment_problems;

/** Week 3 assignment F3: safely allocate parking without dereferencing null. */
public class ParkingSlotSafeAllocator {
    static class ParkingSlot {
        final String slotNo;
        final int capacity;
        int occupiedCount;
        ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            if (capacity < 0 || occupiedCount < 0 || occupiedCount > capacity)
                throw new IllegalArgumentException("Invalid parking capacity");
            this.slotNo = slotNo; this.capacity = capacity; this.occupiedCount = occupiedCount;
        }
        boolean allot(String vehicleNo) {
            if (occupiedCount >= capacity) return false;
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
            return true;
        }
    }
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        if (slots == null) return null;
        for (ParkingSlot slot : slots)
            if (slot != null && slot.occupiedCount < slot.capacity) return slot;
        return null;
    }
    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        // Arrays contain references to the original objects; mutating a slot updates that object.
        ParkingSlot slot = findAvailableSlot(slots);
        if (slot == null) System.out.println("No slots available for " + vehicleNo);
        else slot.allot(vehicleNo);
    }
    public static void main(String[] args) {
        ParkingSlot[] slots = {new ParkingSlot("A1", 4, 3), new ParkingSlot("A2", 5, 5)};
        safeAllot(slots, "TN09AB1234");
        safeAllot(slots, "TN09CD5678");
    }
}