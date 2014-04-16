package se.chalmers.TDA367.group13.util;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;

public class Util {
	public static Font getFont32(){
		try {
			return new AngelCodeFont("res/Fonts/Visitor1.fnt", "res/Fonts/Visitor1_0.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}
}
