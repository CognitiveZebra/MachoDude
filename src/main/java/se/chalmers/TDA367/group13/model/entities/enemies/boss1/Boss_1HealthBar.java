package se.chalmers.TDA367.group13.model.entities.enemies.boss1;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.RoundedRectangle;

import se.chalmers.TDA367.group13.util.Constants;

public class Boss_1HealthBar {
	private float height, width;
	private RoundedRectangle bg, bar;
	private Color barColor, bgColor, textColor;

	public Boss_1HealthBar(){
		height = 30;
		width = 300;
		textColor = new Color(255, 255, 255);
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

	
	public void updateHealthBar(Boss_1 b){
		bg = new RoundedRectangle((Constants.WIDTH/2)-(width/2),100,width, height, 2f);
		bar = new RoundedRectangle((Constants.WIDTH/2)-(width/2),100,(float) (width*getRatio((int)b.getHealth(),(int)b.getMaxHealth())),height, 2f);
		barColor = getColor((int)b.getHealth(), (int)b.getMaxHealth());
		bgColor = getColor((int)b.getHealth(), (int)b.getMaxHealth()).darker().darker();
	}


	public void render(Boss_1 b, Graphics g) {
		updateHealthBar(b);
		g.setColor(bgColor);
		g.fill(bg);
		g.setColor(barColor);
		g.fill(bar);
		g.setColor(textColor);
		g.drawString("KIM TRON IL", (Constants.WIDTH/2)-(width/5), 70);
	}

}
