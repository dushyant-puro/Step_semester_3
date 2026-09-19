package week_03.oop.class_problems;

public class FeeAccountInheritanceDemo {
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
                System.out.println("Payment rejected: amount must be positive.");
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
            pay(amount / 2);
            pay(amount / 2);
        }
    }

    static class ScholarshipFeeAccount extends FeeAccount {
        private double scholarshipPercent;

        ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
            super(regNo, totalFee, amountPaid);
            if (scholarshipPercent < 0 || scholarshipPercent > 100)
                throw new IllegalArgumentException("Scholarship must be between 0 and 100.");
            this.scholarshipPercent = scholarshipPercent;
        }

        double effectiveDue() { return getDue() * (1 - scholarshipPercent / 100); }
    }

    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("RA001", 150000, 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("RA002", 200000, 60000);
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("RA003", 180000, 0, 20);

        hostel.payInTwoInstallments(0); // Demonstrates the installment method; rejected amounts are handled safely.
        scholarship.pay(-500); // Invalid payment is rejected.

        System.out.println("Plain account due: Rs " + plain.getDue());
        System.out.println("Hostel account due: Rs " + hostel.getDue());
        if (scholarship instanceof ScholarshipFeeAccount)
            System.out.println("Scholarship account effective due: Rs " + scholarship.effectiveDue());
    }
}