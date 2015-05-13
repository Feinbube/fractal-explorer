package luv.values.colorizers.mono;

import luv.values.colorizers.Colorizer;
import java.awt.Color;

public class PojoHSBScale extends Colorizer {

    @Override
    protected Color getColor(float value) {
        return Color.getHSBColor(value, 1.0f, 1.0f);
        // HSVtoRGB(ColorHSV(i % 256, 255, 255 * (i < maxIterations)));
    }
}
