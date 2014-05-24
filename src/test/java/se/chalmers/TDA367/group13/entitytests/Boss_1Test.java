package se.chalmers.TDA367.group13.entitytests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;

import se.chalmers.TDA367.group13.model.entities.Entity;
import se.chalmers.TDA367.group13.model.entities.enemies.boss1.Boss_1;

public class Boss_1Test {

	static Boss_1 test;
	
	@BeforeClass
	public static void startUp() throws Exception{
		test = new Boss_1(1, 1);
	}
	
	@Test
	public void testSetY() {
		test.setY(5);
		assertTrue(test.getY() == 5);
	}
	
	@Test
	public void testResize() {
		float height = test.getHeight();
		test.resize(2f);
		assertTrue(test.getHeight() > height);
	}

	@Test
	public void testBoss_1() throws SlickException {
		assertTrue(test instanceof Entity);
	}


	@Test
	public void testGetHealth() {
		assertTrue(test.getHealth() == 20);
	}

	@Test
	public void testSetHealthInt() {
		test.setHealth(22);
		assertTrue(test.getHealth() == 22);
	}

	@Test
	public void testFireLaser() {
		test.fireLaser();
	}

	@Test
	public void testSetHealthFloat() {
		test.setHealth(21f);
		assertTrue(test.getHealth() == 21f);
	}

	@Test
	public void testLoseHealth() {
		float health = test.getHealth();
		test.loseHealth();
		assertTrue(test.getHealth() == health -1);
	}

	@Test
	public void testIsDestroyed() {
		test.setHealth(0f);
		assertTrue(test.isDestroyed());
		test.setHealth(19);
		assertFalse(test.isDestroyed());
	}

	@Test
	public void testIsHurt() {
		test.setHealth(50);
		assertFalse(test.isHurt());
		test.loseHealth();
		assertTrue(test.isHurt());
	}
}
