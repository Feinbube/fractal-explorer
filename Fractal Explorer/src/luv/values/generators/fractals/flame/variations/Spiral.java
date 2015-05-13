package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Spiral extends Variation {

    public Spiral(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
         return xy((params.cosArctanXdivY + params.sinR) * params.rReciprocal, (params.sinArctanXdivY - params.cosArctanXdivY) * params.rReciprocal);
    }
}
