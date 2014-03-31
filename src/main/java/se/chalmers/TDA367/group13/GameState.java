package se.chalmers.TDA367.group13;
import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameState extends BasicGameState{
	public static final int ID = 3;
	private GameView view;
	private GameModel model;
	private Input input;

	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		model = new GameModel(gc);
		view = new GameView(model);
		input = gc.getInput();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sgb, Graphics g)throws SlickException {
		view.render(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)	throws SlickException {
		model.updateModel(input);
	}

	@Override
	public int getID() {
		return ID;
	}

}
