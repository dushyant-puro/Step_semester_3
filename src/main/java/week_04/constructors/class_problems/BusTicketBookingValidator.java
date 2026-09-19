package week_04.constructors.class_problems;

import java.util.HashSet;
import java.util.Set;

public class BusTicketBookingValidator {
    static class BusTicket {
        private final String passengerName;
        private final String destination;
        private boolean checkedIn;

        BusTicket(String passengerName, String destination) {
            if (!meaningfulName(passengerName) || !meaningfulDestination(destination)) {
                throw new IllegalArgumentException("Passenger name or destination is invalid.");
            }
            this.passengerName = passengerName.trim();
            this.destination = destination.trim();
        }

        private static boolean meaningfulName(String value) {
            return value != null && value.trim().matches("[A-Za-z ]+") && !value.trim().isEmpty();
        }

        private static boolean meaningfulDestination(String value) {
            return value != null && !value.trim().isEmpty();
        }

        void markCheckedIn() {
            if (checkedIn) System.out.println(passengerName + " is already checked in.");
            else { checkedIn = true; System.out.println(passengerName + " checked in."); }
        }

        String bookingKey() { return passengerName.toLowerCase() + "|" + destination.toLowerCase(); }
    }

    static void processBatch(String[][] rawBookings) {
        Set<String> accepted = new HashSet<>();
        int valid = 0, rejected = 0, duplicates = 0;
        for (String[] row : rawBookings) {
            try {
                if (row == null || row.length < 2) throw new IllegalArgumentException();
                BusTicket ticket = new BusTicket(row[0], row[1]);
                if (!accepted.add(ticket.bookingKey())) duplicates++;
                else valid++;
            } catch (IllegalArgumentException ex) { rejected++; }
        }
        System.out.printf("Valid: %d | Rejected: %d | Duplicates skipped: %d%n", valid, rejected, duplicates);
    }

    public static void main(String[] args) {
        processBatch(new String[][]{{"Divya", "Chennai"}, {"", "Bangalore"}, {"Ravi123", "Pune"}, {"Divya", "Chennai"}, {" ", " "}});
        BusTicket ticket = new BusTicket("Meera", "Delhi");
        ticket.markCheckedIn();
        ticket.markCheckedIn();
    }
}