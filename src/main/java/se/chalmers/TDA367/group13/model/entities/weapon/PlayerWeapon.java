package se.chalmers.TDA367.group13.model.entities.weapon;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import se.chalmers.TDA367.group13.model.entities.projectile.PlayerProjectile;
import se.chalmers.TDA367.group13.model.entities.projectile.Projectile;
import se.chalmers.TDA367.group13.util.Direction;

public class PlayerWeapon extends Weapon {

	public PlayerWeapon(float x, float y) throws SlickException {
		super(x, y, new Image("res/Sprites/testArm.png").getFlippedCopy(true, false),new Image("res/Sprites/testArm.png"), new Image("res/Sprites/testArm.png"), "TestWeapon", 1);
		time = System.currentTimeMillis();
		cooldown = 300;
		firingSound = new Sound("/res/Sound/Shoot.wav");
	}
	
	public Projectile fireWeapon(Direction direction){
		if (direction == Direction.RIGHT) {
				firingSound.play();
				time = System.currentTimeMillis();
				return new PlayerProjectile(x, y, getAngle(), direction);
		} else {
				firingSound.play();
				time = System.currentTimeMillis();
				return new PlayerProjectile(x, y, getAngle(), direction);
		}
	}
	
	@Override 
	public void render(Graphics g, Direction d){
		if (isReady()) {
			super.render(g, d);
		}
	}
	
	@Override
	public void render(Graphics g, Color c){
		if (isReady()) {
			super.render(g, c);
		}
	}

}
