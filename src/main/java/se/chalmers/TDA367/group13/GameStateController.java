package se.chalmers.TDA367.group13;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class GameStateController extends StateBasedGame {
	private static MenuState menuState;
	private static GameState gameState;

	
	public GameStateController(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		menuState = new MenuState();
		gameState = new GameState();

		
		addState(menuState);
		addState(gameState);
	}
	
	public static GameState getGameState() {
		return gameState;
	}
	
	public static MenuState getMenuState() {
		return menuState;
	}

}
