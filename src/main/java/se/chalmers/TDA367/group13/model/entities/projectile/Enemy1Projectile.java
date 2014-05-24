package se.chalmers.TDA367.group13.model.entities.projectile;

import org.newdawn.slick.Image;

import se.chalmers.TDA367.group13.util.Direction;

public class Enemy1Projectile extends Projectile {
	private static float speed = 0.3f;
	private static float scale = 2f;

	public Enemy1Projectile(float x, float y, float angle, Direction direction) {
		super(x, y, (direction == Direction.LEFT) ? ProjectileImageFactory.getImage("enemy1").getScaledCopy(scale) : ProjectileImageFactory.getImage("enemy1").getFlippedCopy(true,false).getScaledCopy(scale), angle, speed, direction);
		this.getImage().setFilter(Image.FILTER_NEAREST);
		damage = 1;
	}

}
