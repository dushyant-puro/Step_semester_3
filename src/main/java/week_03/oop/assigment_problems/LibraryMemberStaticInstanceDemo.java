package week_03.oop.assigment_problems;

/** Week 3 assignment F4: demonstrate the static-field bug and its correction. */
public class LibraryMemberStaticInstanceDemo {
    static class BrokenLibraryMember {
        static String name;
        static String memberId;
        static int booksIssued;
        BrokenLibraryMember(String n, String id, int books) {
            name = n; memberId = id; booksIssued = books;
        }
        void printCard() { System.out.printf("%s | %s | Books: %d%n", name, memberId, booksIssued); }
    }
    static class LibraryMember {
        private final String name;
        private final String memberId;
        private int booksIssued;
        private static final String libraryName = "SRM Library";
        private static int memberCount = 0;
        LibraryMember(String name, int booksIssued) {
            this.name = name; this.booksIssued = booksIssued;
            this.memberId = String.format("LIB%03d", ++memberCount);
        }
        void printMemberCard() {
            System.out.printf("%s | %s | %s | Books: %d%n", libraryName, memberId, name, booksIssued);
        }
        static void printTotalMembers() { System.out.println("Total members: " + memberCount); }
    }
    public static void main(String[] args) {
        System.out.println("Broken version (second member overwrites shared fields):");
        BrokenLibraryMember first = new BrokenLibraryMember("Ravi", "M001", 2);
        BrokenLibraryMember second = new BrokenLibraryMember("Meera", "M002", 4);
        first.printCard();
        second.printCard();

        System.out.println("Corrected version (each member retains independent data):");
        LibraryMember a = new LibraryMember("Ravi", 2);
        LibraryMember b = new LibraryMember("Meera", 4);
        a.printMemberCard();
        b.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}