package se.chalmers.TDA367.group13;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class GameStateController extends StateBasedGame {

	private static GameState gameState;


	
	
	public GameStateController(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		
		gameState = new GameState();

		
		
		addState(gameState);
	}
	
	public static GameState getGameState() {
		return gameState;
	}


}
