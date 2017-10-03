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

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * The Model class. The purpose of this class is to maintain the game state and to alert attached listeners.
 */
public class Model {
    // header
    public static final int NUM_PITS = 14;  // 0 to 13
    public static final int PIT_P1 = 0;
    public static final int MANCALA_P1 = 6;
    public static final int PIT_P2 = 7;
    public static final int MANCALA_P2 = 13;

    // trailer
    public static final int NUM_FLAGS = 5;  // 14 to 18
    public static final int GAME_FLAG = 14;
    public static final int TURN_FLAG = 15;
    public static final int MOVE_FLAG = 16;
    public static final int FREE_FLAG = 17;
    public static final int UNDO_FLAG = 18;

    private int[] gameState;
    private ArrayList<ChangeListener> listeners;

    /**
     * Construct a Model object.
     *
     * @param initVal the initial value per pit
     */
    public Model(int initVal) {
        gameState = new int[NUM_PITS + NUM_FLAGS];
        listeners = new ArrayList<ChangeListener>();

        for (int i = PIT_P1; i < MANCALA_P1; i++) {
            gameState[i] = initVal;
            gameState[i + 7] = initVal;
        }

        gameState[TURN_FLAG] = 1;
    }

    /**
     * Attach a listener to this Model.
     *
     * @param l the listener
     */
    public void attach(ChangeListener l) {
        listeners.add(l);
        l.stateChanged(new ChangeEvent(this));
    }

    /**
     * Get the game state.
     *
     * @return the game state
     */
    public int[] getGameState() {
        return gameState.clone();
    }

    /**
     * Update the game state at a given position with a given value.
     *
     * @param pos the position
     * @param val the value
     */
    public void update(int pos, int val) {
        gameState[pos] = val;
    }

    /**
     * Alert attached listeners of this Model.
     */
    public void alert() {
        for (ChangeListener l : listeners)
            l.stateChanged(new ChangeEvent(this));
    }
}