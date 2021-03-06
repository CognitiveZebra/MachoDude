package se.chalmers.TDA367.group13.controller;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;

import se.chalmers.TDA367.group13.exception.GameOverException;
import se.chalmers.TDA367.group13.exception.WinException;
import se.chalmers.TDA367.group13.model.GameModel;
import se.chalmers.TDA367.group13.view.GameView;


public class GameState extends BasicGameState{
	public static final int ID = 3;
	private GameView view;
	private GameModel model;
	private Input input;

	
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		model = new GameModel(gc,((GameStateController)sbg).getLevel());
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
			try {
				model.update(input,delta);
			} catch (GameOverException e) {
				sbg.enterState(((GameStateController)sbg).getGameOverState().getID(), new EmptyTransition(), new VerticalSplitTransition());
			} catch (WinException e) {
				sbg.enterState(((GameStateController)sbg).getWinState().getID(), new EmptyTransition(), new VerticalSplitTransition());
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
