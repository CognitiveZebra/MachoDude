package se.chalmers.TDA367.group13.entitytests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.Image;

import se.chalmers.TDA367.group13.model.entities.projectile.BossProjectile;
import se.chalmers.TDA367.group13.util.Direction;

public class Boss1ProjectileTest {

	@Test
	public void testBoss1Projectile() throws Exception {
		BossProjectile test = new BossProjectile(1, 1, 1, Direction.RIGHT);
		assertTrue(test.getDamage() == 3);
	}

}
