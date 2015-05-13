package luv.values.colorizers.mono;

import luv.values.colorizers.Colorizer;
import java.awt.Color;
import luv.graphics.colors.Colors;

public class PojoGrayScale extends Colorizer {

    @Override
    protected Color getColor(float value) {
        return Colors.interpolate(Color.BLACK, Color.WHITE, value);
    }
}
