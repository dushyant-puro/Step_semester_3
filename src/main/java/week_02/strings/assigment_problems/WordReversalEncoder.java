package week_02.strings.assigment_problems;

import java.util.Scanner;

public class WordReversalEncoder {
    public static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            StringBuilder reversed = new StringBuilder();
            for (int j = words[i].length() - 1; j >= 0; j--) reversed.append(words[i].charAt(j));
            if (i > 0) result.append(' ');
            result.append(reversed);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(reverseEachWord(sc.nextLine()));
        sc.close();
    }
}