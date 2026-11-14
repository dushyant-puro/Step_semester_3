package week_03.oop.class_problems;

public class InstanceStaticBoundaryDemo {
    // Broken model: these fields describe an individual student, so making them static
    // means every object shares one value. The latest constructor call overwrites earlier data.
    static class BrokenStudent {
        static String name;
        static String regNo;
        static int attendance;

        BrokenStudent(String name, String regNo, int attendance) {
            BrokenStudent.name = name;
            BrokenStudent.regNo = regNo;
            BrokenStudent.attendance = attendance;
        }
    }

    static class Student {
        private String name;
        private String regNo;
        private int attendance;
        private static final String university = "SRM Institute of Science and Technology";
        private static int admissionCount = 0;

        Student(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            admissionCount++;
            this.regNo = String.format("RA231100301%03d", 10 + admissionCount);
        }

        void printIdCard() {
            System.out.println(name + " | " + regNo + " | " + attendance + "% | " + university);
        }

        static void printTotalAdmissions() {
            System.out.println("Students admitted so far: " + admissionCount);
        }
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenStudent ravi = new BrokenStudent("Ravi", "RA001", 82);
        BrokenStudent meera = new BrokenStudent("Meera", "RA002", 74);
        System.out.println(ravi.name);
        System.out.println(meera.name);
        System.out.println("(Ravi's data was overwritten — both students now show Meera)");

        System.out.println("Fixed version:");
        Student first = new Student("Ravi", 82);
        Student second = new Student("Meera", 74);
        first.printIdCard();
        second.printIdCard();
        Student.printTotalAdmissions();
    }
}