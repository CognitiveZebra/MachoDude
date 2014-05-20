package se.chalmers.TDA367.group13.stateTests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.TDA367.group13.controller.QuitState;

public class QuitStateTest {

	QuitState test = new QuitState();
	
	@Test
	public void testGetID() {
		assertTrue(test.getID() == 666);
	}

}
