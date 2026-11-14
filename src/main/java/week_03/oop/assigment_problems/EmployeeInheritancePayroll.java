package week_03.oop.assigment_problems;

/** Week 3 assignment F2: extend Employee without changing the base class. */
public class EmployeeInheritancePayroll {
    static class Employee {
        private final String empId;
        private final String empName;
        private final double salary;
        Employee(String empId, String empName, double salary) {
            this.empId = empId; this.empName = empName; this.salary = salary;
        }
        double getSalary() { return salary; }
        String getEmpId() { return empId; }
        String getEmpName() { return empName; }
    }
    static class ManagerEmployee extends Employee {
        private final double teamBonus;
        ManagerEmployee(String id, String name, double salary, double bonus) {
            super(id, name, salary); this.teamBonus = bonus;
        }
        double effectiveSalary() { return getSalary() + teamBonus; }
    }
    static class InternEmployee extends Employee {
        private final double stipendCap;
        InternEmployee(String id, String name, double salary, double cap) {
            super(id, name, salary); this.stipendCap = cap;
        }
        double effectiveSalary() { return Math.min(getSalary(), stipendCap); }
    }
    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("E01", "Plain Employee", 40000),
            new ManagerEmployee("E02", "Manager", 70000, 8000),
            new InternEmployee("E03", "Intern", 12000, 10000)
        };
        for (Employee employee : employees) {
            double pay = employee.getSalary();
            if (employee instanceof ManagerEmployee) pay = ((ManagerEmployee) employee).effectiveSalary();
            else if (employee instanceof InternEmployee) pay = ((InternEmployee) employee).effectiveSalary();
            System.out.printf("%s pay: Rs %.1f%n", employee.getEmpName(), pay);
        }
    }
}