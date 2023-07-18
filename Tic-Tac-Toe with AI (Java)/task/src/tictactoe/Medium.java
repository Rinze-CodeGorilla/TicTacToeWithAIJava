package tictactoe;

import java.util.Arrays;
import java.util.Random;

public class Medium implements IPlayer {
    Random random = new Random();

    @Override
    public int move(State state) {
        System.out.println("Making move level \"medium\"");
        var me = state.getMe();
        var opp = state.getOpponent();
        var allowedMoves = state.allowedMoves();
        return Arrays.stream(allowedMoves)
                .filter(move -> State.isWinning(me | move))
                .findAny()
                .orElse(
                        Arrays.stream(allowedMoves)
                                .filter(move -> State.isWinning(opp | move))
                                .findAny()
                                .orElse(
                                        allowedMoves[random.nextInt(0, allowedMoves.length)]
                                )
                );
    }
}
