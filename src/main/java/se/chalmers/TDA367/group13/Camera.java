package se.chalmers.TDA367.group13;

public class Camera {
	
	private float x;

	public Camera() {
		x = 0;
	}
	
	public Camera(float x) {
		this.x = x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getX(){
		return x;
	}
}
