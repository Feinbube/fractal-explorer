package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Horseshoe extends Variation {

    public Horseshoe(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        return xy((params.x - params.y) * (params.x + params.y) * params.rReciprocal, params.twoTimesXTimesY * params.rReciprocal);
    }
}
