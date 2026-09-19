package week_03.oop.class_problems;

public class HostelFeeManagementSystem {
    static class FeeAccount {
        private String regNo;
        private double totalFee;
        private double amountPaid;

        FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
        }

        void pay(double amount) {
            if (amount <= 0) {
                System.out.println("Payment rejected for " + regNo + ": amount must be positive.");
                return;
            }
            amountPaid += amount;
        }

        double getDue() { return Math.max(0, totalFee - amountPaid); }
    }

    static class HostelFeeAccount extends FeeAccount {
        HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }

        void payInTwoInstallments(double amount) {
            if (amount <= 0) {
                pay(amount);
                return;
            }
            pay(amount / 2);
            pay(amount / 2);
        }
    }

    static class HostelRoom {
        private String roomNo;
        private int beds;
        private int occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        boolean allot(String studentName) {
            if (occupied >= beds) return false;
            occupied++;
            return true;
        }
    }

    static class SrmStudent {
        private String name;
        private String regNo;
        private HostelFeeAccount feeAccount;
        private HostelRoom room;
        static int totalStudents = 0;

        SrmStudent(String name, String regNo, HostelFeeAccount feeAccount) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            totalStudents++;
        }

        void assignRoom(HostelRoom room) { this.room = room; }

        String fullStatus() {
            return name + " | Due: Rs " + feeAccount.getDue() + " | Room: "
                    + (room == null ? "unallotted" : room.roomNo);
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) return null;
        for (HostelRoom room : rooms)
            if (room != null && room.occupied < room.beds) return room;
        return null;
    }

    static void safeAllot(HostelRoom[] rooms, SrmStudent student) {
        HostelRoom available = findAvailableRoom(rooms);
        if (available != null && available.allot(student.name)) student.assignRoom(available);
    }

    public static void main(String[] args) {
        HostelRoom[] rooms = { new HostelRoom("C-214", 1, 0), new HostelRoom("C-507", 1, 0) };
        SrmStudent ravi = new SrmStudent("Ravi", "RA001", new HostelFeeAccount("RA001", 200000, 60000));
        SrmStudent anitha = new SrmStudent("Anitha", "RA002", new HostelFeeAccount("RA002", 200000, 20000));
        SrmStudent karthik = new SrmStudent("Karthik", "RA003", new HostelFeeAccount("RA003", 200000, 0));

        ravi.feeAccount.pay(0);       // Rejected: zero is not a valid payment.
        anitha.feeAccount.pay(20000); // Valid payment.
        karthik.feeAccount.pay(-100); // Rejected: negative payment.

        safeAllot(rooms, ravi);
        safeAllot(rooms, anitha);
        safeAllot(rooms, karthik); // No room remains; the student's room reference stays null.

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}