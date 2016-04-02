package com.game;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import com.game.enmu.State;

public class Game {
	private int row;
	private int column;
	private int numberRand;
	private boolean running;
	private Random rand;
	private ArrayList<ArrayList<State>> data;
	private ArrayList<CellStateOfSpecificPlace> back_data;
	private JFrame parent;
	private Thread generate;
	
	public Game(int row, int column, int numberRand){
		this.row = row;
		this.column = column;
		this.numberRand = numberRand;
		this.running = true;
		this.data = new ArrayList<ArrayList<State>>();
		rand = new Random();
		back_data = new ArrayList<CellStateOfSpecificPlace>();
		initinalGame();
		//runGame();
	}
	
	public Game(int row, int column, int numberRand, GameGUI gameGUI) {
		this(row, column, numberRand);
		parent = gameGUI;
	}
	
	/*
	 * initial the game with the specific policy.
	 * */
	public void initinalGame(){
		data.clear();
		for (int rowIndex = 0; rowIndex < row; rowIndex++){
			ArrayList<State> temp = new ArrayList<State>();
			for (int columnIndex = 0; columnIndex < column; columnIndex++){
				temp.add(State.DIE);
			}
			data.add(temp);
		}
		initialPolicy();
	}
	
	/*
	 * Below code is one kind of initial policy of Game Of Life, which you can 
	 * modified by yourself.
	 * */
	public void initialPolicy(){
		int margin = (int)Math.sqrt(numberRand);
		int rowPlace = rand.nextInt(row - margin - 1) + 1;
		int columnPlace = rand.nextInt(column - margin - 1) + 1;
		for (int rowIndex = rowPlace; rowIndex < rowPlace + margin; rowIndex++){
			for (int columnIndex = columnPlace; columnIndex < columnPlace + margin; columnIndex++){
				if (rand.nextBoolean()){
					data.get(rowIndex).set(columnIndex, State.ALIVE);
				} 
			}
		}
	}
	
	/*
	 * Below method run the code in a thread named generate.
	 * */
	public void runGame(){
		if (false == running){
			if (generate.isAlive()){
				generate.interrupt();
				generate = null;
			}
		} else {
			if (null == generate){
				generate = new Thread(new Runnable() {
					@Override
					public void run() {
						while(!Thread.interrupted()){
							//printGame();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
								Thread.currentThread().stop();
							}
							generated();
							if (null != parent){
								parent.repaint();
							}
						}
					}
				});
				generate.start();
			}
		}
	}
	
	/*
	 * generated method to generate the next generation based on current generation.
	 * */
	private void generated() {
		int neighbours = 0;
		State currentCellState = State.DIE;
		State nextCellState = State.DIE;
		back_data.clear();
		for (int rowIndex = 1; rowIndex < row - 1; rowIndex++){
			for (int columnIndex = 1; columnIndex < column - 1; columnIndex++){
				neighbours = getNeighborNumbers(rowIndex, columnIndex);
				currentCellState = data.get(rowIndex).get(columnIndex);
				nextCellState = nextGenerateState(currentCellState, neighbours);
				if (nextCellState != currentCellState){
					back_data.add(new CellStateOfSpecificPlace(rowIndex, columnIndex, nextCellState));
				}
			}
		}
		for (CellStateOfSpecificPlace item:back_data){
			data.get(item.getRowIndex()).set(item.getColumnIndex(), item.getState());
		}
	}
	
	/*
	 * Below method get the cell's neighbor number.
	 * */
	private int getNeighborNumbers(int rowIndex, int columnIndex){
		int counter = 0;
		for (int i = rowIndex - 1; i <= rowIndex + 1; i++){
			for (int j = columnIndex - 1; j <= columnIndex + 1; j++){
				if (State.ALIVE == data.get(i).get(j)){
					counter++;
				}
			}
		}
		counter += State.ALIVE == data.get(rowIndex).get(columnIndex) ? -1:0;
		return counter;
	}
	
	/*
	 * below code to debug the state of the game.
	 * */
	private void printGame(){
		System.out.println("-------------------");
		for (ArrayList<State> rowData : data){
			for (State ColumnData : rowData){
				System.out.print(ColumnData + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------");
	}
	
	/*
	 * judge whether the next generation of cell should be alive.
	 * */
	public State nextGenerateState(State alive, int neighbourNumber) {
		if (State.ALIVE == alive && (2 == neighbourNumber || 3 == neighbourNumber)){
			return State.ALIVE;
		}
		if (State.DIE == alive && 3 == neighbourNumber){
			return State.ALIVE;
		}
		return State.DIE;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public ArrayList<ArrayList<State>> getData() {
		return data;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public static void main(String[] args){
		Game game = new Game(15, 15, 25);
	}

}
