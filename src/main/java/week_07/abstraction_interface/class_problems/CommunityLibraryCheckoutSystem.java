package week_07.abstraction_interface.class_problems;

interface Renewable { String renew(); }
interface Reservable { String reserve(); }

abstract class LibraryItem {
    private static int nextId = 1000;
    private final String itemId;
    protected final String title;
    protected LibraryItem(String title) { this.title = title; itemId = "LI-" + (++nextId); }
    public abstract int getLoanPeriodDays();
    public String getItemId() { return itemId; }
}

class Textbook extends LibraryItem implements Renewable, Reservable {
    public Textbook(String title) { super(title); }
    @Override public int getLoanPeriodDays() { return 14; }
    @Override public String renew() { return title + " renewed"; }
    @Override public String reserve() { return title + " reserved"; }
}

class Magazine extends LibraryItem implements Renewable {
    public Magazine(String title) { super(title); }
    @Override public int getLoanPeriodDays() { return 7; }
    @Override public String renew() { return title + " renewed"; }
}

class DigitalPass implements Renewable {
    private final String resourceName;
    public DigitalPass(String resourceName) { this.resourceName = resourceName; }
    @Override public String renew() { return resourceName + " renewed"; }
}

public class CommunityLibraryCheckoutSystem {
    static void processCheckouts(LibraryItem[] items) {
        for (LibraryItem item : items) System.out.println(item.getLoanPeriodDays());
    }
    static String reserveIfSupported(Object o) {
        if (o instanceof Reservable) return ((Reservable) o).reserve();
        return "Reservation not supported";
    }
    public static void main(String[] args) {
        Textbook t = new Textbook("Java Fundamentals");
        Magazine m = new Magazine("Tech Monthly");
        DigitalPass d = new DigitalPass("E-Journal Access");
        System.out.println(t.getLoanPeriodDays());
        System.out.println(t.renew());
        System.out.println(t.reserve());
        System.out.println(reserveIfSupported(m));
        System.out.println(reserveIfSupported(d));
        LibraryItem ref = t; // Upcasting: Textbook as LibraryItem.
        System.out.println(reserveIfSupported(ref));
        processCheckouts(new LibraryItem[]{t, m});
    }
}