package oop.assignment_problems;

public class CompanyEmployeeRecord {
    private String name;
    private String empId;
    private Employee employee;
    private ParkingSlot slot;

    static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    String fullProfile() {
        String slotInfo;

        if (slot != null) {
            slotInfo = slot.getSlotNo();
        } else {
            slotInfo = "no parking assigned";
        }

        double pay;

        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        return name + " | Pay: Rs " + pay + " | Slot: " + slotInfo;
    }

    static void totalRecords() {
        System.out.println("Total records: " + totalRecords);
    }

    public static void main(String[] args) {
        ParkingSlot[] slots = {
            new ParkingSlot("A1", 4, 0),
            new ParkingSlot("A2", 5, 0)
        };

        Employee divyaEmployee =
                new ManagerEmployee(101, "Divya", 70000, 8000);

        Employee karanEmployee =
                new Employee(102, "Karan", 40000);

        Employee meeraEmployee =
                new InternEmployee(103, "Meera", 10000, 10000);

        ParkingSlot slot1 = ParkingSlot.findAvailableSlot(slots);
        if (slot1 != null) {
            slot1.allot("TN01DV1001");
        }

        ParkingSlot slot2 = ParkingSlot.findAvailableSlot(slots);
        if (slot2 != null) {
            slot2.allot("TN01KR1002");
        }

        ParkingSlot slot3 = ParkingSlot.findAvailableSlot(new ParkingSlot[] {
            new ParkingSlot("A3", 0, 0)
        });

        CompanyEmployeeRecord record1 =
                new CompanyEmployeeRecord("Divya", "E101", divyaEmployee, slot1);

        CompanyEmployeeRecord record2 =
                new CompanyEmployeeRecord("Karan", "E102", karanEmployee, slot2);

        CompanyEmployeeRecord record3 =
                new CompanyEmployeeRecord("Meera", "E103", meeraEmployee, slot3);

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        CompanyEmployeeRecord.totalRecords();
    }
}
