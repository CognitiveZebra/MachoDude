package se.chalmers.TDA367.group13.entities.projectile;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ProjectileImageFactory {
	public static Image getImage(String s){
		
		String path;
		switch(s){
		case "player": path = "res/Sprites/testArm.png"; break;
		case "enemy1": path ="res/Sprites/Enemies/Enemy_1/Enemy_1-Projectile.png"; break;
		case "fire": path = "res/Sprites/Enemies/Enemy_1/Enemy_1-Projectile.png"; break; 
		default: path = null; break; 
		}
		
		Image image = null; 
		
		try {
			image = new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return image;
	}
}