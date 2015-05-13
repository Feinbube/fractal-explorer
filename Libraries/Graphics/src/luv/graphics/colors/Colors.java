package luv.graphics.colors;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Colors {

    private static final double INT_TO_FLOAT_CONST = 1f / 255f;

    public static Color interpolate(Color c1, Color c2, double fraction) {
        return interpolate(c1, c2, fraction, fraction, fraction, fraction);
    }

    // http://harmoniccode.blogspot.de/2011/04/bilinear-color-interpolation.html
    public static Color interpolate(Color c1, Color c2, double fractionR, double fractionG, double fractionB, double fractionA) {

        fractionR = Math.min(fractionR, 1f);
        fractionR = Math.max(fractionR, 0f);
        fractionG = Math.min(fractionG, 1f);
        fractionG = Math.max(fractionG, 0f);
        fractionB = Math.min(fractionB, 1f);
        fractionB = Math.max(fractionB, 0f);
        fractionA = Math.min(fractionA, 1f);
        fractionA = Math.max(fractionA, 0f);

        final double r1 = c1.getRed() * INT_TO_FLOAT_CONST;
        final double g1 = c1.getGreen() * INT_TO_FLOAT_CONST;
        final double b1 = c1.getBlue() * INT_TO_FLOAT_CONST;
        final double a1 = c1.getAlpha() * INT_TO_FLOAT_CONST;

        final double r2 = c2.getRed() * INT_TO_FLOAT_CONST;
        final double g2 = c2.getGreen() * INT_TO_FLOAT_CONST;
        final double b2 = c2.getBlue() * INT_TO_FLOAT_CONST;
        final double a2 = c2.getAlpha() * INT_TO_FLOAT_CONST;

        final double d_r = r2 - r1;
        final double d_g = g2 - g1;
        final double d_b = b2 - b1;
        final double d_a = a2 - a1;

        double red = r1 + (d_r * fractionR);
        double green = g1 + (d_g * fractionG);
        double blue = b1 + (d_b * fractionB);
        double alpha = a1 + (d_a * fractionA);

        return newColor(red, green, blue, alpha);
    }

    public static Color transparent(Color c, double fraction) {
        final double r = c.getRed() * INT_TO_FLOAT_CONST;
        final double g = c.getGreen() * INT_TO_FLOAT_CONST;
        final double b = c.getBlue() * INT_TO_FLOAT_CONST;
        final double a = fraction;

        return newColor(r, g, b, a);
    }

    public static Color newColor(double r, double g, double b) {
        return newColor(r, g, b, 1.0);
    }

    public static Color newColor(double r, double g, double b, double a) {
        r = Math.min(r, 1f);
        r = Math.max(r, 0f);
        g = Math.min(g, 1f);
        g = Math.max(g, 0f);
        b = Math.min(b, 1f);
        b = Math.max(b, 0f);
        a = Math.min(a, 1f);
        a = Math.max(a, 0f);

        return new Color((float) r, (float) g, (float) b, (float) a);
    }

    public static List<Color> createColorMap() {
        return createColorMap(32, Color.RED, Color.GREEN, Color.BLUE);
    }

    public static List<Color> createColorMap(int stepSize, Color... colors) {
        List<Color> result = new ArrayList<>();

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
                result.add(new Color(r, g, b));
            }
        }

        return result;
    }

    static float[] gammaLookUpTable;

    public static Color gamma(Color c) {
        if (gammaLookUpTable == null) {
            gammaLookUpTable = new float[256];
            for (int i = 0; i < 256; i++) {
                gammaLookUpTable[i] = (float)Math.pow(i / 255.0, 1.0 / 2.2);
            }
        }

        return newColor(gammaLookUpTable[c.getRed()], gammaLookUpTable[c.getGreen()], gammaLookUpTable[c.getBlue()]);
    }
}
