package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Eyefish extends Variation {

    public Eyefish(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        return xy(params.twoDivRPlusOneTimesX, params.twoDivRPlusOneTimesY);
    }
}
