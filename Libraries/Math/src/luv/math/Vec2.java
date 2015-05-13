package luv.math;

public class Vec2 {

	private float x;
	private float y;
	
	public Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	final float getX() {
		return x;
	}
	
	final float getY() {
		return y;
	}
	
	public void add(Vec2 other) {
		this.x += other.x;
		this.y += other.y;
	}
}
