package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class HealthBarEnemy {
	private float height = 5, yOffset = -10;
	private int max;
	
	public HealthBarEnemy(int max){
		this.max = max;
	}

	public void render(Enemy e, Graphics g){
		if(e.isHurt()){
			g.setColor(Color.black);
			//frame
			g.drawRect(e.getX()-1,e.getY()-1+yOffset,e.getWidth()+2, height+2);
			g.setColor(this.getColor(e.health, max));
			//health bar
			g.fillRect(e.getX(),e.getY()+yOffset,(float) (e.getWidth()*getRatio(e.health,max)),height);
		}
	}
	
	public Color getColor(int health, int max){
		double percent = getRatio(health,max);
		if(percent <=  0.33) return new Color(250,20,20,150);
		else if(percent <= 0.66) return new Color(255,83,0,150);
		else return new Color(20,250,20,150);
	}

	private double getRatio(int health, int max) {
		return (double)health/(double)max;
	}
	
}
