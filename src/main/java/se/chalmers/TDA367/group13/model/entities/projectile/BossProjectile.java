package se.chalmers.TDA367.group13.model.entities.projectile;

import se.chalmers.TDA367.group13.util.Direction;

public class BossProjectile extends Projectile {
	private static float speed = 0.66f;
	private static float scale = 1.25f;
	public BossProjectile(float x, float y, float angle, Direction direction) {
		super(x, y, ProjectileImageFactory.getImage("boss").getScaledCopy(scale), angle, speed, direction);
		damage = 3;
	}

}
