package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.TDA367.group13.Level;

public class Enemy_1_Weapon extends Weapon {
	
	private long time;
	private float cooldown;
	private Level level;

	Enemy_1_Weapon(float x, float y, Level level) throws SlickException {
		super(x, y, new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Arm.png"),new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Arm.png").getFlippedCopy(true, false), new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Projectilet.png").getFlippedCopy(true, false), "Enemy_1_Weapon", 1);
		this.level = level;
		time = System.currentTimeMillis();
		cooldown = 300;
	}
	
	public void fireWeapon(Direction direction){

		if (direction == Direction.RIGHT) {
			if ((System.currentTimeMillis()-time) > cooldown){
				level.getProjectiles().add(new Projectile(x, y, getProjectileImage().copy().getFlippedCopy(true, false), getAngle(), 5, direction));
				time = System.currentTimeMillis();
			}
		} else if (direction == Direction.LEFT) {
			if ((System.currentTimeMillis()-time) > cooldown){
				level.getProjectiles().add(new Projectile(x, y, getProjectileImage().copy(), getAngle(), 5, direction));
				time = System.currentTimeMillis();
			}
		}
		
	}

}
