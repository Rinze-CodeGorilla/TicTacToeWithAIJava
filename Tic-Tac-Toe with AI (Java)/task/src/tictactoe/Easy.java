package tictactoe;

import java.util.Random;

public class Easy implements IPlayer {
    Random random = new Random();

    @Override
    public int move(State state) {
        System.out.println("Making move level \"easy\"");
        var allowedMoves = state.allowedMoves();
        return allowedMoves[random.nextInt(0, allowedMoves.length)];
    }
}
