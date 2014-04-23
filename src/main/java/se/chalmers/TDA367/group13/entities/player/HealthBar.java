package se.chalmers.TDA367.group13.entities.player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.TDA367.group13.Game;

public class HealthBar {

	private Image fullImage, emptyImage;
	private Image [] hearts;
	private float spacing;
	private int orgHealth;
	
	public HealthBar(int health) throws SlickException {
		fullImage = new Image("res/Sprites/MachoDude/Heart-Full.png");
		emptyImage = new Image("res/Sprites/MachoDude/Heart-Empty.png");
		hearts = new Image[health];
		orgHealth = health;
		resize(5);
		for (int i = 0; i < health; i++) {
			hearts[i] = fullImage.copy();
		}
	}
	

	public void render(Graphics g, int health){

		
		for (int i = 0; i < orgHealth - health; i++) {
			hearts[i] = emptyImage.copy();
		}
		for (int i = 0; i < hearts.length; i++) {
			g.drawImage(hearts[i], Game.WIDTH - (i+1)*(hearts[i].getWidth()+ spacing), hearts[i].getWidth()/11);
		} 
	}
	
	public void resize(float scale) {
		fullImage.setFilter(Image.FILTER_NEAREST);
		fullImage = fullImage.getScaledCopy(scale);
		emptyImage.setFilter(Image.FILTER_NEAREST);
		emptyImage= emptyImage.getScaledCopy(scale);
		spacing = fullImage.getWidth()/11;
	}
	
}
