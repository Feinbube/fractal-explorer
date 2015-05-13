package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Blur extends Variation {

    public Blur(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double v = params.twoPi * rnd();
        double rand = rnd();
        return xy(rand * Math.cos(v), rand * Math.sin(v));
    }
}
