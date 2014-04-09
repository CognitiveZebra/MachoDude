package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TestWeapon extends Weapon {
	private long time;
	private float cooldown ;
	
	
	TestWeapon(float x, float y) throws SlickException {
		super(x, y, new Image("res/Sprites/testArm.png").getFlippedCopy(true, false), "TestWeapon", 1);
		time = System.currentTimeMillis();
		cooldown = 300;
	}
	
	public void fireWeapon(){

		if ((System.currentTimeMillis()-time) > cooldown){
		getProjectiles().add(new Projectile(x, y, getImage().copy(), getAngle(), 10));
		time = System.currentTimeMillis();
		}
		
	}

}
