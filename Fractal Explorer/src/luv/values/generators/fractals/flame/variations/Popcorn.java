package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Popcorn extends Variation {

    public Popcorn(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        return xy(params.x + params.c * Math.sin(Math.tan(3 * params.y)), params.y + params.f * Math.sin(Math.tan(3 * params.x)));
    }
}
