package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * in this file draw the background and the moving snake.
 */
public class SnakePanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<Place> snakeBody;
    private Place food;
    private int row;
    private int column;

    public SnakePanel(ArrayList<Place> a) {
        this.snakeBody = a;
        this.row = 20;
        this.column = 10;
    }

    public SnakePanel(ArrayList<Place> snakeBody, int row, int column) {
        this.snakeBody = snakeBody;
        this.row = row;
        this.column = column;
    }

    public void setA(ArrayList<Place> snakeBody) {
        this.snakeBody = snakeBody;
    }

    public void setFood(Place food) {
        this.food = food;
    }

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
         * below draw the snake with three color for head, body and tail.
         */
        for (int i = 0; i < snakeBody.size(); i++) {
            if (0 == i) {
                gg.setColor(Color.red);
                gg.fillRect(x + snakeBody.get(i).getY() * squre, y + snakeBody.get(i).getX() * squre, squre, squre);
            } else if (snakeBody.size() - 1 == i) {
                gg.setColor(Color.pink);
                gg.fillRect(x + snakeBody.get(i).getY() * squre, y + snakeBody.get(i).getX() * squre, squre, squre);
            } else {
                gg.setColor(Color.green);
                gg.fillRect(x + snakeBody.get(i).getY() * squre, y + snakeBody.get(i).getX() * squre, squre, squre);
            }
        }
        /*
         * draw the snake's food with green color circle shape.
         */
        if (null != food) {
            gg.setColor(Color.green);
            gg.fillOval(x + food.getY() * squre, 10 + food.getX() * squre, squre, squre);
        }
        gg.setStroke(new BasicStroke(5f));
        gg.setColor(Color.blue);
        gg.drawRect(24, 9, squre * column + 2, squre * row + 2);
        gg.dispose();
    }
}
