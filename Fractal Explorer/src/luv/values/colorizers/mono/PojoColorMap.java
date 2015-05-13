package luv.values.colorizers.mono;

import luv.values.colorizers.Colorizer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import luv.graphics.colors.ColorAt;

public class PojoColorMap extends Colorizer {

    List<ColorAt> colors;

    public PojoColorMap() {
        colors = new ArrayList<>();
        initColorMap(32, Color.RED, Color.GREEN, Color.BLUE);
    }

    protected void initColorMap(int stepSize, Color... colors) {
        this.colors.add(new ColorAt(0.0f, Color.BLACK));

        int index = 1;
        for (int i = 0; i < colors.length - 1; i++) {
            Color c0 = colors[i];
            int r0 = c0.getRed();
            int g0 = c0.getGreen();
            int b0 = c0.getBlue();

            Color c1 = colors[i + 1];
            int r1 = c1.getRed();
            int g1 = c1.getGreen();
            int b1 = c1.getBlue();

            int dr = r1 - r0;
            int dg = g1 - g0;
            int db = b1 - b0;

            for (int j = 0; j < stepSize; j++) {
                float alpha = (float) j / (stepSize - 1);
                int r = (int) (r0 + alpha * dr);
                int g = (int) (g0 + alpha * dg);
                int b = (int) (b0 + alpha * db);
                this.colors.add(new ColorAt(index / (float) (stepSize * (colors.length - 1)), new Color(r, g, b)));
                index++;
            }
        }
    }

    @Override
    protected Color getColor(float value) {
        return ColorAt.getColor(colors, value);
    }
}
