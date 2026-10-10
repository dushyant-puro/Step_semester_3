package week_10.linked_lists.assigment_problems;

public class PolynomialCalculator {
    static final class Node {
        int coefficient, exponent; Node next;
        Node(int coefficient, int exponent) { this.coefficient = coefficient; this.exponent = exponent; }
    }
    /** Inputs are ordered by decreasing exponent. O(m+n) time and O(m+n) output space. */
    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0), tail = dummy;
        while (p != null || q != null) {
            int coefficient, exponent;
            if (q == null || (p != null && p.exponent > q.exponent)) {
                coefficient = p.coefficient; exponent = p.exponent; p = p.next;
            } else if (p == null || q.exponent > p.exponent) {
                coefficient = q.coefficient; exponent = q.exponent; q = q.next;
            } else {
                coefficient = p.coefficient + q.coefficient; exponent = p.exponent;
                p = p.next; q = q.next;
            }
            if (coefficient != 0) {
                tail.next = new Node(coefficient, exponent);
                tail = tail.next;
            }
        }
        return dummy.next;
    }
    static void printPolynomial(Node node) {
        if (node == null) { System.out.println("0"); return; }
        boolean first = true;
        while (node != null) {
            int c = node.coefficient, e = node.exponent;
            if (!first) System.out.print(c < 0 ? " - " : " + ");
            else if (c < 0) System.out.print("-");
            int abs = Math.abs(c);
            if (e == 0 || abs != 1) System.out.print(abs);
            if (e > 0) { System.out.print("x"); if (e > 1) System.out.print("^" + e); }
            first = false; node = node.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Node p = new Node(5,3); p.next = new Node(4,2); p.next.next = new Node(2,0);
        Node q = new Node(5,2); q.next = new Node(-3,1); q.next.next = new Node(1,0);
        printPolynomial(addPolynomials(p,q));
    }
}