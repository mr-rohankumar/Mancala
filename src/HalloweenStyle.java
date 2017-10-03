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

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Concrete strategy class
 */
public class HalloweenStyle implements BoardStyle {
    /**
     * Gets the color of the board
     */
    @Override
    public Color getBoardColor() {
        return Color.ORANGE;
    }

    /**
     * Gets the color of the pit
     */
    @Override
    public Color getPitColor() {
        return Color.MAGENTA;
    }

    /**
     * Gets the shape of the pit
     */
    @Override
    public Shape getPitShape() {
        return new Ellipse2D.Double(0, 0, 10, 10);
    }

    /**
     * Gets the pit dimension
     */
    @Override
    public Dimension getDimension() {
        return new Dimension(50, 50);
    }

    /**
     * Draws the shape of the pit
     */
    @Override
    public void customDraw(Graphics g, int width, int height) {
        g.drawOval(0, 0, width, height);
    }

    /**
     * Fills the shape of the pit
     */
    @Override
    public void customFill(Graphics g, int width, int height) {
        g.fillOval(0, 0, width, height);
    }
}