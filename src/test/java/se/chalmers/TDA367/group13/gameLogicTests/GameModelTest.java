package se.chalmers.TDA367.group13.gameLogicTests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.tests.InputTest;

import se.chalmers.TDA367.group13.Game;
import se.chalmers.TDA367.group13.controller.GameStateController;
import se.chalmers.TDA367.group13.model.GameModel;
import se.chalmers.TDA367.group13.model.entities.player.Player;
import se.chalmers.TDA367.group13.model.level.Level_1;

public class GameModelTest {

	static GameModel test;
	
	@BeforeClass
	public static void startUp() throws Exception {
		test = new GameModel(new AppGameContainer(new GameStateController("MachoDude")), 1);
	}
	
	@Test
	public void testGameModel() {
		assertTrue(test instanceof GameModel);
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
		test.startMusic();
	}

}
