package week_09.data_structures.class_problems;

public class LibraryCatalogLookup {
    public static final class Book {
        public final String isbn, title;
        public Book(String isbn, String title) { this.isbn = isbn; this.title = title; }
    }
    /** Catalog must be sorted lexicographically by ISBN. O(log n) time, O(1) extra space. */
    public static String findBook(Book[] catalog, String targetIsbn) {
        int low = 0, high = catalog.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = catalog[mid].isbn.compareTo(targetIsbn);
            if (cmp == 0) return catalog[mid].title;
            if (cmp < 0) low = mid + 1; else high = mid - 1;
        }
        return "Not Found";
    }
    public static void main(String[] args) {
        Book[] catalog = {
            new Book("0001112223", "Introduction to Algebra"),
            new Book("0002223334", "Beginning Python"),
            new Book("0003334445", "Classic Mythology"),
            new Book("0004445556", "Data and Society"),
            new Book("0005556667", "European History")
        };
        System.out.println(findBook(catalog, "0003334445"));
        System.out.println(findBook(catalog, "0009998887"));
    }
}