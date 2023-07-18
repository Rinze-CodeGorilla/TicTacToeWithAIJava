package tictactoe;

public class Game {
    public static void go(IPlayer p1, IPlayer p2) {
        State state = new State();
        state.print();
        while (!state.isFinished()) {
            if (state.firstPlayerTurn) {
                state.move(p1.move(state));
            } else {
                state.move(p2.move(state));
            }
            state.print();
        }
        state.printResult();
    }
}
