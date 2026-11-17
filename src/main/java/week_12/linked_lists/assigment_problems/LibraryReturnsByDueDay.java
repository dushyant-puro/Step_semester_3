package week_12.linked_lists.assigment_problems;

public class LibraryReturnsByDueDay {
    static class Node {
        String title;
        int dueDay;
        Node next;

        Node(String title, int dueDay) {
            this.title = title;
            this.dueDay = dueDay;
        }
    }

    public static Node sortByDueDay(Node head) {
        if (head == null || head.next == null) return head;

        // Split the list into two halves using slow and fast pointers.
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = slow.next;
        slow.next = null;

        Node left = sortByDueDay(head);
        Node right = sortByDueDay(secondHalf);
        return merge(left, right);
    }

    private static Node merge(Node left, Node right) {
        Node mergedHead = null;
        Node mergedTail = null;

        while (left != null && right != null) {
            Node selected;
            // Taking from the left on equal days makes the merge stable.
            if (left.dueDay <= right.dueDay) {
                selected = left;
                left = left.next;
            } else {
                selected = right;
                right = right.next;
            }

            if (mergedHead == null) mergedHead = selected;
            else mergedTail.next = selected;
            mergedTail = selected;
        }

        Node remaining = (left != null) ? left : right;
        if (mergedHead == null) return remaining;
        mergedTail.next = remaining;
        return mergedHead;
    }

    private static Node buildList(String[] titles, int[] days) {
        Node head = null;
        Node tail = null;
        for (int i = 0; i < titles.length; i++) {
            Node node = new Node(titles[i], days[i]);
            if (head == null) head = node;
            else tail.next = node;
            tail = node;
        }
        return head;
    }

    private static void printList(Node head) {
        while (head != null) {
            System.out.print(head.title + ":" + head.dueDay + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node returns = buildList(
                new String[]{"Java", "DSA", "OS", "DBMS", "CN", "AI"},
                new int[]{15, 3, 9, 3, 12, 1}
        );
        printList(sortByDueDay(returns));
        printList(sortByDueDay(null));
    }
}
