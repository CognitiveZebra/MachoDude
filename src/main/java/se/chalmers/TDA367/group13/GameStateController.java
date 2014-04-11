package se.chalmers.TDA367.group13;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class GameStateController extends StateBasedGame {
	private static MenuState menuState;
	private static GameState gameState;
	private static QuitState quitState;
	private static GameOverState gameOverState;

	
	public GameStateController(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		menuState = new MenuState();
		gameState = new GameState();
		quitState = new QuitState();
		gameOverState = new GameOverState();

		
		addState(menuState);
		addState(gameState);
		addState(quitState);
		addState(gameOverState);
	}
	
	public static GameState getGameState() {
		return gameState;
	}
	
	public static MenuState getMenuState() {
		return menuState;
	}
	
	public static QuitState getQuitState() {
		return quitState;
	}
	
	public static GameOverState getGameOverState(){
		return gameOverState;
	}

}
