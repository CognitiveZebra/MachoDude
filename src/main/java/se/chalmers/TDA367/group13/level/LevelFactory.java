package se.chalmers.TDA367.group13.level;

import org.newdawn.slick.SlickException;

public class LevelFactory {
	public static Level createLevel(int i) throws SlickException {
		Level level = null;

		switch (i) {
		case 1:
			level = new Level_1();
			break;
		default:
			;
			break;
		}

		return level;

	}
}
