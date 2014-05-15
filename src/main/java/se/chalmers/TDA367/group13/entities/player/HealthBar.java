package se.chalmers.TDA367.group13.entities.player;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.TDA367.group13.Game;

public class HealthBar {

	private Image fullImage, halfImage, emptyImage;
	private ArrayList<Image> hearts;
	private float spacing;
	private float orgHealth;
	
	public HealthBar(int health) throws SlickException {
		fullImage = new Image("res/Sprites/MachoDude/Heart-Full.png");
		halfImage = new Image("res/Sprites/MachoDude/Heart-Half.png");
		emptyImage = new Image("res/Sprites/MachoDude/Heart-Empty.png");
		hearts = new ArrayList<Image>();
		orgHealth = health;
		resize(5);
	}
	

	public void render(Graphics g, int health){
		hearts.clear();
		for (int i = 0; i < orgHealth/2; i++) {
			if (i < health/2) {
				hearts.add(fullImage.copy());
			} else if (health%2 != 0 && i == health/2) {
				hearts.add(halfImage.copy());
			} else {
				hearts.add(emptyImage.copy());
			}
		}
		
		for (int i = 0; i < hearts.size(); i++) {
			g.drawImage(hearts.get(i), (i)*(hearts.get(i).getWidth()+ spacing), hearts.get(i).getWidth()/11);
		}
	}
	
	public void resize(float scale) {
		fullImage.setFilter(Image.FILTER_NEAREST);
		fullImage = fullImage.getScaledCopy(scale);
		halfImage.setFilter(Image.FILTER_NEAREST);
		halfImage= halfImage.getScaledCopy(scale);
		emptyImage.setFilter(Image.FILTER_NEAREST);
		emptyImage= emptyImage.getScaledCopy(scale);
		spacing = fullImage.getWidth()/11;
	}
	
}
