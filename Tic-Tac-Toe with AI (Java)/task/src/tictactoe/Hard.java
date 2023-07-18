package tictactoe;

import java.util.Arrays;

public class Hard implements IPlayer {

    @Override
    public int move(State state) {
        System.out.println("Making move level \"hard\"");
        var max = Integer.MIN_VALUE;
        var move = 0;
        for (int allowedMove : state.allowedMoves()) {
            var minmax = minmax(state, allowedMove);
            if (minmax > max) {
                max = minmax;
                move = allowedMove;
            }
        }
        return move;
    }

    int minmax(State currentState, int move) {
        var state = currentState.tryMove(move);
        if (State.isWinning(state.getOpponent())) return 1;
        if (state.isFinished()) return 0;
        return Arrays.stream(state.allowedMoves()).map(allowedMove -> -minmax(state, allowedMove)).min().orElseThrow();
    }
}
