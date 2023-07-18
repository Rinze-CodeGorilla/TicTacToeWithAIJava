package tictactoe;

import java.util.Scanner;

public class Human implements IPlayer {
    private final Scanner scanner;

    public Human(Scanner scanner) {
        this.scanner = scanner;
    }

    public int move(State state) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            try {
                var command = scanner.nextLine();
                var lineScanner = new Scanner(command);
                int row = lineScanner.nextInt();
                int col = lineScanner.nextInt();
                lineScanner.close();

                if (row < 1 || row > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                if (col < 1 || col > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                var position = (row - 1) * 3 + col - 1;
                var move = State.positions[position];
                if (!state.isMoveAllowed(move)) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
                return move;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }
}
