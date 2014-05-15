package se.chalmers.TDA367.Group13.stateTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.Input;

import se.chalmers.TDA367.group13.controller.SettingsState;

public class SettingsStateTest {

	SettingsState test = new SettingsState();
	
	@Test
	public void testGetID() {
		assertTrue(test.getID() == 15);
	}

	@Test
	public void testKeyPressed() {
		test.keyPressed(Input.KEY_A, 'A');
	}

	@Test
	public void testMousePressed() {
		test.mousePressed(Input.MOUSE_LEFT_BUTTON, 1, 1);
	}

	@Test
	public void testInit() {
		//TODO: Find out a good way to test voidmethods/Slickmethods
		fail("Not yet implemented");
	}

	@Test
	public void testRender() {
		//TODO: Find out a good way to test voidmethods/Slickmethods
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		//TODO: Find out a good way to test voidmethods/Slickmethods
		fail("Not yet implemented");
	}

	@Test
	public void testInitSettings() {
		//TODO: Find out a good way to test voidmethods/Slickmethods
		fail("Not yet implemented");
	}

}
