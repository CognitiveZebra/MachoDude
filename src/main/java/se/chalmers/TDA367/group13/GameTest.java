package se.chalmers.TDA367.group13;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.SlickException;

public class GameTest {

	@Test
	public void testGame() {
		Game test = new Game();
		assertTrue(test.getTitle() == "MachoDude");
	}

	@Test
	public void testRender() {
		//No Rendering is done in Game
	}

	@Test
	public void testInitGameContainer() {
		//No initializing is done in Game
	}

	@Test
	public void testUpdateGameContainerInt() {
		//No update is done in Game
	}

	@Test
	public void testMain() {
		//TODO: Find out a good way to test main method
		
	}

}
