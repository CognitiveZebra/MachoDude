package se.chalmers.TDA367.group13.entities.weapon.enemy2weapon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.entities.projectile.FireProjectile;
import se.chalmers.TDA367.group13.entities.projectile.Projectile;
import se.chalmers.TDA367.group13.entities.weapon.Weapon;
import se.chalmers.TDA367.group13.util.Direction;

public class Enemy_2_Weapon extends Weapon {
	private XMLPackedSheet weaponSheet;

	public Enemy_2_Weapon(float x, float y) throws SlickException {
		super(x, y, new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Arm.png"), new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Arm.png").getFlippedCopy(true, false), new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Projectile.png"),"Enemy_1_Weapon", 1);
		shoulder = new Vector2f(4, 4);
		nuzzle = new Vector2f(3, 4);
		distanceToNuzzle = new Line(nuzzle, shoulder);
		nuzzleAngle = Math.atan2(distanceToNuzzle.getX1() - distanceToNuzzle.getX2(), distanceToNuzzle.getY1() - distanceToNuzzle.getY2());
		time = System.currentTimeMillis();
		cooldown = 750;
		speed = 2;
		weaponSheet = new XMLPackedSheet("res/Sprites/Enemies/Enemy_2/Enemy_2WeaponSheet.png", "res/Sprites/Enemies/Enemy_2/Enemy_2WeaponSheet.xml");
		setRightImage(weaponSheet.getSprite("Enemy_2Weapon.png"));
		setLeftImage(weaponSheet.getSprite("Enemy_2Weapon.png"));

	}
	
	@Override
	public Projectile fireWeapon(Direction direction) {
		if (direction == Direction.RIGHT) {
			if ((System.currentTimeMillis() - time) > cooldown) {
				time = System.currentTimeMillis();
				return new FireProjectile(getProjectileX(direction),getProjectileY(direction), getAngle(), direction);
			}
		} else if (direction == Direction.LEFT) {
			if ((System.currentTimeMillis() - time) > cooldown) {
				time = System.currentTimeMillis();
				return new FireProjectile(getProjectileX(direction),getProjectileY(direction), getAngle(), direction);
			}
		}
		
		return null;

	}
	
}
