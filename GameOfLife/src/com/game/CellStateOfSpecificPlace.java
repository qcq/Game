package com.game;

import com.game.enmu.State;

public class CellStateOfSpecificPlace {
	private int rowIndex;
	private int columnIndex;
	private State state;
	
	public CellStateOfSpecificPlace() {
		this(0, 0, State.DIE);
	}

	public CellStateOfSpecificPlace(int rowIndex, int columnIndex, State state) {
		super();
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.state = state;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	
}
