package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Boss_1 extends Entity {
	private Image image, jaw, laserBegin, laserBeam;
	private int health, jawPosition;
	
	
	public Boss_1(float x, float y) throws SlickException{
		super(x, y, new Image("/res/Sprites/Bosses/1/boss_1_head.png"));
		this.jaw = new Image("/res/Sprites/Bosses/1/boss_1_jaw.png");
		this.laserBegin = new Image("/res/Sprites/Bosses/1/laser_begin.png");
		this.laserBeam = new Image("/res/Sprites/Bosses/1/laser_beam.png");
		this.health = 20;
		this.jawPosition = (int) (getY()+20); // This position is just preliminary and not really based on anything
	}

	public Image getJaw() {
		return jaw;
	}


	public void setJaw(Image jaw) {
		this.jaw = jaw;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}
	
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY());
		g.drawImage(jaw, getX(), jawPosition);
	}
	
	public void resize(float scale){
		//this code is awfull
		image.setFilter(Image.FILTER_NEAREST);
		image = image.getScaledCopy(scale);
		jaw.setFilter(Image.FILTER_NEAREST);
		jaw = jaw.getScaledCopy(scale);
		setImage(image);
		setJaw(jaw);
	}
	
	public void fireLaser(){
		new Projectile(getX(), getY(), laserBeam, 0, 20, Direction.LEFT );
	}
}

