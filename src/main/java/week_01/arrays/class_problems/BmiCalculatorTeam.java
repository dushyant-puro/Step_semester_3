package week_01.arrays.class_problems;

import java.util.Scanner;

public class BmiCalculatorTeam {
    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25.0) return "Normal";
        if (bmi < 30.0) return "Overweight";
        return "Obese";
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        if (heights.length != weights.length) {
            System.out.println("Height and weight arrays must have equal length.");
            return;
        }
        System.out.printf("%-8s | %-12s | %-12s | %-8s | %s%n", "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] <= 0 || weights[i] <= 0) {
                System.out.println("Invalid measurements for Person " + (i + 1));
                continue;
            }
            double bmi = weights[i] / (heights[i] * heights[i]);
            System.out.printf("%-8d | %-12.2f | %-12.2f | %-8.2f | %s%n", i + 1, heights[i], weights[i], bmi, getBmiStatus(bmi));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of people: ");
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println("Number of people must be positive.");
            sc.close();
            return;
        }
        double[] heights = new double[n], weights = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Person " + (i + 1) + " height (m): ");
            heights[i] = sc.nextDouble();
            System.out.print("Person " + (i + 1) + " weight (kg): ");
            weights[i] = sc.nextDouble();
        }
        printWellnessReport(heights, weights);
        sc.close();
    }
}