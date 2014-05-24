package se.chalmers.TDA367.group13.entitytests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.opengl.Display;

import se.chalmers.TDA367.group13.entities.projectile.PlayerProjectile;
import se.chalmers.TDA367.group13.util.Direction;

public class PlayerProjectileTest {
	
	@Test
	public void testPlayerProjectile() {
		PlayerProjectile test = new PlayerProjectile(1, 1, 1, Direction.RIGHT);
		assertTrue(test.getDamage() == 1);
	}

}
