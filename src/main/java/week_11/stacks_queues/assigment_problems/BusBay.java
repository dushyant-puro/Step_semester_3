package week_11.stacks_queues.assigment_problems;

/**
 * Problem 4: The Bus Bay Ring
 *
 * Fixed-size circular deque backed only by an int array.
 * All insert, delete and peek operations run in O(1) time.
 */
public class BusBay {

    private final int[] buses;
    private int front;
    private int size;

    public BusBay(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }

        buses = new int[capacity];
        front = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == buses.length;
    }

    public boolean insertFront(int bus) {
        if (isFull()) {
            return false;
        }

        front = (front - 1 + buses.length) % buses.length;
        buses[front] = bus;
        size++;
        return true;
    }

    public boolean insertLast(int bus) {
        if (isFull()) {
            return false;
        }

        int rearIndex = (front + size) % buses.length;
        buses[rearIndex] = bus;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }

        front = (front + 1) % buses.length;
        size--;

        if (size == 0) {
            front = 0;
        }

        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        size--;

        if (size == 0) {
            front = 0;
        }

        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : buses[front];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }

        int rearIndex = (front + size - 1) % buses.length;
        return buses[rearIndex];
    }

    public static void main(String[] args) {
        BusBay bay = new BusBay(3);

        System.out.println(bay.insertLast(101));
        System.out.println(bay.insertLast(102));
        System.out.println(bay.insertFront(100));
        System.out.println(bay.insertFront(99));
        System.out.println(bay.getRear());
        System.out.println(bay.isFull());
        System.out.println(bay.deleteLast());
        System.out.println(bay.insertFront(98));
        System.out.println(bay.getFront());
    }
}
