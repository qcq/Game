package com.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.game.enmu.State;

public class GameBoard extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game;  
	private int squre;
      
    public GameBoard() {  
        //this.setBounds(x, y, width, height);  
    }  
    public GameBoard(Game game){  
        this.game = game;  
    }  
      
    public void paint(Graphics g){  
        Graphics2D gg = (Graphics2D)g;  
        squre = (this.getWidth() - 50) / Math.max(game.getRow(), game.getColumn());   
        int x = 25;  
        int y = 10;  
        gg.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_ROUND,   
                BasicStroke.JOIN_ROUND, 1.0f,new float[]{5f, 5f},0f));  
        gg.setColor(Color.blue);  
        for (int i = 0; i < game.getRow(); i++){  
            for (int j = 0; j < game.getColumn(); j++){  
                gg.drawRect(x + j * squre, y + i * squre, squre, squre);  
            }  
        }  
        for (int i = 0; i < game.getRow(); i++){  
            for (int j = 0; j < game.getColumn(); j++){  
                if (State.ALIVE == game.getData().get(i).get(j)){  
                    gg.setColor(Color.blue);  
                    gg.fillOval(x + j * squre, y + i * squre, squre, squre);  
                } else {  
                    gg.setColor(Color.WHITE);  
                    gg.fillOval(x + j * squre, y + i * squre, squre, squre);  
                } 
            }  
        }  
          
        gg.setStroke(new BasicStroke(5f));  
        gg.setColor(Color.blue);  
        gg.drawRect(x - 1, y - 1, squre * game.getColumn() + 2, squre * game.getRow() + 2);  
        gg.dispose();  
          
    }  
}
