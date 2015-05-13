package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Heart extends Variation {

    public Heart(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double arctanXdivYTimesR = params.arctanXdivY * params.r;
        return xy(params.r * Math.sin(arctanXdivYTimesR), params.r * -Math.cos(arctanXdivYTimesR));
    }
}
