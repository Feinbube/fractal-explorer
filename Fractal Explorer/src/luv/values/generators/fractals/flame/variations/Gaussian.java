package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Gaussian extends Variation {

    public Gaussian(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double sum = (rnd() + rnd() + rnd() + rnd()) - 2.0;
        double v = params.twoPi * rnd();
        return xy(sum * Math.cos(v), sum * Math.sin(v));
    }
}
