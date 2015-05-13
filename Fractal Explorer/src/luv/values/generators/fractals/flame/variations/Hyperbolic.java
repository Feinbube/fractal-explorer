package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Hyperbolic extends Variation {

    public Hyperbolic(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
         return xy(params.sinArctanXdivY * params.rReciprocal, params.r * params.cosArctanXdivY);
    }
}
