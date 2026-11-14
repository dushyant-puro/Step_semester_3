package week_03.oop.class_problems;

public class AttendanceSystem {
    static class SrmStudent {
        private String name;
        private String regNo;
        private int attendance;

        SrmStudent(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        void addAttendanceUpdate(int newAttendance) {
            attendance = newAttendance;
        }

        boolean isEligible() {
            return attendance >= 75;
        }

        // classAverage is static because it operates on the whole class array;
        // isEligible is an instance method because it checks this student's attendance.
        static double classAverage(SrmStudent[] students) {
            if (students == null || students.length == 0) return 0.0;
            int total = 0;
            for (SrmStudent student : students) total += student.attendance;
            return (double) total / students.length;
        }
    }

    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA231100301011", 82),
            new SrmStudent("Anitha", "RA231100301012", 68),
            new SrmStudent("Karthik", "RA231100301013", 91),
            new SrmStudent("Meera", "RA231100301014", 74),
            new SrmStudent("Suresh", "RA231100301015", 60)
        };

        for (SrmStudent student : students) {
            System.out.printf("%s - %d%% - %s%n", student.name, student.attendance,
                    student.isEligible() ? "Eligible" : "Detained");
        }
        System.out.printf("Class average: %.1f%%%n", SrmStudent.classAverage(students));
    }
}