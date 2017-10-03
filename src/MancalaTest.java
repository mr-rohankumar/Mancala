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

/**
 * Tester class. This class contains the main method that will take user's choices
 * and call the board class to create a board based on the user's specifications.
 */
public class MancalaTest {
    public static void main(String[] args) {
        JPanel p = new JPanel(new BorderLayout(0, 10));

        JLabel topLabel = new JLabel("Please select board style: ");
        p.add(topLabel, BorderLayout.NORTH);

        String[] styles = {"Christmas", "Halloween"};
        JComboBox styleList = new JComboBox(styles);
        p.add(styleList, BorderLayout.CENTER);

        JLabel bottomLabel = new JLabel("Please select number of stones per pit: ");
        p.add(bottomLabel, BorderLayout.SOUTH);

        String[] options = {"3", "4"};
        int selectedOption = JOptionPane.showOptionDialog(null, p, "Mancala", JOptionPane.NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

        Board b = new Board(styleList.getSelectedItem().equals("Christmas") ? new ChristmasStyle() : new HalloweenStyle(), selectedOption == 0 ? 3 : 4);
    }
}
