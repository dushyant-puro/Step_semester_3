package week_12.linked_lists.assigment_problems;

import java.util.HashMap;
import java.util.Map;

public class RecentCache {
    private static class Node {
        int key;
        String page;
        Node previous;
        Node next;

        Node(int key, String page) {
            this.key = key;
            this.page = page;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head;
    private final Node tail;

    public RecentCache(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacity must be at least 1");
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(-1, "");
        this.tail = new Node(-1, "");
        head.next = tail;
        tail.previous = head;
    }

    public String get(int key) {
        Node node = cache.get(key);
        if (node == null) return "-1";
        moveToMostRecent(node);
        return node.page;
    }

    public void put(int key, String page) {
        Node existing = cache.get(key);
        if (existing != null) {
            existing.page = page;
            moveToMostRecent(existing);
            return;
        }

        if (cache.size() == capacity) {
            Node leastRecent = tail.previous;
            unlink(leastRecent);
            cache.remove(leastRecent.key);
        }

        Node node = new Node(key, page);
        cache.put(key, node);
        addAfterHead(node);
    }

    private void moveToMostRecent(Node node) {
        unlink(node);
        addAfterHead(node);
    }

    private void unlink(Node node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }

    private void addAfterHead(Node node) {
        node.next = head.next;
        node.previous = head;
        head.next.previous = node;
        head.next = node;
    }

    private String contentsMostRecentFirst() {
        StringBuilder result = new StringBuilder();
        Node current = head.next;
        while (current != tail) {
            if (result.length() > 0) result.append(", ");
            result.append(current.key).append("=").append(current.page);
            current = current.next;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        RecentCache recentCache = new RecentCache(2);
        recentCache.put(1, "Home");
        System.out.println("put(1,Home)");
        recentCache.put(2, "Notes");
        System.out.println("put(2,Notes)");
        System.out.println("get(1) -> " + recentCache.get(1));

        int evicted = 2;
        recentCache.put(3, "Quiz");
        System.out.println("put(3,Quiz) evicted " + evicted);
        System.out.println("get(2) -> " + recentCache.get(2));

        recentCache.put(1, "Home2");
        System.out.println("put(1,Home2) updated");
        evicted = 3;
        recentCache.put(4, "Mail");
        System.out.println("put(4,Mail) evicted " + evicted);
        System.out.println("get(3) -> " + recentCache.get(3));
        System.out.println("get(1) -> " + recentCache.get(1));
        System.out.println("Cache (most recent first): " + recentCache.contentsMostRecentFirst());
    }
}
