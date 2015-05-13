package luv.values.colorizers;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;
import luv.values.Configurable;
import luv.values.Property;
import luv.values.generators.ValueGenerator;

public abstract class Colorizer extends Configurable {

    public BufferedImage getImage(double x0, double y0, double x1, double y1, int w, int h, ValueGenerator... valueGenerators) {
        return blendImage(new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB), x0, y0, x1, y1, valueGenerators);
    }

    public BufferedImage blendImage(BufferedImage image, double x0, double y0, double x1, double y1, ValueGenerator... valueGenerators) {
        int data[] = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        List<float[]> valuesList = new ArrayList<>();
        for (ValueGenerator valueGenerator : valueGenerators) {
            valuesList.add(valueGenerator.getValues(x0, y0, x1, y1, image.getWidth(), image.getHeight()));
        }

        for (int i = 0; i < data.length; i++) {
            float[] values = new float[valuesList.size()];
            for (int vIndex = 0; vIndex < valuesList.size(); vIndex++) {
                values[vIndex] = valuesList.get(vIndex)[i];
            }
            data[i] = getColorValue(values);
        }
        return image;
    }

    protected int getColorValue(float... values) {
        return getColorValue(values[0]);
    }

    protected int getColorValue(float value) {
        return getColor(value).hashCode();
    }

    protected Color getColor(float value) {
        return Color.BLACK;
    }

    @Override
    public Property[] getProperties() {
        return new Property[]{};
    }

    @Override
    public void setProperty(String name, float value) {
    }
}
