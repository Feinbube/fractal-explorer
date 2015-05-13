package luv.values.colorizers.mono;

import luv.values.colorizers.Colorizer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import luv.graphics.colors.ColorAt;

public class PojoRainbowMap extends Colorizer {

    List<ColorAt> rainbowColors;

    public PojoRainbowMap() {
        rainbowColors = new ArrayList<>();
        rainbowColors.add(new ColorAt(0.0f, new Color(255, 0, 0)));
        rainbowColors.add(new ColorAt(0.2f, new Color(255, 255, 0)));
        rainbowColors.add(new ColorAt(0.4f, new Color(0, 255, 0)));
        rainbowColors.add(new ColorAt(0.6f, new Color(0, 255, 255)));
        rainbowColors.add(new ColorAt(0.8f, new Color(0, 0, 255)));
        rainbowColors.add(new ColorAt(1.0f, new Color(255, 0, 255)));
    }

    @Override
    protected Color getColor(float value) {
        return ColorAt.getColor(rainbowColors, value);
    }
}
