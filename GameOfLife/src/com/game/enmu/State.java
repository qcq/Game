package com.game.enmu;

public enum State {
	DIE,
	ALIVE;
	public String toString(){
		return name().substring(0, 1);
	}
}
