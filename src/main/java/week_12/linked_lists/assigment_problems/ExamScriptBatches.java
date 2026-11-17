package week_12.linked_lists.assigment_problems;

public class ExamScriptBatches {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node reverseInGroups(Node head, int k) {
        if (head == null || k <= 1) return head;

        Node current = head;
        Node newHead = null;
        Node previousGroupTail = null;

        while (current != null) {
            // Check that a complete group of k nodes is available.
            Node kth = current;
            for (int i = 1; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) break; // Leave the incomplete final group unchanged.

            Node nextGroup = kth.next;
            Node previous = nextGroup;
            Node groupTail = current;

            // Reverse exactly k links without creating any new list nodes.
            while (current != nextGroup) {
                Node next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            if (newHead == null) {
                newHead = kth;
            } else {
                previousGroupTail.next = kth;
            }
            previousGroupTail = groupTail;
            current = nextGroup;
        }

        return newHead == null ? head : newHead;
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
        printList(reverseInGroups(buildList(1, 2, 3, 4, 5, 6, 7, 8), 3));
        printList(reverseInGroups(buildList(11, 12), 3));
    }
}
