package week_09.data_structures.class_problems;

public class MaxSumSubarrayFixedK {
    /** O(n) time, O(1) auxiliary space. */
    public static long maxSumSubarray(int[] sales, int k) {
        if (sales == null || k < 1 || k > sales.length)
            throw new IllegalArgumentException("Require 1 <= k <= sales.length");
        long window = 0;
        for (int i = 0; i < k; i++) window += sales[i];
        long best = window;
        for (int i = k; i < sales.length; i++) {
            window += sales[i] - (long) sales[i - k];
            best = Math.max(best, window);
        }
        return best;
    }
    public static void main(String[] args) {
        System.out.println(maxSumSubarray(new int[]{2,1,5,1,3,2}, 3)); // 9
    }
}