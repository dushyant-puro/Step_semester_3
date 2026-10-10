package week_10.linked_lists.assigment_problems;

public class MergingTwoTicketCounters {
    static final class Node {
        int data; Node next;
        Node(int data) { this.data = data; }
    }
    /** Relinks existing nodes only. O(m+n) time, O(1) auxiliary space. */
    public static Node mergeSorted(Node a, Node b) {
        Node dummy = new Node(0), tail = dummy;
        while (a != null && b != null) {
            if (a.data <= b.data) { tail.next = a; a = a.next; }
            else { tail.next = b; b = b.next; }
            tail = tail.next;
        }
        tail.next = a != null ? a : b;
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
    public static void main(String[] args) {
        print(mergeSorted(of(3,8,15), of(5,8,12,20)));
    }
}