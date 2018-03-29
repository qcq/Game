package game.gobang;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GoBangBoard extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Gobang gobang;
    private int row;
    private int column;
    private int squre;

    public GoBangBoard() {
        // this.setBounds(x, y, width, height);
    }

    public GoBangBoard(Gobang gobang, int row, int column) {
        this.gobang = gobang;
        this.column = column;
        this.row = row;
    }

    public int getSqure() {
        return squre;
    }

    public void paint(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        squre = (this.getWidth() - 50) / Math.max(row, column);
        /**
         * from the point (25, 10) draw the game's background.
         */
        /*
         * below draw the background gird.
         */
        int x = 25;
        int y = 10;
        gg.setStroke(
                new BasicStroke(0.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[] { 5f, 5f }, 0f));
        gg.setColor(Color.blue);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                gg.drawRect(x + j * squre, y + i * squre, squre, squre);
            }
        }

        for (int i = 0; i < gobang.getData().length; i++) {
            for (int j = 0; j < gobang.getData()[i].length; j++) {
                if (GobangColor.WHITE == gobang.getData()[i][j]) {
                    gg.setColor(Color.WHITE);
                    gg.fillOval(x + j * squre, y + i * squre, squre, squre);
                } else if (GobangColor.BLACK == gobang.getData()[i][j]) {
                    gg.setColor(Color.BLACK);
                    gg.fillOval(x + j * squre, y + i * squre, squre, squre);
                }
            }
        }

        gg.setStroke(new BasicStroke(6f));
        gg.setColor(Color.red);
        gg.drawRect(x, y, squre * 2, squre * 2);
        if (GobangColor.BLACK == gobang.getData()[0][0]) {
            gg.setColor(Color.BLACK);
        } else if (GobangColor.WHITE == gobang.getData()[0][0]) {
            gg.setColor(Color.blue);
        } else {
            ;
        }
        gg.fillOval(x, y, squre * 2, squre * 2);

        gg.setStroke(new BasicStroke(5f));
        gg.setColor(Color.blue);
        gg.drawRect(x - 1, y - 1, squre * column + 2, squre * row + 2);
        gg.dispose();

    }
}
