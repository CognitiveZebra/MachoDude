package se.chalmers.TDA367.Group13.stateTests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.TDA367.group13.controller.GameOverState;

public class GameOverStateTest {

	GameOverState gos = new GameOverState();
	@Test
	public void testGetID() {
		assertTrue(gos.getID() == 1337);
	}

	@Test
	public void testInit() {
		//TODO: Figure out a way to test Slick-methods
	}

	@Test
	public void testRender() {
		//TODO: Figure out a way to test Slick-methods
	}

	@Test
	public void testUpdate() {
		//TODO: Figure out a way to test Slick-methods
	}

	@Test
	public void testInitMenu() {
		//TODO: Figure out a way to test Slick-methods
	}

}
