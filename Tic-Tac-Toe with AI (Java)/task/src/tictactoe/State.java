package tictactoe;

import java.util.Arrays;
import java.util.stream.IntStream;

class State {
    final public static int[] lines = {0b111_000_000, 0b000_111_000, 0b000_000_111, 0b100_100_100, 0b010_010_010, 0b001_001_001, 0b100_010_001, 0b001_010_100,};
    final public static int[] positions = {0b100_000_000, 0b010_000_000, 0b001_000_000, 0b000_100_000, 0b000_010_000, 0b000_001_000, 0b000_000_100, 0b000_000_010, 0b000_000_001,};
    private int player1 = 0;
    private int player2 = 0;

    public boolean firstPlayerTurn = true;

    public int getMe() {
        return firstPlayerTurn ? player1 : player2;
    }

    public int getOpponent() {
        return firstPlayerTurn ? player2 : player1;
    }

    public void print() {
        var pp = Arrays.stream(positions).map(p -> (player1 & p) != 0 ? 'X' : (player2 & p) != 0 ? 'O' : '_').boxed().toArray();
        System.out.format("---------\n" + "| %c %c %c |\n" + "| %c %c %c |\n" + "| %c %c %c |\n" + "---------\n", pp);
        var allowedMoves = IntStream.range(0, 9).filter(i -> isMoveAllowed(State.positions[i])).toArray();
        System.out.format("Player X: %3o\nPlayer O: %3o\nAllowed : %s\n", player1, player2, Arrays.toString(allowedMoves));
    }


    public static boolean isWinning(int player) {
        return Arrays.stream(lines).anyMatch(line -> (line & player) == line);
    }

    public State tryMove(int move) {
        var x = new State();
        x.player1 = player1;
        x.player2 = player2;
        x.firstPlayerTurn = firstPlayerTurn;
        x.move(move);
        return x;
    }

    public void move(int move) {
        if (!isMoveAllowed(move)) {
            throw new RuntimeException(String.format("Illegal move attempted: %3o", move));
        }
        if (firstPlayerTurn) {
            player1 |= move;
        } else {
            player2 |= move;
        }
        firstPlayerTurn = !firstPlayerTurn;
    }

    public int[] allowedMoves() {
        return Arrays.stream(positions).filter(this::isMoveAllowed).toArray();
    }

    public boolean isMoveAllowed(int position) {
        return ((player1 | player2) & position) == 0;
    }

    public boolean isFinished() {
        return (player1 | player2) == 0b111_111_111 || Arrays.stream(lines).anyMatch(line -> ((player1 & line) == line || (player2 & line) == line));
    }

    public void printResult() {
        if (isWinning(player1)) {
            System.out.println("X wins");
        } else if (isWinning(player2)) {
            System.out.println("O wins");
        } else if ((player1 | player2) == 0b111111111) {
            System.out.println("Draw");
        }
    }
}