package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input command: ");
            var line = scanner.nextLine();
            var lineScanner = new Scanner(line);
            switch (lineScanner.next()) {
                case "exit" -> {
                    return;
                }
                case "start" -> {
                    if (!handleStart(lineScanner, scanner)) {
                        System.out.println("Bad parameters!");
                    }
                }
            }
        }
    }

    public static boolean handleStart(Scanner parameters, Scanner input) {
        var players = new java.util.LinkedList<IPlayer>();
        while (parameters.hasNext() && players.size() < 2) {
            switch (parameters.next()) {
                case "user" -> players.add(new Human(input));
                case "easy" -> players.add(new Easy());
                case "medium" -> players.add(new Medium());
                case "hard" -> players.add(new Hard());
                default -> {
                    return false;
                }
            }
        }
        if (players.size() != 2) {
            return false;
        }
        if (parameters.hasNext()) {
            return false;
        }
        Game.go(players.remove(), players.remove());
        return true;
    }
}

