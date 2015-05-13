package luv.values.colorizers.mono;

import luv.values.colorizers.Colorizer;
import java.awt.Color;
import luv.graphics.colors.Colors;
import luv.values.Property;

public class PojoLinearScale extends Colorizer {

    Color c1;
    Color c2;

    public PojoLinearScale(Color c1, Color c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    protected Color getColor(float value) {
        return Colors.interpolate(c1, c2, value);
    }

    @Override
    public Property[] getProperties() {
        return new Property[]{
            new Property("c1.r", c1.getRed(), 0, 255, 1, 255),
            new Property("c1.g", c1.getGreen(), 0, 255, 1, 0),
            new Property("c1.b", c1.getBlue(), 0, 255, 1, 0),
            new Property("c2.r", c2.getRed(), 0, 255, 1, 0),
            new Property("c2.g", c2.getGreen(), 0, 255, 1, 255),
            new Property("c2.b", c2.getBlue(), 0, 255, 1, 255)
        };
    }

    @Override
    public void setProperty(String name, float value) {
        if (name.equals("c1.r")) {
            c1 = new Color((int) value, c1.getGreen(), c1.getBlue());
        }
        if (name.equals("c1.g")) {
            c1 = new Color(c1.getRed(), (int) value, c1.getBlue());
        }
        if (name.equals("c1.b")) {
            c1 = new Color(c1.getRed(), c1.getGreen(), (int) value);
        }
        if (name.equals("c2.r")) {
            c2 = new Color((int) value, c2.getGreen(), c2.getBlue());
        }
        if (name.equals("c2.g")) {
            c2 = new Color(c2.getRed(), (int) value, c2.getBlue());
        }
        if (name.equals("c2.b")) {
            c2 = new Color(c2.getRed(), c2.getGreen(), (int) value);
        }
    }
}
