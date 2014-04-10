package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TestWeapon extends Weapon {
	private long time;
	private float cooldown ;
	
	
	TestWeapon(float x, float y) throws SlickException {
		super(x, y, new Image("res/Sprites/testArm.png").getFlippedCopy(true, false),new Image("res/Sprites/testArm.png"), new Image("res/Sprites/testArm.png"), "TestWeapon", 1);
		time = System.currentTimeMillis();
		cooldown = 300;
	}
	
	public void fireWeapon(Direction direction){
		if (direction == Direction.RIGHT) {
			if ((System.currentTimeMillis()-time) > cooldown){
				getProjectiles().add(new Projectile(x, y, getProjectileImage().copy().getFlippedCopy(true, false), getAngle(), 10, direction));
				time = System.currentTimeMillis();
			}
		} else if (direction == Direction.LEFT) {
			if ((System.currentTimeMillis()-time) > cooldown){
				getProjectiles().add(new Projectile(x, y, getProjectileImage().copy(), getAngle(), 10, direction));
				time = System.currentTimeMillis();
			}
		}
		
	}

}
