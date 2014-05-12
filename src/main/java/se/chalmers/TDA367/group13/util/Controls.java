package se.chalmers.TDA367.group13.util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Scanner;

import org.newdawn.slick.Input;


public class Controls {
	
	private  int rightKey, leftKey, jumpKey, shootKey;
	private static Controls instance = null;
	
	private Controls() {
		readControls();
	}
	
	public static synchronized Controls getInstance(){
		if(instance == null){
			instance = new Controls();
		}
		return instance; 
	}
	
	
	
	public  void reset() {
    	setRightKey(Input.KEY_D);
    	setLeftKey(Input.KEY_A);
    	setJumpKey(Input.KEY_W);
    	setShootKey(Input.MOUSE_LEFT_BUTTON);
	}
	
	public  void readControls() {
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

	public  void saveControls() {
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
	public  int getRightKey() {
		return rightKey;
	}

	public  void setRightKey(int rightKey) {
		this.rightKey = rightKey;
	}

	public  int getLeftKey() {
		return leftKey;
	}

	public  void setLeftKey(int leftKey) {
		this.leftKey = leftKey;
	}

	public  int getJumpKey() {
		return jumpKey;
	}

	public  void setJumpKey(int jumpKey) {
		this.jumpKey = jumpKey;
	}

	public  int getShootKey() {
		return shootKey;
	}

	public  void setShootKey(int shootKey) {
		this.shootKey = shootKey;
	}

	public boolean isDuplicate(String title, int control) {
		LinkedList<Integer> controls = new LinkedList<Integer>();
		switch (title) {
			case "RIGHT":
				controls.add(leftKey);
				controls.add(jumpKey);
				controls.add(shootKey);
				break;
			case "LEFT":
				controls.add(rightKey);
				controls.add(jumpKey);
				controls.add(shootKey);
				break;
			case "JUMP":
				controls.add(leftKey);
				controls.add(rightKey);
				controls.add(shootKey);
				break;
			case "SHOOT":
				controls.add(leftKey);
				controls.add(jumpKey);
				controls.add(rightKey);
				break;
		default:
			break;
		}
		return controls.contains(control);
	}
}
