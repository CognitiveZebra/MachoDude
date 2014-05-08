package se.chalmers.TDA367.group13.entities.enemies;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.SlickException;

public class Boss_1Test {

	Boss_1 test;
	
	@Test
	public void testSetY() {
		test.setY(5);
		assertTrue(test.getY() == 5);
	}

	@Test
	public void testRenderGraphics() {
		//TODO: Eventually test Slick-methods
	}

	@Test
	public void testResize() {
		
	}

	@Test
	public void testBoss_1() throws SlickException {
		
		Boss_1 test2 = new Boss_1(0,0);
		
		this.test = test2;
	

	}

//	@Test
//	public void testInitAnimations() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetHealth() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetHealthInt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testResizeBeam() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFireLaser() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetHealthFloat() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testResizeImages() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testLoseHealth() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsDestroyed() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsHurt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetWalkingSpeed() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetNextRightX() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetNextLeftX() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetNextY() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMoveRight() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMoveLeft() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMoveY() {
//		fail("Not yet implemented");
//	}
//
}
