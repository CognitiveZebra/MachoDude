package se.chalmers.TDA367.group13.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameStateController extends StateBasedGame {
	private MenuState menuState;
	private GameState gameState;
	private QuitState quitState;
	private LevelState levelState; 
	private GameOverState gameOverState;
	private WinState winState;
	private SettingsState settingsState;
	private StatsState statsState;
	private int level = 1;
	
	public GameStateController(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		menuState = new MenuState();
		gameState = new GameState();
		quitState = new QuitState();
		gameOverState = new GameOverState();
		winState = new WinState();
		settingsState = new SettingsState();
		statsState = new StatsState();
		levelState = new LevelState();

		
		addState(menuState);
		addState(gameState);
		addState(quitState);
		addState(gameOverState);
		addState(winState);
		addState(levelState);
		addState(settingsState);
		addState(statsState);
	}
	
	public BasicGameState getGameState() {
		return gameState;
	}
	
	public BasicGameState getMenuState() {
		return menuState;
	}
	
	public BasicGameState getQuitState() {
		return quitState;
	}
	
	
	public BasicGameState getGameOverState() {
		return gameOverState;
	}
	
	public BasicGameState getWinState() {
		return winState;
	}
	
	public BasicGameState getSettingsState() {
		return settingsState;
	}
	
	public BasicGameState getStatsState(){
		return statsState;
	}
	
	public BasicGameState getLevelState(){
		return levelState;
		
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return level;
	}

}
