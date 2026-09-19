package week_02.strings.assigment_problems;

import java.util.Scanner;

public class LibraryIsbnNormalizerValidator {
    public static String normalizeCode(String raw) {
        String code = raw.trim();
        if (code.length() < 3) return code.toUpperCase();
        return code.substring(0, 3).toUpperCase() + code.substring(3);
    }

    public static String validateAndFormat(String code) {
        if (code.length() != 13) return "Invalid: code must be exactly 13 characters";
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) return "Invalid: publisher code must be 3 letters";
        }
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) return "Invalid: code body must contain only digits";
        }
        return new StringBuilder().append('[').append(code.substring(0, 3)).append("] YEAR: ")
                .append(code.substring(3, 7)).append(" | CATALOG: ").append(code.substring(7)).toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(validateAndFormat(normalizeCode(sc.nextLine())));
        sc.close();
    }
}