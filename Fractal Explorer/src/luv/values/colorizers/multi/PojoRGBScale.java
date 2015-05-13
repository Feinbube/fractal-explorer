package luv.values.colorizers.multi;

import java.awt.Color;
import luv.values.colorizers.Colorizer;

public class PojoRGBScale extends Colorizer {

    @Override
    protected int getColorValue(float... values) {
        return new Color(values[0], values[1 % values.length], values[2 % values.length], 1.0f).hashCode();
    }
}
