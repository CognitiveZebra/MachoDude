package se.chalmers.TDA367.group13.util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

import org.newdawn.slick.Input;


public class Controls {
	
	private static int rightKey, leftKey, jumpKey, shootKey;
	
	public static void readControls() {
        Scanner sc = null;

        try {
        	File file = new File("res/User/Controls/controls.save");
        	if (file.exists()) {
	        	sc = new Scanner(new FileReader(file));
	            sc.useDelimiter(";");
	            if (sc.hasNext()) {
	            	setRightKey(sc.nextInt());
	            	setLeftKey(sc.nextInt());
	            	setJumpKey(sc.nextInt());
	            	setShootKey(sc.nextInt());
	            }
	            sc.close();
        	} else {
        		saveControls();
        	}

        } catch (FileNotFoundException e) {
            throw new RuntimeException("I/O error. Could not read from file.", e);
        }
	}

	public static void saveControls() {
		Writer writer = null;
		try {
			File file = new File("res/User/Controls/controls.save");
			 
			if(!file.exists()){
				writer = new PrintWriter(file, "UTF-8");
				file.createNewFile();
                writer.write(String.format("%s;%s;%s;%s;", Input.KEY_D,Input.KEY_A,Input.KEY_W,Input.MOUSE_LEFT_BUTTON));
            	setRightKey(Input.KEY_D);
            	setLeftKey(Input.KEY_A);
            	setJumpKey(Input.KEY_W);
            	setShootKey(Input.MOUSE_LEFT_BUTTON);
			} else {
				writer = new PrintWriter(file, "UTF-8");
				writer.write(String.format("%s;%s;%s;%s;", getRightKey(),
             											getLeftKey(),
             											getJumpKey(),
             											getShootKey()));
			}

             writer.close();
			
			


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int getRightKey() {
		return rightKey;
	}

	public static void setRightKey(int rightKey) {
		Controls.rightKey = rightKey;
	}

	public static int getLeftKey() {
		return leftKey;
	}

	public static void setLeftKey(int leftKey) {
		Controls.leftKey = leftKey;
	}

	public static int getJumpKey() {
		return jumpKey;
	}

	public static void setJumpKey(int jumpKey) {
		Controls.jumpKey = jumpKey;
	}

	public static int getShootKey() {
		return shootKey;
	}

	public static void setShootKey(int shootKey) {
		Controls.shootKey = shootKey;
	}
}
