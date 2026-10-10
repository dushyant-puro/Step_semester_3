package week_10.linked_lists.assigment_problems;

public class CampusPortalBrowserHistory {
    private static final class Node {
        String url; Node prev, next;
        Node(String url) { this.url = url; }
    }
    private Node current;
    public CampusPortalBrowserHistory(String homepage) {
        if (homepage == null || homepage.isEmpty()) throw new IllegalArgumentException("Homepage required");
        current = new Node(homepage);
    }
    /** O(1); forward nodes are detached and become eligible for garbage collection. */
    public String visit(String url) {
        if (url == null || url.isEmpty()) throw new IllegalArgumentException("URL required");
        current.next = null;
        Node node = new Node(url); node.prev = current; current.next = node; current = node;
        return current.url;
    }
    public String back(int steps) {
        if (steps < 0) throw new IllegalArgumentException("steps cannot be negative");
        while (steps-- > 0 && current.prev != null) current = current.prev;
        return current.url;
    }
    public String forward(int steps) {
        if (steps < 0) throw new IllegalArgumentException("steps cannot be negative");
        while (steps-- > 0 && current.next != null) current = current.next;
        return current.url;
    }
    public static void main(String[] args) {
        CampusPortalBrowserHistory h = new CampusPortalBrowserHistory("srm.edu");
        h.visit("a.com"); h.visit("b.com"); h.visit("c.com");
        System.out.println(h.back(1)); System.out.println(h.back(1));
        System.out.println(h.forward(1)); h.visit("d.com");
        System.out.println(h.forward(2)); System.out.println(h.back(2)); System.out.println(h.back(7));
    }
}