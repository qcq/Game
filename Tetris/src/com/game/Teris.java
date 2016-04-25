package com.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.game.shape.Shape;
import com.game.state.ShapeEnum;
import com.game.util.Enums;
import com.game.util.SameRow;
import com.game.util.Utils;

public class Teris extends JFrame {

    /**
     * This is the main GUI of Teris, which the code executed.
     */
    private static final long serialVersionUID = 1L;
    private int row;
    private int column;
    private JPanel mainframe;
    private JPanel control;
    private BorderLayout border;
    private FlowLayout flow;
    private JButton start;
    private JButton exit;
    private TerisPanel board;
    private List<Point> data;
    private ActionListener actionlistener;
    private KeyAdapter keylistener;
    private Timer time;
    private Timer updater;
    private int speed;
    private JTextField score;
    private Shape shape;
    private int limitTop;
    private SameRow sameRow;

    public Teris(String title, int row, int column) {
        super(title);
        this.row = row;
        this.column = column;
        initial();
        setSize(300, (int) (300 * this.row * 1.0 / this.column));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        this.setVisible(true);
        this.requestFocus();
    }

    private void initial() {
        data = new ArrayList<Point>();
        speed = 1000;
        limitTop = row;
        sameRow = new SameRow();
        createComponent();
        loadShape();
        layOut();
        listeners();
    }

    private void createComponent() {
        start = new JButton("Start");
        exit = new JButton("Exit");
        mainframe = new JPanel();
        control = new JPanel();
        border = new BorderLayout();
        board = new TerisPanel(data, row, column);
        flow = new FlowLayout();
        score = new JTextField();
        score.setEditable(false);
    }

    private void layOut() {
        getContentPane().add(mainframe);
        mainframe.setLayout(border);
        mainframe.add(control, BorderLayout.NORTH);
        control.setLayout(flow);
        control.add(start);
        control.add(score);
        control.add(exit);
        score.setPreferredSize(new Dimension(70, 30));
        score.setText("0");
        mainframe.add(board, BorderLayout.CENTER);
    }

    private void loadShape() {
        /*
         * for debug reason.
         */
        shape = Utils.makeShape(Enums.random(ShapeEnum.class), row, column);
        // shape = Utils.makeShape(ShapeEnum.TRIANGLE, row, column);
        int shiftToCenter = column / 2 - shape.getCenterOfShape();
        for (int i = 0; i < shiftToCenter; i++) {
            shape.ShiftRight();
        }
        board.setBasePoint(shape.getData().get(2));
        data.addAll(shape.getData());
    }

    private void listeners() {
        /*
         * action listener for the button of start)(for start) and end(for exit)
         */
        actionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("Exit")) {
                    System.exit(0);
                } else if (command.equals("Start")) {
                    time.start();
                    updater.start();
                    requestFocus();
                }
            }
        };
        /*
         * The keyboard listener to direction key(the key in here is you can not
         * get the reverse direction) and speed key(w for high speed, s for low
         * speed).
         */
        keylistener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_LEFT) {
                    shiftLeft();
                } else if (code == KeyEvent.VK_RIGHT) {
                    shiftRight();
                } else if (code == KeyEvent.VK_UP) {
                    speed += 100;
                    time.setDelay(speed);
                    System.out.println(speed);
                } else if (code == KeyEvent.VK_DOWN) {
                    speed /= 2;
                    speed = 0 == speed ? 10 : speed;
                    time.setDelay(speed);
                    System.out.println(speed);
                } else if (code == KeyEvent.VK_SPACE) {
                    changeShape();
                } else {
                    System.out.println("undefine for this key!");
                }
            }
        };
        /**
         * here set the timer to the speed of 1 grid/sec
         */
        time = new Timer(speed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
            }
        });
        updater = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        this.addKeyListener(keylistener);
        start.addActionListener(actionlistener);
        exit.addActionListener(actionlistener);
    }

    protected boolean shiftLeft() {
        shape.ShiftLeft();
        boolean canShiftLeft = !Utils.hasSamePoint(data);
        if (!canShiftLeft) {
            shape.ShiftRight();
        }
        return canShiftLeft;
    }

    protected boolean shiftRight() {
        shape.ShiftRight();
        boolean canShiftRight = !Utils.hasSamePoint(data);
        if (!canShiftRight) {
            shape.ShiftLeft();
        }
        return canShiftRight;
    }

    protected boolean changeShape() {
        try {
            shape.changeShape();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean canChangeShape = !Utils.hasSamePoint(data);
        for (Point item : shape.getData()) {
            if (item.x >= row || item.y >= column || item.x < 0 || item.y < 0) {
                canChangeShape = false;
                System.out.println("Care");
                break;
            }
        }
        if (!canChangeShape) {
            try {
                shape.unChangeShape();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return canChangeShape;
    }

    private void move() {
        if (isAlive()) {
            if (isMoveFinished()) {
                updateLimitTop();
                updateScore(); // 消方块
                loadShape();
                speed = 1000;
                time.setDelay(speed);
            } else {
                shape.ShiftDown();
            }
        } else {
            time.stop();
            updater.stop();
            // JOptionPane.showMessageDialog(this, "You Lose! Come on!");
            int choice = JOptionPane.showConfirmDialog(this, "try again!", "Message", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.YES_OPTION == choice) {
                /**
                 * ready for the restart the game.
                 */
                score.setText("0");
            }
        }
    }

    /*
     * This method should check whether the game can go on. same with
     * canShiftDown function.
     */
    private boolean isAlive() {
        return limitTop > 0;
    }

    /*
     * This method will check whether single move is overd. no data overlapped.
     */
    private boolean isMoveFinished() {
        boolean flag = false;
        if (shape.ShiftDown()) {
            flag = Utils.hasSamePoint(data);
            shape.ShiftUp();
        } else {
            flag = true;
        }
        return flag;
    }

    private void updateLimitTop() {
        for (Point item : data) {
            limitTop = Math.min(limitTop, item.x);
        }
    }

    private void updateScore() {
        int counter = 0;
        int collepsedRowNumber = 0;
        for (int i = row - 1; i >= 0; i--) {
            for (Point item : data) {
                if (i == item.x) {
                    counter++;
                }
            }
            if (column == counter) {
                collepsedRowNumber++;
                sameRow.setRow(i);
                data.removeIf(sameRow);
                moveTopToBelow(i);
                /*
                 * here need to Re-scanning the row of i to be sure whether
                 * there is exist another row can collapsed.
                 */
                i++;
            }
            counter = 0;
        }
        score.setText(String.valueOf(Integer.valueOf(score.getText()) + scoreFunction(collepsedRowNumber)));
    }

    private void moveTopToBelow(int i) {
        for (Point item : data) {
            if (item.x < i) {
                item.x++;
            }
        }
    }

    /*
     * 可以同时依据消掉的行数来使用不同的加分策略。
     */
    private int scoreFunction(int collepsedRowNumber) {
        return collepsedRowNumber * collepsedRowNumber;
    }

    public static void main(String[] args) {
        new Teris("test", 30, 15);
    }

}
