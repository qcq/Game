package com.game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainframe;  
    private GameBoard board;  
    private JPanel control;   
    private BorderLayout border;  
    private JButton start;  
    private JButton stop;
    private JButton exit;  
    private JButton restart;
    private int row;  
    private int column;  
    private Game game;   
    private ActionListener actionlistener;
	
	public GameGUI(String title, int row, int column){
		super(title);
		this.row = row;
		this.column = column;
		initial();  
        setSize(900, 900);  
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
        start = new JButton("Start");    
        game = new Game(this.row, this.column, 81, this);  
        board = new GameBoard(game);  
        stop = new JButton("Stop");
        exit = new JButton("Exit");
        restart = new JButton("Re-Start");
	}



	private void layOut() {
		this.getContentPane().add(mainframe);   
        mainframe.setLayout(border);  
        mainframe.add(board, BorderLayout.CENTER);   
        mainframe.add(control, BorderLayout.EAST);  
        Box ve = Box.createVerticalBox();  
        ve.add(start);  
        ve.add(Box.createVerticalStrut(50));  
        stop.setSize(start.getWidth(), start.getHeight());  
        ve.add(stop); 
        ve.add(Box.createVerticalStrut(50));
        ve.add(exit);
        ve.add(Box.createVerticalStrut(50));
        ve.add(restart);
        control.add(ve);
        
	}



	private void listener() {
        actionlistener = new ActionListener() {   
            @Override  
            public void actionPerformed(ActionEvent e) {  
                if (((JButton)(e.getSource())).getText().equals("Start")){  
                    game.setRunning(true); 
                    game.runGame();
                } else if (((JButton)(e.getSource())).getText().equals("Exit")){  
                    System.exit(0);  
                } else if (((JButton)(e.getSource())).getText().equals("Stop")){  
                	game.setRunning(false); 
                    game.runGame();
                } else if (((JButton)(e.getSource())).getText().equals("Re-Start")){  
                	game.setRunning(true);
                	game.initinalGame();
                	repaint();
                    game.runGame();
                }
            }  
        };   
        start.addActionListener(actionlistener);  
        stop.addActionListener(actionlistener); 
        restart.addActionListener(actionlistener); 
        exit.addActionListener(actionlistener);
	}



	public static void main(String[] args) {
		GameGUI game = new GameGUI("Game Of Life", 25, 25);

	}

}
