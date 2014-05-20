package se.chalmers.TDA367.group13.entitytests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import se.chalmers.TDA367.group13.entities.projectile.Enemy1Projectile;
import se.chalmers.TDA367.group13.util.Direction;

public class Enemy1ProjectileTest {

	@BeforeClass
	public static void startUp() throws Exception{
		Display.create();
	}
	
	@Test
	public void testEnemy1Projectile() {
		Enemy1Projectile test = new Enemy1Projectile(1, 1, 1, Direction.RIGHT);
		assertTrue(test.getDamage() == 1);
	}
	
	@AfterClass
	public static void close(){
		Display.destroy();
	}

}
