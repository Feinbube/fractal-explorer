package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Diamond extends Variation {

    public Diamond(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        return xy(params.sinArctanXdivY * params.cosR, params.cosArctanXdivY * params.sinR);
    }
}
