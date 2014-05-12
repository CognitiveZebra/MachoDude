package se.chalmers.TDA367.Group13.gameLogicTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.Input;

import se.chalmers.TDA367.group13.entities.player.Player;
import se.chalmers.TDA367.group13.level.Level_1;
import se.chalmers.TDA367.group13.model.GameModel;

public class GameModelTest {

	GameModel test = new GameModel(null);
	
	@Test
	public void testGameModel() {
		GameModel model = new GameModel(null);
	}

	@Test
	public void testUpdate() {
		//TODO: Test perhaps movement to right and see if coordinates moved.
	}

	@Test
	public void testGetLevel() {
		assertTrue(test.getLevel().getClass() == Level_1.class);
	}

	@Test
	public void testGetPlayer() {
		assertTrue(test.getPlayer().getClass() == Player.class );
	}

	@Test
	public void testStartMusic() {
		//test.startMusic();
		//TODO: Need a getter for Music to test if it's playing
	}

}
