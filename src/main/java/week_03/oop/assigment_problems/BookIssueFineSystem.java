package week_03.oop.assigment_problems;

/** Week 3 assignment F1: object-based library fine tracking. */
public class BookIssueFineSystem {
    static class BookIssue {
        private final String title;
        private final String borrowerName;
        private final int daysOverdue;

        BookIssue(String title, String borrowerName, int daysOverdue) {
            this.title = title;
            this.borrowerName = borrowerName;
            this.daysOverdue = daysOverdue;
        }
        double fineAmount() { return daysOverdue > 0 ? daysOverdue * 5.0 : 0.0; }
        boolean isSeverelyOverdue() { return daysOverdue > 14; }

        // Static because the total is calculated across many BookIssue objects;
        // fineAmount() is an instance method because it depends on this book issue.
        static double totalFineCollected(BookIssue[] issues) {
            double total = 0;
            for (BookIssue issue : issues) total += issue.fineAmount();
            return total;
        }
    }

    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Ravi", 18),
            new BookIssue("Effective Java", "Anitha", 5),
            new BookIssue("Refactoring", "Meera", 0),
            new BookIssue("DSA Handbook", "Karthik", 21),
            new BookIssue("Design Patterns", "Suresh", 9)
        };
        for (BookIssue issue : issues) {
            System.out.printf("%s - %d days - %s - Fine: Rs %.1f%n",
                issue.title, issue.daysOverdue,
                issue.isSeverelyOverdue() ? "Severely overdue" : "OK", issue.fineAmount());
        }
        System.out.printf("Total fine collected: Rs %.1f%n", BookIssue.totalFineCollected(issues));
    }
}