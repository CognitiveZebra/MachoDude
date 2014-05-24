package se.chalmers.TDA367.group13.stateTests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.TDA367.group13.controller.StatsState;

public class StatsStateTest {

	StatsState test = new StatsState();
	
	@Test
	public void testGetID() {
		assertTrue(test.getID() == 42);
	}

}
