package se.chalmers.TDA367.Group13.gameLogicTests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import se.chalmers.TDA367.group13.controller.GameStateController;
import se.chalmers.TDA367.group13.entities.player.Player;
import se.chalmers.TDA367.group13.level.Level_1;
import se.chalmers.TDA367.group13.model.GameModel;

public class GameModelTest {

	static GameModel test;
	
	@BeforeClass
	public static void startUp() throws Exception {
		Display.create();
		test = new GameModel(new AppGameContainer(new GameStateController("MachoDude")), 1);
	}
	
	@Test
	public void testGameModel() {
		assertTrue(test instanceof GameModel);
	}

	@Test
	public void testUpdate() {
		//TODO: Test perhaps movement to right and see if coordinates moved.
		fail("not yet implemented");
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
		fail("not yet implemented");
	}
	
	@AfterClass
	public static void close(){
		Display.destroy();
	}

}
