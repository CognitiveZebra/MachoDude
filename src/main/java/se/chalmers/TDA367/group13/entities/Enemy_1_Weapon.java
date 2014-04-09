package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy_1_Weapon extends Weapon {
	
	Enemy_1_Weapon(float x, float y) throws SlickException {
		super(x, y, new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Arm.png"),new Image("res/Sprites/Enemies/Enemy_1/Enemy_1-Arm.png").getFlippedCopy(true, false), "Enemy_1_Weapon", 1);
	}
	
	public void fireWeapon(Direction direction){
		System.out.println("Shots fired!");
	}
	

}
