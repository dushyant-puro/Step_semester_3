package week_11.stacks_queues.assigment_problems;

import java.util.Arrays;

/**
 * Problem 1: The Canteen Price Streak
 *
 * Uses a monotonic stack of indices to calculate every price streak in O(n).
 */
public class CanteenPriceStreak {

    public static int[] priceStreak(int[] price) {
        if (price == null) {
            throw new IllegalArgumentException("Price array cannot be null.");
        }

        int n = price.length;
        int[] streak = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = 0; i < n; i++) {
            // Remove earlier days that cannot block today's streak.
            while (top >= 0 && price[stack[top]] <= price[i]) {
                top--;
            }

            // The remaining top is the nearest previous day with a higher price.
            streak[i] = (top == -1) ? i + 1 : i - stack[top];

            // Keep today's index for future days.
            stack[++top] = i;
        }

        return streak;
    }

    public static void main(String[] args) {
        int[] first = {100, 80, 60, 70, 60, 75, 85};
        int[] second = {10, 20, 30};

        System.out.println(Arrays.toString(priceStreak(first)));
        System.out.println(Arrays.toString(priceStreak(second)));
    }
}
