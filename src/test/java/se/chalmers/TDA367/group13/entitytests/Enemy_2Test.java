package se.chalmers.TDA367.group13.entitytests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.opengl.Display;

import se.chalmers.TDA367.group13.model.entities.Entity;
import se.chalmers.TDA367.group13.model.entities.enemies.Enemy;
import se.chalmers.TDA367.group13.model.entities.enemies.enemy2.Enemy_2;

public class Enemy_2Test {

	static Enemy_2 test;
	
	@BeforeClass
	public static void startUp() throws Exception{
		test = new Enemy_2(1, 1);
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
		float height = test.getWeapon().getHeight();
		test.initAnimations();
		assertTrue(test.getWeapon().getHeight() == 2*height);
	}

	@Test
	public void testResizeImages() {
		//TODO: Discuss how to test resizeImage
	}
}
