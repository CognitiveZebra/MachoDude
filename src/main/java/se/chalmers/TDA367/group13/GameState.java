package se.chalmers.TDA367.group13;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.TDA367.group13.util.Stats;


public class GameState extends BasicGameState{
	public static final int ID = 3;
	private GameView view;
	private GameModel model;
	private Input input;

	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		model = new GameModel(gc);
		view = new GameView(model);
		input = gc.getInput();
		model.startMusic();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sgb, Graphics g)throws SlickException {
		view.render(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)	throws SlickException {
		if(model.getPlayer().isDead()){
			
			Stats.addEnemiesKilled(model.getLevel().getScore());
			
			if(Stats.getHighscore() < model.getLevel().getScore()){
				Stats.setHighscore(model.getLevel().getScore());
			}
			
			GameStateController.getGameOverState().setScore(model.getLevel().getScore());
			sbg.enterState(GameStateController.getGameOverState().getID());
		} else {
			model.update(input,delta);
		}

	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
	}
}
