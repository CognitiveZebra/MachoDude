package se.chalmers.TDA367.group13.entitytests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.TDA367.group13.model.entities.projectile.FireProjectile;
import se.chalmers.TDA367.group13.util.Direction;

public class FireProjectileTest {

	@Test
	public void testFireProjectile() {
		FireProjectile test = new FireProjectile(1, 1, 2, Direction.RIGHT);
		assertTrue(test.getDamage() == 1);
	}

}
