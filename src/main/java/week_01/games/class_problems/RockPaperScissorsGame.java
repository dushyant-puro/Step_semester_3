package week_01.games.class_problems;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsGame {
    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) return "Draw";
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equals("Scissors"))
                || (playerMove.equalsIgnoreCase("Paper") && computerMove.equals("Rock"))
                || (playerMove.equalsIgnoreCase("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int rounds = 5, wins = 0, losses = 0, draws = 0;
        String[] players = new String[rounds], computers = new String[rounds], results = new String[rounds];

        for (int i = 0; i < rounds; i++) {
            System.out.print("Round " + (i + 1) + " - Enter Rock, Paper, or Scissors: ");
            String move = sc.nextLine().trim();
            while (!move.equalsIgnoreCase("Rock") && !move.equalsIgnoreCase("Paper") && !move.equalsIgnoreCase("Scissors")) {
                System.out.print("Invalid move. Enter Rock, Paper, or Scissors: ");
                move = sc.nextLine().trim();
            }
            players[i] = move;
            computers[i] = MOVES[random.nextInt(MOVES.length)];
            results[i] = playRound(players[i], computers[i]);
            if (results[i].equals("Player Wins")) wins++;
            else if (results[i].equals("Computer Wins")) losses++;
            else draws++;
        }

        System.out.println("\nRound | Player Move | Computer Move | Result");
        for (int i = 0; i < rounds; i++)
            System.out.printf("%5d | %-11s | %-13s | %s%n", i + 1, players[i], computers[i], results[i]);
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", wins, losses, draws, wins * 100.0 / rounds);
        sc.close();
    }
}