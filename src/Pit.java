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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Class that is used to create the pits for the view of the game board.
 */
public class Pit extends JButton implements ChangeListener {
    private int position;
    private Color pitColor;
    private Shape pitShape;

    /**
     * Constructor of the pit class.
     *
     * @param pos The position of the pit.
     * @param c   The color of the pit.
     * @param s   The shape of the pit.
     * @param d   The dimension of the pit.
     */
    public Pit(int pos, Color c, Shape s, Dimension d) {
        position = pos;
        pitShape = s;
        pitColor = c;
        setPreferredSize(d);
        setContentAreaFilled(false);
    }

    /**
     * Accessor for the position of the pit.
     *
     * @return position The position of the pit.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Paints the shape of the pit.
     */
    protected void paintComponent(Graphics g) {
        setBackground(pitColor);

        if (getModel().isArmed())
            g.setColor(Color.lightGray);
        else
            g.setColor(getBackground());

        if (pitShape.equals(new Rectangle2D.Double(0, 0, 10, 10)))
            g.fillRect(0, 0, getSize().width - 1, getSize().height - 1);
        else
            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        super.paintComponent(g);
    }

    /**
     * Paints the border of the pit.
     */
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());

        if (pitShape.equals(new Rectangle2D.Double(0, 0, 10, 10)))
            g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
        else
            g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Model m = (Model) e.getSource();
        setText(Integer.toString(m.getGameState()[position]));
    }
}