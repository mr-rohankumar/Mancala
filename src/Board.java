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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that will create the UI elements of game board and to attach listeners to the UI elements.
 */
public class Board {
    /**
     * Constructor of the board.
     *
     * @param style The style that is used for the view design of the game board.
     * @param num   The number of initial stones per pit.
     */
    Board(BoardStyle style, int num) {
        final Model m = new Model(num);
        final Logic l = new Logic(m);

        JLabel topLabel = new JLabel("        B               B6             B5           B4            B3            B2           B1");
        JLabel bottomLabel = new JLabel("                          A1           A2            A3            A4            A5          A6               A");

        JPanel firstRow = new JPanel();
        firstRow.setLayout(new GridLayout(1, 6));

        JPanel firstMancala = new JPanel();

        JPanel secondRow = new JPanel();
        secondRow.setLayout(new GridLayout(1, 6));

        JPanel secondMancala = new JPanel();

        ActionListener turn = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pit p = (Pit) e.getSource();
                l.takeTurn(p.getPosition());
            }
        };

        Pit p;

        for (int i = 0; i < Model.MANCALA_P1; i++) {
            p = new Pit(i, style.getPitColor(), style.getPitShape(), style.getDimension());
            p.addActionListener(turn);
            m.attach(p);
            secondRow.add(p);
        }

        p = new Pit(Model.MANCALA_P1, style.getPitColor(), style.getPitShape(), new Dimension(50, 100));
        p.setEnabled(false);
        firstMancala.add(p);
        m.attach(p);

        for (int i = Model.MANCALA_P2 - 1; i > Model.MANCALA_P1; i--) {
            p = new Pit(i, style.getPitColor(), style.getPitShape(), style.getDimension());
            p.addActionListener(turn);
            m.attach(p);
            firstRow.add(p);
        }

        p = new Pit(Model.MANCALA_P2, style.getPitColor(), style.getPitShape(), new Dimension(50, 100));
        p.setEnabled(false);
        secondMancala.add(p);
        m.attach(p);

        ActionListener undo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                l.undoTurn();
            }
        };

        Undo u = new Undo();
        u.addActionListener(undo);
        m.attach(u);
        firstMancala.add(u);

        ActionListener end = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                l.endTurn();
                int win = m.getGameState()[Model.GAME_FLAG];
                if (win != 0)
                    gameOver(win);
            }
        };

        End e = new End();
        e.addActionListener(end);
        m.attach(e);
        firstMancala.add(e);

        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new GridLayout(2, 1));
        rowPanel.add(firstRow);
        rowPanel.add(secondRow);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(secondMancala, BorderLayout.WEST);
        mainPanel.add(rowPanel, BorderLayout.CENTER);
        mainPanel.add(firstMancala, BorderLayout.EAST);

        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(topLabel, BorderLayout.NORTH);
        f.add(mainPanel, BorderLayout.CENTER);
        f.add(bottomLabel, BorderLayout.SOUTH);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(style.getBoardColor());
        f.pack();
        f.setVisible(true);
    }

    /**
     * Creates the GAME OVER notification screen.
     */
    private void gameOver(int win) {
        // Added Game Over Screen that appears when one side of board is empty
        JPanel p = new JPanel();

        String msg = "";
        if (win == 1)
            msg = "Congratulations! Player A is the winner.";
        else if (win == 2)
            msg = "Congratulations! Player B is the winner.";
        else
            msg = "The game is a draw!";

        JLabel label = new JLabel(msg);
        p.add(label);

        String[] options = {"Exit"};
        int selectedOption = JOptionPane.showOptionDialog(null, p, "GAME OVER!",
                JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selectedOption == 0)
            System.exit(0);
    }
}