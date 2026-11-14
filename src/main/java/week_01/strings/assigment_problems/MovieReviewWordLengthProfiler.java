package week_01.strings.assigment_problems;

import java.util.Scanner;

public class MovieReviewWordLengthProfiler {
    public static void classifyWordLengths(String review) {
        int shortWords = 0, mediumWords = 0, longWords = 0;
        String trimmed = review.trim();
        if (!trimmed.isEmpty()) {
            String[] words = trimmed.split("\\s+");
            for (String word : words) {
                int length = word.length();
                if (length <= 4) shortWords++;
                else if (length <= 8) mediumWords++;
                else longWords++;
            }
        }
        System.out.printf("Short: %d | Medium: %d | Long: %d%n", shortWords, mediumWords, longWords);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter movie review: ");
        classifyWordLengths(sc.nextLine());
        sc.close();
    }
}