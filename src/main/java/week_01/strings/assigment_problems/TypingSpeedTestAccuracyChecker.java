package week_01.strings.assigment_problems;

import java.util.Scanner;

public class TypingSpeedTestAccuracyChecker {
    public static void checkTypingAccuracy(String original, String typed) {
        if (original.length() != typed.length()) {
            System.out.println("The two strings must have equal length.");
            return;
        }
        int matched = 0, firstMismatch = -1;
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) matched++;
            else if (firstMismatch == -1) firstMismatch = i;
        }
        double accuracy = original.isEmpty() ? 100.0 : matched * 100.0 / original.length();
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ", matched, original.length(), accuracy);
        if (firstMismatch == -1) System.out.println("No Mismatches");
        else System.out.printf("First Mismatch at position %d ('%c' vs '%c')%n", firstMismatch + 1,
                original.charAt(firstMismatch), typed.charAt(firstMismatch));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Original passage: ");
        String original = sc.nextLine();
        System.out.print("Typed passage (same length): ");
        String typed = sc.nextLine();
        checkTypingAccuracy(original, typed);
        sc.close();
    }
}