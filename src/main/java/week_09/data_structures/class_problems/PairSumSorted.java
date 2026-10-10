package week_09.data_structures.class_problems;

import java.util.Arrays;

public class PairSumSorted {
    /** Returns the matching values, or an empty array when no pair exists. O(n) time, O(1) extra space. */
    public static int[] pairSumSorted(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            long sum = (long) nums[left] + nums[right];
            if (sum == target) return new int[]{nums[left], nums[right]};
            if (sum < target) left++; else right--;
        }
        return new int[0];
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(pairSumSorted(new int[]{-4, -1, 0, 3, 5, 9}, 4)));
        System.out.println(Arrays.toString(pairSumSorted(new int[]{1, 2, 3}, 100)));
    }
}