package se.chalmers.TDA367.group13.util;

public class Camera {
	
	private float x;

	public Camera() {
		x = 0;
	}
	
	public Camera(float x) {
		this.x = x;
	}
	
	public float getX(){
		return x;
	}
	
	public void move(float f) {
		x = x + f;
	}
}
