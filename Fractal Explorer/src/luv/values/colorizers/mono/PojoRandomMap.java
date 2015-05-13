package luv.values.colorizers.mono;

import luv.values.colorizers.Colorizer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import luv.graphics.colors.ColorAt;

public class PojoRandomMap extends Colorizer {

    List<ColorAt> colors;

    public PojoRandomMap(int numColors) {
        final float goldenRatio = 0.618033988749895f;

        Random rand = new Random(System.currentTimeMillis());
        colors = new ArrayList<>();

        for (int i = 1; i <= numColors; ++i) {
            colors.add(new ColorAt(i / (float) numColors, Color.getHSBColor((float) i * goldenRatio, rand.nextFloat(), 0.5f + rand.nextFloat() / 2.f)));
        }
    }

    @Override
    protected Color getColor(float value) {
        return ColorAt.getColor(colors, value);
    }
}
