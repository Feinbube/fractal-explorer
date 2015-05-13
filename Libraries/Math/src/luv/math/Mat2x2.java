package luv.math;

public class Mat2x2 {

	float x1;
	float x2;
	float y1;
	float y2;
	
	public Mat2x2(final float x1, final float x2, final float y1, final float y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public static Vec2 mult(final Mat2x2 mat, final Vec2 v) {
		return mat.mult(v);
	}
	
	public final Vec2 mult(final Vec2 v) {
		return new Vec2(
				x1*v.getX() + x2*v.getY(),
				y1*v.getX() + y2*v.getY()); 
	}
}
