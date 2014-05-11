package se.chalmers.TDA367.group13.entities.weapon.enemy1weapon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.entities.Projectile;
import se.chalmers.TDA367.group13.entities.weapon.Weapon;
import se.chalmers.TDA367.group13.util.Direction;
import se.chalmers.TDA367.group13.view.Level;

public class Enemy_1_Weapon extends Weapon {
	private float speed;
	private static Vector2f shoulder = new Vector2f(11, 11);
	private static Vector2f nuzzle = new Vector2f(17, 21);

	public Enemy_1_Weapon(float x, float y) throws SlickException {
		super(x, y, shoulder, nuzzle, new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Arm.png"), new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Arm.png").getFlippedCopy(true, false), new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Projectile.png"),"Enemy_1_Weapon", 1);
		cooldown = 750;
		speed = 3;
		firingSound = new Sound("/res/Sound/Enemy_1/Shoot.wav");

	}

	public Projectile fireWeapon(Direction direction) {
		if (direction == Direction.RIGHT) {
				firingSound.play();
				time = System.currentTimeMillis();

				return new Projectile(getProjectileX(direction),getProjectileY(direction),getProjectileImage().copy().getFlippedCopy(true, false), getAngle(), speed, direction);
		} else {
				firingSound.play();
				time = System.currentTimeMillis();
				return new Projectile(getProjectileX(direction),getProjectileY(direction),getProjectileImage().copy(), getAngle(), speed, direction);
		}
	}
	
}
