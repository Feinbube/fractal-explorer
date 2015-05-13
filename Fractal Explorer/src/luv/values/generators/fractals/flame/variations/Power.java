package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Power extends Variation {

    public Power(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double powr = Math.pow(params.r, params.sinArctanXdivY);
        return xy(powr * params.cosArctanXdivY, powr * params.sinArctanXdivY);
    }
}
