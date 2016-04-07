package com.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

/**
 * in this file draw the background and the moving snake.
 */
public class TerisPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int row;
    private int column;
    private List<Point> data;
    private Point basePoint;

    public TerisPanel() {
        this(null, 20, 10);
    }

    public TerisPanel(List<Point> data, int row, int column) {
        if (data == null) {
            try {
                throw new Exception("The List<Point> should not be null, which can be empty.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.data = data;
        this.row = row;
        this.column = column;
    }

    public void setBasePoint(Point point) {
        this.basePoint = point;
    }

    /*
     * 此处有一个坐标系的问题，在Teris这个类实例化采用row*column这种形式，也就是行*列的模式， 会导致一个问题：和java
     * swing内置的作图坐标系相违背，原则上是横着的是X坐标轴，竖着的是y坐标轴， 但是采用上述的关系之后，需要在使用java
     * swing作图的时候将x,y坐标轴进行调换。
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        int squre = (this.getWidth() - 50) / column;
        /**
         * from the point (25, 10) draw the game's background.
         */
        /*
         * below draw the background gird.
         */
        int x = 25;
        int y = 10;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                gg.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
                        new float[] { 5f, 5f }, 0f));
                gg.setColor(Color.yellow);
                gg.drawRect(x + j * squre, y + i * squre, squre, squre);
            }
        }

        /*
         * draw the russia squre which meaningful to game.
         */
        for (Point item : data) {
            gg.setColor(Color.GREEN);
            gg.fillRect(x + item.y * squre, y + item.x * squre, squre, squre);
        }

        /*
         * draw the base line which can predict the place.
         */
        if (null != basePoint) {
            gg.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
                    new float[] { 5f, 5f }, 0f));
            gg.setColor(Color.red);
            gg.drawLine(x + basePoint.y * squre, y, x + basePoint.y * squre, y + row * squre);
        }

        gg.setStroke(new BasicStroke(5f));
        gg.setColor(Color.blue);
        gg.drawRect(24, 9, squre * column + 2, squre * row + 2);
        gg.dispose();
    }
}