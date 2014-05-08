package se.chalmers.TDA367.group13.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameStateTest {

	GameState gs = new GameState();
	@Test
	public void testGetID() {
		assertTrue(gs.getID() == 3);
	}

}
