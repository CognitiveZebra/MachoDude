package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class HealthBarEnemy {
	private float height = 5, yOffset = -10;
	private Rectangle frame, bar;
	private Color barColor;


	public void render(Enemy e, Graphics g){
		if(e.isHurt()){
			
			updateHealthBar(e);
			
			g.setColor(barColor);
			g.fill(bar);
			g.setColor(Color.black);
			g.draw(frame);
		}
	}
	
	public Color getColor(int health, int max){
		double ratio = getRatio(health,max);
		double ratiotInverse = 1 - ratio; 
		float red = (float)(ratiotInverse);
		float green = (float)(ratio);
		float blue = 0;
		return new Color(red, green, blue);
	}

	private double getRatio(int health, int max) {
		return (double)health/(double)max;
	}
	
	public void updateHealthBar(Enemy e){
		frame = new Rectangle(e.getX(),e.getY()+yOffset,e.getWidth(), height);
		bar = new Rectangle(e.getX(),e.getY()+yOffset,(float) (e.getWidth()*getRatio(e.health,e.maxHealth)),height);
		barColor = getColor(e.health, e.maxHealth);
	}
	
}
