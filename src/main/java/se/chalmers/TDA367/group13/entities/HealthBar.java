package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.TDA367.group13.Game;

public class HealthBar extends Entity {

	public State state;
	
	public enum State{
		EMPTY, FILLED
	}
	
	public HealthBar(float x, float y) throws SlickException {
		super(x, y, new Image("res/Sprites/MachoDude/Heart-Full.png"));
		
		state = State.FILLED;

	}
	

	@Override
	public void render(Graphics g){
		
		
		
		switch(state){
		
		case EMPTY:
			try {
				setImage(new Image("res/Sprites/MachoDude/Heart-Empty.png"));
			} catch (SlickException e) {
				e.printStackTrace();
			}
			g.drawImage(getImage(), 0, 0);
			break;
		case FILLED:
			try {
				setImage(new Image("/res/Sprites/MachoDude/Heart-Full.png"));
			} catch (SlickException e) {
				e.printStackTrace();
			}
			g.drawImage(getImage(), 0, 0);
			break;
		}
	}
	
}
