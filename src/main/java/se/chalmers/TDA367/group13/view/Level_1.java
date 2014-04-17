package se.chalmers.TDA367.group13.view;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.TDA367.group13.util.Camera;

public class Level_1 extends Level{

	public Level_1()throws SlickException {
		super(new Camera(), new TiledMap("res/Maps/test_map.tmx"), new Image("res/Backgrounds/Jungle_Test.gif"), new Music("res/Music/RoccoW_-_Something_Something_Knight.ogg"));
	}
}
