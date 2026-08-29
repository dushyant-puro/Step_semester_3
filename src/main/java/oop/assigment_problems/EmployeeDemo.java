package oop.assignment_problems;

public class EmployeeDemo {
    public static void main(String[] args) {
        Employee plain = new Employee(101, "Amit", 40000);
        Employee manager = new ManagerEmployee(102, "Rahul", 70000, 8000);
        Employee intern = new InternEmployee(103, "Priya", 12000, 10000);

        if (plain instanceof ManagerEmployee) {
            System.out.println("Manager effective pay: Rs " +
                    ((ManagerEmployee) plain).effectiveSalary());
        } else if (plain instanceof InternEmployee) {
            System.out.println("Intern effective pay: Rs " +
                    ((InternEmployee) plain).effectiveSalary());
        } else {
            System.out.println("Plain employee pay: Rs " + plain.getSalary());
        }

        if (manager instanceof ManagerEmployee) {
            System.out.println("Manager effective pay: Rs " +
                    ((ManagerEmployee) manager).effectiveSalary());
        } else if (manager instanceof InternEmployee) {
            System.out.println("Intern effective pay: Rs " +
                    ((InternEmployee) manager).effectiveSalary());
        } else {
            System.out.println("Plain employee pay: Rs " + manager.getSalary());
        }

        if (intern instanceof ManagerEmployee) {
            System.out.println("Manager effective pay: Rs " +
                    ((ManagerEmployee) intern).effectiveSalary());
        } else if (intern instanceof InternEmployee) {
            System.out.println("Intern effective pay: Rs " +
                    ((InternEmployee) intern).effectiveSalary());
        } else {
            System.out.println("Plain employee pay: Rs " + intern.getSalary());
        }
    }
}
