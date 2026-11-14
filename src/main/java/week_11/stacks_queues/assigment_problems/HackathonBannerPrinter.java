package week_11.stacks_queues.assigment_problems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem 2: The Hackathon Banner Printer
 *
 * Expands k[text] patterns, including nested patterns, without recursion.
 * One stack stores repeat counts and the other stores previous text builders.
 */
public class HackathonBannerPrinter {

    public static String expandBanner(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Banner code cannot be null.");
        }

        Deque<Integer> repeatCounts = new ArrayDeque<>();
        Deque<StringBuilder> previousTexts = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();

        int i = 0;

        while (i < code.length()) {
            char ch = code.charAt(i);

            if (ch >= '0' && ch <= '9') {
                // Read the complete repeat count (it may contain multiple digits).
                int count = 0;
                while (i < code.length()
                        && code.charAt(i) >= '0'
                        && code.charAt(i) <= '9') {
                    count = count * 10 + (code.charAt(i) - '0');
                    i++;
                }

                if (count <= 0 || i >= code.length() || code.charAt(i) != '[') {
                    throw new IllegalArgumentException("Invalid repeat pattern.");
                }

                repeatCounts.push(count);
                previousTexts.push(current);
                current = new StringBuilder();

                // Skip the opening bracket; the next characters form the inner text.
                i++;
            } else if (ch == ']') {
                if (repeatCounts.isEmpty() || previousTexts.isEmpty()) {
                    throw new IllegalArgumentException("Unmatched closing bracket.");
                }

                int repetitions = repeatCounts.pop();
                StringBuilder expanded = previousTexts.pop();

                for (int repeat = 0; repeat < repetitions; repeat++) {
                    expanded.append(current);
                }

                current = expanded;
                i++;
            } else if (ch == '[') {
                throw new IllegalArgumentException("Opening bracket needs a repeat count.");
            } else {
                // Ordinary characters outside or inside brackets are copied as-is.
                current.append(ch);
                i++;
            }
        }

        if (!repeatCounts.isEmpty() || !previousTexts.isEmpty()) {
            throw new IllegalArgumentException("Unmatched opening bracket.");
        }

        return current.toString();
    }

    public static void main(String[] args) {
        System.out.println(expandBanner("3[ab]2[c]"));
        System.out.println(expandBanner("2[x3[y]]z"));
    }
}
