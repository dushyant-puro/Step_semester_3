package week_12.linked_lists.assigment_problems;

public class TokenMirrorCheck {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        // Find the end of the first half (middle for an odd-sized list).
        Node firstHalfEnd = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            firstHalfEnd = firstHalfEnd.next;
            fast = fast.next.next;
        }

        Node secondHalfStart = reverse(firstHalfEnd.next);
        boolean palindrome = true;
        Node left = head;
        Node right = secondHalfStart;

        while (right != null) {
            if (left.data != right.data) {
                palindrome = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        // Restore the original list before returning, even if a mismatch was found.
        firstHalfEnd.next = reverse(secondHalfStart);
        return palindrome;
    }

    private static Node reverse(Node head) {
        Node previous = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    private static Node buildList(int... values) {
        Node head = null;
        Node tail = null;
        for (int value : values) {
            Node node = new Node(value);
            if (head == null) head = node;
            else tail.next = node;
            tail = node;
        }
        return head;
    }

    private static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node first = buildList(4, 7, 9, 7, 4);
        System.out.println("Palindrome: " + isPalindrome(first));
        System.out.print("List after check: ");
        printList(first);

        Node second = buildList(4, 7, 7, 9);
        System.out.println("Palindrome: " + isPalindrome(second));
        System.out.print("List after check: ");
        printList(second);
    }
}
