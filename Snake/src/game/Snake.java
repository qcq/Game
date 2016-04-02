package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Snake extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JButton start;
    private JButton exit;
    private JPanel mainframe;
    private JPanel control;
    private SnakePanel board;
    private BorderLayout border;
    private FlowLayout flow;
    private int row;
    private int column;
    private Direction direction;
    private ActionListener actionlistener;
    private KeyAdapter keylistener;
    private Timer time;
    private Random rand;
    private ArrayList<Place> snakeBody;
    private Place food;
    private boolean caneat;
    private ActionListener timelistener;
    private int speed;
    private JTextField score;

    /**
     * default set the board to 20 * 10
     */
    public Snake() {
        this("Snake", 20, 10);
    }

    public Snake(String title, int row, int column) {
        super(title);
        // this.setUndecorated(true);
        this.row = row;
        this.column = column;
        initial(row, column);
        // this.pack();
        setSize(300, (int) (300 * this.row * 1.0 / this.column));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        this.setVisible(true);
        this.requestFocus();
    }

    private void initial(int row, int column) {
        snakeBody = new ArrayList<Place>();
        /**
         * initial the snake body! to length 3
         */
        loadSnakeBody();
        direction = Direction.UP;
        speed = 1000;
        caneat = false;
        createComponent();
        layOut();
        listener();
        food();
    }

    private void loadSnakeBody() {
        snakeBody.clear();
        snakeBody.add(new Place(row / 2 - 1, column / 2));
        snakeBody.add(new Place(row / 2, column / 2));
        snakeBody.add(new Place(row / 2 + 1, column / 2));
    }

    /**
     * create the component which used in Snake.
     */
    private void createComponent() {
        start = new JButton("Start");
        exit = new JButton("Exit");
        mainframe = new JPanel();
        control = new JPanel();
        border = new BorderLayout();
        board = new SnakePanel(snakeBody, row, column);
        flow = new FlowLayout();
        rand = new Random();
        food = new Place(-1, -1);
        board.setFood(food);
        score = new JTextField();
        score.setEditable(false);
    }

    /**
     * The Snake game's layout.
     */
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

    /**
     * register all listeners to the snake game.
     */
    private void listener() {
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
                if (code == KeyEvent.VK_LEFT && direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                } else if (code == KeyEvent.VK_RIGHT && direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                } else if (code == KeyEvent.VK_UP && direction != Direction.DOWN) {
                    direction = Direction.UP;
                } else if (code == KeyEvent.VK_DOWN && direction != Direction.UP) {
                    direction = Direction.DOWN;
                } else if (code == KeyEvent.VK_W) {
                    speed -= 100;
                    time.setDelay(speed);
                    System.out.println(speed);
                } else if (code == KeyEvent.VK_S) {
                    speed += 100;
                    time.setDelay(speed);
                    System.out.println(speed);
                }
            }
        };
        timelistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
                repaint();
            }
        };
        /**
         * here set the timer to the speed of 1 grid/sec
         */
        time = new Timer(speed, timelistener);
        this.addKeyListener(keylistener);
        start.addActionListener(actionlistener);
        exit.addActionListener(actionlistener);
    }

    /*
     * used for checking the validity of the place where food come out. insure
     * the food not come out above the snake's body.
     */
    private boolean isMoveValid(int x, int y) {
        boolean flag = true;
        for (Place a : snakeBody) {
            if (a.getX() == x && a.getY() == y) {
                flag = false;
            }
        }
        return flag;
    }

    /*
     * come out the food
     */
    private void food() {
        int food_row = rand.nextInt(row);
        int food_column = rand.nextInt(column);
        while (!isMoveValid(food_row, food_column)) {
            food_row = rand.nextInt(row);
            food_column = rand.nextInt(column);
        }
        food.setX(food_row);
        food.setY(food_column);
    }

    /*
     * judge whether the snake is alive now!
     */
    private boolean isAlive() {
        if (snakeBody.get(0).getX() >= 0 && snakeBody.get(0).getX() < row && snakeBody.get(0).getY() >= 0
                && snakeBody.get(0).getY() < column && checkBody()) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * check the snake's head whether or not touch its body!
     */
    private boolean checkBody() {
        for (int i = 4; i < snakeBody.size(); i++) {
            if (snakeBody.get(0).equals(snakeBody.get(i))) {
                return false;
            }
        }
        return true;
    }

    /*
     * snake's body moves forward without head which with special considering.
     */
    private void dealWithBody() {
        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).setX(snakeBody.get(i - 1).getX());
            snakeBody.get(i).setY(snakeBody.get(i - 1).getY());
        }
    }

    /**
     * The Snake's actual move.
     */
    private void move() {
        Place oldhead = new Place(snakeBody.get(0).getX(), snakeBody.get(0).getY());
        if (isAlive()) {
            caneat = food.equals(snakeBody.get(snakeBody.size() - 1));
            dealWithBody();
            if (direction == Direction.UP) {
                snakeBody.get(0).setX(oldhead.getX() - 1);
                snakeBody.get(0).setY(oldhead.getY());
            } else if (direction == Direction.DOWN) {
                snakeBody.get(0).setX(oldhead.getX() + 1);
                snakeBody.get(0).setY(oldhead.getY());
            } else if (direction == Direction.LEFT) {
                snakeBody.get(0).setX(oldhead.getX());
                snakeBody.get(0).setY(oldhead.getY() - 1);
            } else if (direction == Direction.RIGHT) {
                snakeBody.get(0).setX(oldhead.getX());
                snakeBody.get(0).setY(oldhead.getY() + 1);
            }
            if (caneat) {
                snakeBody.add(new Place(food.getX(), food.getY()));
                score.setText(String.valueOf(Integer.valueOf(score.getText()) + 1));
                food();
            }
        } else {
            time.stop();
            // JOptionPane.showMessageDialog(this, "You Lose! Come on!");
            int choice = JOptionPane.showConfirmDialog(this, "try again!", "Message", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.YES_OPTION == choice) {
                /**
                 * ready for the restart the game.
                 */
                loadSnakeBody();
                score.setText("0");
            }
        }
    }

    public static void main(String[] args) {
        Snake test = new Snake("test", 30, 15);
    }

}