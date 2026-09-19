package week_02.strings.assigment_problems;

import java.util.*;

public class StopWordFilteredWordFrequencyReport {
    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "the", "was", "and", "a", "is", "of", "in"));

    public static void printFilteredWordFrequency(String feedback) {
        String cleaned = feedback.toLowerCase().replaceAll("[.,!?;:]", " ").trim();
        if (cleaned.isEmpty()) return;

        Map<String, Integer> frequency = new HashMap<>();
        for (String word : cleaned.split("\\s+")) {
            if (!word.isEmpty() && !STOP_WORDS.contains(word)) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequency.entrySet());
        entries.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printFilteredWordFrequency(sc.nextLine());
        sc.close();
    }
}