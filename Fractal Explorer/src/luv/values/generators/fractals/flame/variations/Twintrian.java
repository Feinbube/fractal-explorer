package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Twintrian extends Variation {

    public Twintrian(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double v = rnd() * params.r;
        double sin = Math.sin(v);
        double t = Math.log10(sin * sin) + Math.cos(v);
        return xy(params.x * t, params.x * (t - Math.PI * sin));
    }
}
