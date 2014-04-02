package se.chalmers.TDA367.group13;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level_1 extends Level{

	public Level_1()throws SlickException {
		super(new Camera(), new TiledMap("res/Maps/test_map.tmx"), new Image("res/Backgrounds/Jungle_Test.gif"));
	}
}
