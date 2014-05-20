package se.chalmers.TDA367.group13.stateTests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.TDA367.group13.controller.GameState;

public class GameStateTest {

	GameState gs = new GameState();
	@Test
	public void testGetID() {
		assertTrue(gs.getID() == 3);
	}

}
