package se.chalmers.TDA367.group13.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuitStateTest {

	QuitState test = new QuitState();
	
	@Test
	public void testGetID() {
		assertTrue(test.getID() == 666);
	}

}
