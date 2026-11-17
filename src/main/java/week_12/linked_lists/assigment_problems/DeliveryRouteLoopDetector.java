package week_12.linked_lists.assigment_problems;

public class DeliveryRouteLoopDetector {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static String detectLoop(Node head) {
        Node slow = head;
        Node fast = head;

        // Floyd's algorithm: the pointers meet only when a cycle exists.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                int loopLength = 1;
                Node current = slow.next;
                while (current != slow) {
                    loopLength++;
                    current = current.next;
                }

                // Reset one pointer to the head; their next meeting is the loop start.
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return "Loop: yes, starts at stop " + slow.data + ", length " + loopLength;
            }
        }
        return "Loop: no";
    }

    private static Node createList(int[] stops, int pos) {
        if (stops.length == 0) return null;
        Node head = new Node(stops[0]);
        Node tail = head;
        Node loopStart = pos == 0 ? head : null;

        for (int i = 1; i < stops.length; i++) {
            tail.next = new Node(stops[i]);
            tail = tail.next;
            if (i == pos) loopStart = tail;
        }
        if (pos >= 0) tail.next = loopStart;
        return head;
    }

    public static void main(String[] args) {
        System.out.println(detectLoop(createList(new int[]{10, 20, 30, 40, 50}, 2)));
        System.out.println(detectLoop(createList(new int[]{10, 20, 30}, -1)));
        System.out.println(detectLoop(createList(new int[]{7}, 0)));
    }
}
