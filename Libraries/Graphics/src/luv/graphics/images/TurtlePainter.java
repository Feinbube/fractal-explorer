package luv.graphics.images;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class TurtlePainter extends ImagePainter {

    private double xPos;
    private double yPos;
    private double rotation;
    private Boolean drawing;

    public TurtlePainter(BufferedImage image) {
    	super(image);
    	
        xPos = image.getWidth() / 2;
        yPos = image.getHeight() / 2;
        drawing = false;
        rotation = 180.0;
        color = Color.BLACK;
        zoomX = 1.0;
        zoomY = 1.0;

    }

    public TurtlePainter(int w, int h) {
        this(getEmptyImage(w, h));
    }

    public void turn(final double angle) {
        rotation -= angle;
        if (rotation >= 360 || rotation < 0) {
            rotation = rotation % 360;
        }
        assert (rotation < 360 && rotation >= 0);
    }

    public void forward(final double l) {

        final double endX = xPos + Math.sin(deg2rad(rotation)) * l * zoomX;
        final double endY = yPos + Math.cos(deg2rad(rotation)) * l * zoomY;
        moveto(endX, endY);
    }

    public void moveto(double x, double y) {
        if (drawing) {
            graphics.setColor(color);
            graphics.drawLine((int) xPos, (int) yPos, (int) x, (int) y);
        }
        xPos = x;
        yPos = y;
    }

    static final double deg2rad(final double rotation) {
        return rotation * Math.PI / 180.0;
    }

    public void startDrawing() {
        this.drawing = true;
    }

    public void stopDrawing() {
        this.drawing = false;
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double angle) {
        this.rotation = angle;
    }
}
