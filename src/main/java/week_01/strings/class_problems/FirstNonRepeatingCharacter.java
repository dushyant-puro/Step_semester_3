package week_01.strings.class_problems;

import java.util.Scanner;

public class FirstNonRepeatingCharacter {
    public static char findFirstNonRepeatingChar(String text) {
        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);
            int frequency = 0;
            for (int j = 0; j < text.length(); j++) {
                if (text.charAt(j) == current) frequency++;
            }
            if (frequency == 1) return current;
        }
        return '\0';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        char result = findFirstNonRepeatingChar(text);
        if (result == '\0') System.out.println("No Non-Repeating Character Found");
        else System.out.println("First Non-Repeating Character: '" + result + "'");
        sc.close();
    }
}