package se.chalmers.TDA367.group13.stateTests;

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

}
