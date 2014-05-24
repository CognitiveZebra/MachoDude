package se.chalmers.TDA367.group13.model.entities.enemies;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.RoundedRectangle;

public class HealthBarEnemy {
	private float height = 5, yOffset = -10;
	private RoundedRectangle bg, bar;
	private Color barColor, bgColor;


	public void render(Enemy e, Graphics g){
		if(e.isHurt()){
			
			updateHealthBar(e);
			g.setColor(bgColor);
			g.fill(bg);
			g.setColor(barColor);
			g.fill(bar);

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
		bg = new RoundedRectangle(e.getX(),e.getY()+yOffset,e.getWidth(), height, 2f);
		bar = new RoundedRectangle(e.getX(),e.getY()+yOffset,(float) (e.getWidth()*getRatio(e.health,e.maxHealth)),height, 2f);
		barColor = getColor(e.health, e.maxHealth);
		bgColor = getColor(e.health, e.maxHealth).darker().darker();
	}
	
}
