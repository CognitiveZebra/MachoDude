package se.chalmers.TDA367.Group13.entitytests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.opengl.Display;

import se.chalmers.TDA367.group13.entities.Entity;
import se.chalmers.TDA367.group13.entities.enemies.Enemy;
import se.chalmers.TDA367.group13.entities.enemies.EnemyStill;
import se.chalmers.TDA367.group13.entities.enemies.EnemyWalking;
import se.chalmers.TDA367.group13.entities.enemies.enemy1.Enemy_1;
import se.chalmers.TDA367.group13.util.Direction;

public class Enemy_1Test {

	static Enemy_1 test;
	
	@BeforeClass
	public static void startUp() throws Exception{
		Display.create();
		test = new Enemy_1(1, 1);
	}

	@Test
	public void testResize() {
		float height = test.getHeight();
		test.resize(3);
		assertTrue(test.getHeight() == 3*height);
	}

	@Test
	public void testEnemy_1() {
		assertTrue(test instanceof Enemy && test instanceof Entity);
	}

	@Test
	public void testInitAnimations() {
		float height = test.getHeight();
		test.initAnimations();
		assertTrue(test.getHeight() == 2*height);
	}

	@Test
	public void testResizeImages() {
		//TODO: Discuss how to test resizeImage
	}
	
	//From this point, the methods in abstract class Enemy is tested
	
	
	@Test
	public void testSetDirection() {
		test.setDirection(Direction.RIGHT);
		assertTrue(test.getDirection().equals(Direction.RIGHT));
	}
	
	@Test
	public void testGetDirection() {
		assertTrue(test.getDirection().equals(Direction.LEFT));
	}
	
	@Test
	public void testGetState() {
		System.out.println(test.getState());
		assertTrue(test.getState().getClass().equals(EnemyStill.class)); 
	}
	
	@Test
	public void testGetStillState() {
		assertTrue(test.getStillState().getClass().equals(EnemyStill.class));
	}
	
	@Test
	public void testGetWalkingState() {
		assertTrue(test.getWalkingState().getClass().equals(EnemyWalking.class));
	}
	
	
	
	
	@AfterClass
	public void close() {
		Display.destroy();
	}

}
