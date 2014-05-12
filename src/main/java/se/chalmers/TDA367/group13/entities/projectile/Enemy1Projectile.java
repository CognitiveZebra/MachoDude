package se.chalmers.TDA367.group13.entities.projectile;

import org.newdawn.slick.Image;

import se.chalmers.TDA367.group13.entities.player.Player;
import se.chalmers.TDA367.group13.util.Direction;

public class Enemy1Projectile extends Projectile {
	private static float speed = 3;

	public Enemy1Projectile(float x, float y, float angle, Direction direction) {
		super(x, y, (direction == Direction.LEFT) ? ProjectileImageFactory.getImage("enemy1").getScaledCopy(Player.scale) : ProjectileImageFactory.getImage("enemy1").getFlippedCopy(true,false).getScaledCopy(Player.scale), angle, 5, direction);
		this.getImage().setFilter(Image.FILTER_NEAREST);
		damage = 1;
	}

}
