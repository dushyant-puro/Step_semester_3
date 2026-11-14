package week_01.arrays.assigment_problems;

import java.util.Scanner;

public class WarehouseInventoryBalancer {
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA.length != sectionB.length || sectionA.length == 0) {
            System.out.println("Arrays must have equal, non-zero lengths.");
            return;
        }
        int totalA = 0, totalB = 0;
        int max = Integer.MIN_VALUE, maxIndex = -1;
        String maxSection = "";
        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            totalB += sectionB[i];
            if (sectionA[i] > max) { max = sectionA[i]; maxIndex = i; maxSection = "Section A"; }
            if (sectionB[i] > max) { max = sectionB[i]; maxIndex = i; maxSection = "Section B"; }
        }
        System.out.printf("Section A Total: %d | Section B Total: %d | Status: %s | Highest Quantity: %d (%s, Item %d)%n",
                totalA, totalB, totalA == totalB ? "Balanced" : "Not Balanced", max, maxSection, maxIndex + 1);
    }

    public static void main(String[] args) {
        analyzeInventory(new int[]{20, 15, 30}, new int[]{25, 10, 30});
    }
}