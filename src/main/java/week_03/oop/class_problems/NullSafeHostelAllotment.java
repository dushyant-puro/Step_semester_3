package week_03.oop.class_problems;

public class NullSafeHostelAllotment {
    static class HostelRoom {
        private String roomNo;
        private int beds;
        private int occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        boolean allot(String name) {
            if (occupied >= beds) return false;
            occupied++;
            return true;
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) return null;
        for (HostelRoom room : rooms)
            if (room != null && room.occupied < room.beds) return room;
        return null;
    }

    static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);
        if (room == null) {
            System.out.println("No rooms available for " + studentName);
        } else if (room.allot(studentName)) {
            System.out.println(studentName + " allotted to room " + room.roomNo);
        }
    }

    public static void main(String[] args) {
        // The array holds references to HostelRoom objects. Passing the array copies
        // the reference value, not the room objects, so mutations affect those objects.
        HostelRoom[] rooms = { new HostelRoom("C-214", 3, 2), new HostelRoom("C-507", 2, 2) };
        safeAllot(rooms, "Divya");
        safeAllot(rooms, "Divya");
    }
}