package se.chalmers.TDA367.Group13.stateTests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.TDA367.group13.controller.GameStateController;

public class GameStateControllerTest {
	GameStateController test;
	
	@Test
	public void testGameStateController() {
		test = new GameStateController("MachoDude");
		
		assertTrue(test.getTitle() == "MachoDude");
	}

	@Test
	public void testInitStatesListGameContainer() {
		//TODO: Figure out a way to test Slick-methods
		fail("Not yet implemented");
	}

	@Test
	public void testGetGameState() {
		assertTrue(GameStateController.getGameState().getID() == 3);
	}

	@Test
	public void testGetMenuState() {
		assertTrue(GameStateController.getMenuState().getID() == 2);
	}

	@Test
	public void testGetQuitState() {
		assertTrue(GameStateController.getQuitState().getID() == 666);
	}

	@Test
	public void testGetGameOverState() {
		assertTrue(GameStateController.getGameOverState().getID() == 1337);
	}

	@Test
	public void testGetSettingsState() {
		assertTrue(GameStateController.getSettingsState().getID() == 15);
	}

	@Test
	public void testGetStatsState() {
		assertTrue(GameStateController.getStatsState().getID() == 42);
	}

}
