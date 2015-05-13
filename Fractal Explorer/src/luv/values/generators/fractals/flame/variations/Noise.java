package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Noise extends Variation {

    public Noise(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double v = params.twoPi * rnd();
        double rand = rnd();
        return xy(rand * params.x * Math.cos(v), rand * params.y * Math.sin(v));
    }
}
