package luv.graphics.images;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImagePainter {

	protected final BufferedImage image;
	protected final Graphics graphics;
	protected Color color;
	protected double zoomX;
	protected double zoomY;
	
	public ImagePainter(final int w, final int h) {
		this.image = getEmptyImage(w, h);
		this.graphics = image.getGraphics();
	}
	
	public ImagePainter(BufferedImage image) {
        this.image = image;
        this.graphics = image.getGraphics();
	}

	protected static BufferedImage getEmptyImage(final int w, final int h) {
	    BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    result.getGraphics().setColor(Color.WHITE);
	    result.getGraphics().fillRect(0, 0, w, h);
	
	    return result;
	}

	public void setColor(final Color c) {
	    graphics.setColor(c);
	}

	public BufferedImage getImage() {
	    return image;
	}

	public void setZoom(double zoomX, double zoomY) {
	    this.zoomX = zoomX;
	    this.zoomY = zoomY;
	}

	public void setPixelColor(final int x, final int y, Color c) {
		image.setRGB(x, y, c.getRGB());
	}
}
