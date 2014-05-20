package se.chalmers.TDA367.group13.stateTests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.TDA367.group13.controller.MenuState;

public class MenuStateTest {

	MenuState test = new MenuState();
	@Test
	public void testGetID() {
		assertTrue(test.getID() == 2);
	}

}
