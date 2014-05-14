package se.chalmers.TDA367.Group13.entitytests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.TDA367.group13.entities.projectile.Enemy1Projectile;
import se.chalmers.TDA367.group13.util.Direction;

public class Enemy1ProjectileTest {

	@Test
	public void testEnemy1Projectile() {
		Enemy1Projectile test = new Enemy1Projectile(1, 1, 1, Direction.RIGHT);
		assertTrue(test.getDamage() == 1);
	}

}
