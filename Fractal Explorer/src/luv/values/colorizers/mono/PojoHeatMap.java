package luv.values.colorizers.mono;

import luv.values.colorizers.Colorizer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import luv.graphics.colors.ColorAt;

public class PojoHeatMap extends Colorizer {

    List<ColorAt> colors;

    public PojoHeatMap() {
        colors = new ArrayList<>();
        colors.add(new ColorAt(0.0f, new Color(0, 0, 0)));
        colors.add(new ColorAt(0.17f, new Color(0, 0, 255)));
        colors.add(new ColorAt(0.33f, new Color(0, 255, 255)));
        colors.add(new ColorAt(0.5f, new Color(0, 255, 0)));
        colors.add(new ColorAt(0.67f, new Color(255, 255, 0)));
        colors.add(new ColorAt(0.83f, new Color(255, 0, 0)));
        colors.add(new ColorAt(1.0f, new Color(255, 255, 255)));
    }

    @Override
    protected Color getColor(float value) {
        return ColorAt.getColor(colors, value);
    }
}
