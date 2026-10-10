package week_10.linked_lists.assigment_problems;

public class CancellingKthLastOrder {
    static final class Node {
        int data; Node next;
        Node(int data) { this.data = data; }
    }
    /** Removes the kth node from the end in one pass. O(n) time, O(1) space. */
    public static Node removeKthFromEnd(Node head, int k) {
        if (k < 1) throw new IllegalArgumentException("k must be positive");
        Node dummy = new Node(0); dummy.next = head;
        Node lead = dummy, trail = dummy;
        for (int i = 0; i < k + 1; i++) {
            if (lead == null) throw new IllegalArgumentException("k exceeds list length");
            lead = lead.next;
        }
        while (lead != null) { lead = lead.next; trail = trail.next; }
        trail.next = trail.next.next;
        return dummy.next;
    }
    static Node of(int... values) {
        Node dummy = new Node(0), tail = dummy;
        for (int v : values) { tail.next = new Node(v); tail = tail.next; }
        return dummy.next;
    }
    static void print(Node n) {
        while (n != null) { System.out.print(n.data); if (n.next != null) System.out.print(" -> "); n = n.next; }
        System.out.println();
    }
    public static void main(String[] args) { print(removeKthFromEnd(of(10,20,30,40,50), 2)); }
}