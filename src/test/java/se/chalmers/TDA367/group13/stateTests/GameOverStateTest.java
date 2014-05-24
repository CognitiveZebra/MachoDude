package se.chalmers.TDA367.group13.stateTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.AppGameContainer;

import se.chalmers.TDA367.group13.controller.GameOverState;
import se.chalmers.TDA367.group13.controller.GameStateController;

public class GameOverStateTest {

	GameOverState gos = new GameOverState();
	@Test
	public void testGetID() {
		assertTrue(gos.getID() == 1337);
	}

}
