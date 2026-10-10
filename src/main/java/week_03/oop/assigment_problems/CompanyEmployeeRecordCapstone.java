package week_03.oop.assigment_problems;

/** Week 3 assignment F5: employee records, inheritance, composition and null-safe parking. */
public class CompanyEmployeeRecordCapstone {
    static class Employee {
        private final String id, name;
        private final double salary;
        Employee(String id, String name, double salary) {
            this.id = id; this.name = name; this.salary = salary;
        }
        double effectivePay() { return salary; }
        String name() { return name; }
    }
    static class ManagerEmployee extends Employee {
        private final double bonus;
        ManagerEmployee(String id, String name, double salary, double bonus) {
            super(id, name, salary); this.bonus = bonus;
        }
        @Override double effectivePay() { return super.effectivePay() + bonus; }
    }
    static class ParkingSlot {
        final String slotNo; boolean occupied;
        ParkingSlot(String slotNo) { this.slotNo = slotNo; }
        boolean allot() { if (occupied) return false; occupied = true; return true; }
    }
    static class CompanyEmployeeRecord {
        final String name, empId;
        final Employee employee;
        ParkingSlot slot;
        static int totalRecords = 0;
        CompanyEmployeeRecord(String name, String empId, Employee employee) {
            this.name = name; this.empId = empId; this.employee = employee; totalRecords++;
        }
        String fullProfile() {
            return name + " | Pay: Rs " + employee.effectivePay() + " | Slot: "
                + (slot == null ? "no parking assigned" : slot.slotNo);
        }
    }
    static void safeAllot(ParkingSlot slot, CompanyEmployeeRecord record) {
        if (slot != null && slot.allot()) record.slot = slot;
    }
    public static void main(String[] args) {
        CompanyEmployeeRecord a = new CompanyEmployeeRecord("Divya", "E01",
            new ManagerEmployee("E01", "Divya", 70000, 8000));
        CompanyEmployeeRecord b = new CompanyEmployeeRecord("Karan", "E02",
            new Employee("E02", "Karan", 40000));
        CompanyEmployeeRecord c = new CompanyEmployeeRecord("Meera", "E03",
            new Employee("E03", "Meera", 10000));
        safeAllot(new ParkingSlot("A1"), a);
        safeAllot(new ParkingSlot("A2"), b);
        System.out.println(a.fullProfile());
        System.out.println(b.fullProfile());
        System.out.println(c.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}