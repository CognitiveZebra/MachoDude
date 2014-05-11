package se.chalmers.TDA367.group13.entities.weapon.playerweapon;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import se.chalmers.TDA367.group13.entities.Projectile;
import se.chalmers.TDA367.group13.entities.weapon.Weapon;
import se.chalmers.TDA367.group13.util.Direction;

public class TestWeapon extends Weapon {

	public TestWeapon(float x, float y) throws SlickException {
		super(x, y, new Image("res/Sprites/testArm.png").getFlippedCopy(true, false),new Image("res/Sprites/testArm.png"), new Image("res/Sprites/testArm.png"), "TestWeapon", 1);
		time = System.currentTimeMillis();
		cooldown = 300;
		firingSound = new Sound("/res/Sound/Shoot.wav");
	}
	
	public Projectile fireWeapon(Direction direction){
		if (direction == Direction.RIGHT) {
				firingSound.play();
				time = System.currentTimeMillis();
				return new Projectile(x, y, getProjectileImage().copy().getFlippedCopy(true, false), getAngle(), 10, direction);
		} else {
				firingSound.play();
				time = System.currentTimeMillis();
				return new Projectile(x, y, getProjectileImage().copy(), getAngle(), 10, direction);
		}
	}
	
	
	@Override
	public void render(Graphics g, Color c){
		if ((System.currentTimeMillis()-time) > cooldown) {
			super.render(g, c);
		}
	}

}
