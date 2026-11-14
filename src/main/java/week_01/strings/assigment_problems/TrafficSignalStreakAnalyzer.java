package week_01.strings.assigment_problems;

import java.util.Scanner;

public class TrafficSignalStreakAnalyzer {
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("Signal log is empty.");
            return;
        }
        char longestColor = signalLog.charAt(0), currentColor = signalLog.charAt(0);
        int longest = 1, current = 1;
        for (int i = 1; i < signalLog.length(); i++) {
            char c = signalLog.charAt(i);
            if (c == currentColor) current++;
            else {
                currentColor = c;
                current = 1;
            }
            if (current > longest) {
                longest = current;
                longestColor = currentColor;
            }
        }
        System.out.println("Longest Streak: '" + longestColor + "' repeated " + longest + " times");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter signal log (R/Y/G): ");
        findLongestStreak(sc.nextLine().trim());
        sc.close();
    }
}