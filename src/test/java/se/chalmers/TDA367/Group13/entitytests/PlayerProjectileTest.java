package se.chalmers.TDA367.Group13.entitytests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.TDA367.group13.entities.projectile.PlayerProjectile;
import se.chalmers.TDA367.group13.util.Direction;

public class PlayerProjectileTest {

	@Test
	public void testPlayerProjectile() {
		PlayerProjectile test = new PlayerProjectile(1, 1, 1, Direction.RIGHT);
		assertTrue(test.getDamage() == 1);
	}

}
