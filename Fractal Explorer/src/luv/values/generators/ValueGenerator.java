package luv.values.generators;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.Serializable;

import luv.values.Configurable;
import luv.values.Property;

public abstract class ValueGenerator extends Configurable implements Serializable {

    private static final long serialVersionUID = -7309017890811745885L;
    protected double xOffset = 0.0;
    protected double yOffset = 0.0;
    protected double zoom = 1.0;

    public float[] getValues(double x0, double y0, double x1, double y1, int w, int h) {
        x0 = (x0 - xOffset) / zoom;
        x1 = (x1 - xOffset) / zoom;
        y0 = (y0 - yOffset) / zoom;
        y1 = (y1 - yOffset) / zoom;
        
        return this.generateValues(x0, y0, x1, y1, w, h);
    }
    
    protected float[] generateValues(double x0, double y0, double x1, double y1, int w, int h) {
        float[] values = new float[w * h];

        double stepX = (x1 - x0) / w;
        double stepY = (y1 - y0) / h;

        for (int yImage = 0; yImage < h; ++yImage) {
            for (int xImage = 0; xImage < w; ++xImage) {
                values[yImage * w + xImage] = at(x0 + xImage * stepX, y0 + yImage * stepY);
            }
        }

        return values;
    }

    protected float at(double x, double y) {
        return 0.0f;
    }

    protected float[] toValues(BufferedImage image) {
        int data[] = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int value;
        for (int i = 0; i < data.length; i++) {
            value = data[i];
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }

        float[] result = new float[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = min != max ? (data[i] - min) / (max - min) : 1;
        }

        return result;
    }

    @Override
    public Property[] getProperties() {
        return Property.Merge(new Property[]{
            new Property("xOffset", (float) xOffset, -100f, 100f, 0.000001f, 0.0f),
            new Property("yOffset", (float) yOffset, -100f, 100f, 0.000001f, 0.0f),
            new Property("zoom", (float) zoom, 0.1f, 10f, 0.1f, 1.0f)
        }, getAdditionalProperties());
    }

    protected Property[] getAdditionalProperties() {
        return new Property[]{};
    }

    @Override
    public void setProperty(String name, float value) {
        if (name.equals("xOffset")) {
            xOffset = value;
        }
        if (name.equals("yOffset")) {
            yOffset = value;
        }
        if (name.equals("zoom")) {
            zoom = value;
        }

        setAdditionalProperty(name, value);
    }

    protected void setAdditionalProperty(String name, float value) {
    }

    public ValueGenerator setup(double xOffset, double yOffset, double zoom) {
        this.setProperty("xOffset", (float) xOffset);
        this.setProperty("yOffset", (float) yOffset);
        this.setProperty("zoom", (float) zoom);
        return this;
    }
}
