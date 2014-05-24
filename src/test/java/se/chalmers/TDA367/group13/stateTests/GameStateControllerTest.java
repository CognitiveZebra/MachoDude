package se.chalmers.TDA367.group13.stateTests;

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

}
