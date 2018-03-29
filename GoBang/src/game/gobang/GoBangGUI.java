package game.gobang;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GoBangGUI extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel mainframe;
    private GoBangBoard board;
    private JPanel control;
    private BorderLayout border;
    private JButton start;
    private JButton end;
    private int row;
    private int column;
    private Gobang gobang;
    private MouseAdapter mouselistener;
    private boolean flag;
    private boolean ok;
    private ActionListener actionlistener;

    public GoBangGUI() {
        this("GoBang Game", 10, 10);
    }

    public GoBangGUI(String title, int row, int column) {
        super(title);
        this.row = row;
        this.column = column;
        this.flag = false;
        this.ok = false;
        initial();
        setSize(600, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        this.setVisible(true);
        this.requestFocus();
    }

    private void initial() {
        createComponent();
        layOut();
        listener();
    }

    private void createComponent() {
        mainframe = new JPanel();
        control = new JPanel();
        border = new BorderLayout();
        new GridLayout();
        start = new JButton("Start");
        new JLabel();
        gobang = new Gobang(row, column);
        board = new GoBangBoard(gobang, row, column);
        end = new JButton("Exit");
    }

    private void layOut() {
        this.getContentPane().add(mainframe);
        mainframe.setLayout(border);
        mainframe.add(board, BorderLayout.CENTER);
        mainframe.add(control, BorderLayout.EAST);
        Box ve = Box.createVerticalBox();
        ve.add(start);
        ve.add(Box.createVerticalStrut(50));
        end.setSize(start.getWidth(), start.getHeight());
        ve.add(end);
        control.add(ve);
    }

    /*
     * 判断鼠标落入棋盘窗格的位置索引 (x, y)为当前鼠标的坐标。
     */
    private Place judgePlace(int indexx, int indexy, int x, int y, int squre) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (x >= indexx + j * squre && x <= indexx + (j + 1) * squre && y >= indexy + i * squre
                        && y <= indexy + (i + 1) * squre) {
                    return new Place(i, j);
                }
            }
        }
        return null;
    }

    private void listener() {
        mouselistener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                /*
                 * (25, 10) is a special coordinate. the board draw begins here,
                 * when the board added to the this JFrame the begin change to
                 * (25, 10 + 25), However, I do not know why. just keep it;
                 */
                if (ok) {
                    Place chessPlace = judgePlace(25, 10, x, y, board.getSqure());
                    if (null == chessPlace) {
                    	JOptionPane.showMessageDialog(mainframe, "The place you choose is illegal place.");
                    	return;
                    }
                    // this flag used to indicate which player should be move the chess.
                    if (flag) {
                    	moveChess(chessPlace, GobangColor.WHITE, GobangColor.BLACK);
                    } else {
                    	moveChess(chessPlace, GobangColor.BLACK, GobangColor.WHITE);
                    }
                } else {
                    JOptionPane.showMessageDialog(mainframe, "please start the game");
                }
            }
        };
        actionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((JButton) (e.getSource())).getText().equals("Start")) {
                    ok = true;
                } else if (((JButton) (e.getSource())).getText().equals("Exit")) {
                    System.exit(0);
                }
            }
        };
        board.addMouseListener(mouselistener);
        start.addActionListener(actionlistener);
        end.addActionListener(actionlistener);
    }
    
    private void moveChess(Place chessPlace, GobangColor player, GobangColor nextPlayer) {
    	if (!gobang.check(chessPlace.getX(), chessPlace.getY())) {
            JOptionPane.showMessageDialog(mainframe, "Change another place, which is indicate which player should be move next.");
            return;
        } else {
            gobang.setChess(chessPlace.getX(), chessPlace.getY(), player);
            gobang.setChess(0, 0, nextPlayer);
            //repaint();
            if (gobang.isSuccess(new Place(0, 0), new Place(row - 1, column - 1), player)) {
                int choice = JOptionPane.showConfirmDialog(mainframe, "The " + player + " player is win",
                        "DO you want try again", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    gobang.initial();
                    ok = false;
                    //repaint();
                }
            }
            flag = !flag;
        }
    	repaint();
    }

    public static void main(String[] args) {
        GoBangGUI game = new GoBangGUI(null, 20, 20);
    }

}
