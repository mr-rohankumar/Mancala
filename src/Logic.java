/*
 MIT License

 Copyright (c) 2017 Rohan Kumar

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

/**
 * The Logic class. The purpose of this class is to control the Model through commands defined by the game rules.
 */
public class Logic {
    public static final int UNDO_LIMIT = 3;

    private final Model model;
    private int[] currBoard;
    private int[] prevBoard;

    /**
     * Construct a Logic object.
     *
     * @param m the model to control
     */
    public Logic(Model m) {
        model = m;
        currBoard = model.getGameState();
    }

    /**
     * Execute the take turn command at a given pit position.
     *
     * @param pos the pit position
     */
    public void takeTurn(int pos) {
        if (currBoard[Model.MOVE_FLAG] == 1 || !valid(pos) || currBoard[pos] == 0)
            return;

        prevBoard = model.getGameState();

        currBoard[pos] = 0;
        model.update(pos, 0);
        for (int i = prevBoard[pos]; i > 0; i--) {
            pos++;
            if (currBoard[Model.TURN_FLAG] == 1 && pos == Model.MANCALA_P2 ||
                    currBoard[Model.TURN_FLAG] == 2 && pos == Model.MANCALA_P1)
                pos++;
            pos %= Model.NUM_PITS;
            model.update(pos, ++currBoard[pos]);
        }

        last(pos);

        model.update(Model.MOVE_FLAG, 1);

        commit();
    }

    /**
     * Execute the undo turn command.
     */
    public void undoTurn() {
        if (currBoard[Model.MOVE_FLAG] == 0 || currBoard[Model.UNDO_FLAG] == UNDO_LIMIT)
            return;

        for (int i = 0; i < prevBoard.length; i++)
            model.update(i, prevBoard[i]);

        model.update(Model.UNDO_FLAG, currBoard[Model.UNDO_FLAG] + 1);

        commit();
    }

    /**
     * Execute the end turn command.
     */
    public void endTurn() {
        if (currBoard[Model.MOVE_FLAG] == 0)
            return;

        win();

        if (currBoard[Model.FREE_FLAG] == 0)
            model.update(Model.TURN_FLAG, currBoard[Model.TURN_FLAG] == 1 ? 2 : 1);
        model.update(Model.MOVE_FLAG, 0);
        model.update(Model.FREE_FLAG, 0);
        model.update(Model.UNDO_FLAG, 0);

        commit();
    }

    private boolean valid(int pos) {
        if (currBoard[Model.TURN_FLAG] == 1)
            return Model.PIT_P1 <= pos && pos < Model.MANCALA_P1;
        return Model.PIT_P2 <= pos && pos < Model.MANCALA_P2;
    }

    private void last(int pos) {
        if (pos == Model.MANCALA_P1 || pos == Model.MANCALA_P2)
            model.update(Model.FREE_FLAG, 1);

        else if (valid(pos) && prevBoard[pos] == 0) {
            if (currBoard[Model.TURN_FLAG] == 1) {
                pos += 7;
                model.update(Model.MANCALA_P1, currBoard[Model.MANCALA_P1] + currBoard[pos]);
            } else {
                pos -= 7;
                model.update(Model.MANCALA_P2, currBoard[Model.MANCALA_P2] + currBoard[pos]);
            }
            model.update(pos, 0);
        }
    }

    private void win() {
        boolean p1 = true, p2 = true;

        for (int i = Model.PIT_P1; i < Model.MANCALA_P1; i++) {
            if (currBoard[i] != 0)
                p1 = false;
            if (currBoard[i + 7] != 0)
                p2 = false;
        }

        if (p1 || p2) {
            int i = currBoard[Model.MANCALA_P1] - currBoard[Model.MANCALA_P2];
            model.update(Model.GAME_FLAG, i > 0 ? 1 : i < 0 ? 2 : 3);
        }
    }

    private void commit() {
        currBoard = model.getGameState();
        model.alert();
    }
}