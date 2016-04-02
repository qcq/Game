package com.game.test;

import static org.junit.Assert.*;

//import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.game.Game;
import com.game.enmu.State;

public class TestGame {

	private Game mockGame;
	private Game game;


	@Before
	public void setUp() throws Exception {
		//mockGame = EasyMock.createMock(Game.class);
		//mockGame.initinalGame();
		game = new Game(5, 5, 9);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void AliveCellMoreThanThreeNeibourShouldBeDie() {
		State result = game.nextGenerateState(State.ALIVE, 3 + 1);
		assertEquals(State.DIE, result);
	}
	
	@Test
	public void AliveCellLessThanOneNeibourShouldBeDie() {
		State result = game.nextGenerateState(State.ALIVE, 1);
		assertEquals(State.DIE, result);
	}
	
	@Test
	public void AliveCellWithTwoOrThreeNeibourShouldBeAlive() {
		State result = game.nextGenerateState(State.ALIVE, 2);
		assertEquals(State.ALIVE, result);
		result = game.nextGenerateState(State.ALIVE, 3);
		assertEquals(State.ALIVE, result);
	}
	
	@Test
	public void DieCellWithThreeNeibourShouldBeAlive() {
		State result = game.nextGenerateState(State.DIE, 3);
		assertEquals(State.ALIVE, result);
	}

}
