package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Ex extends Variation {

    public Ex(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double p0 = params.sinArctanXdivYPlusR;
        double p03 = p0 * p0 * p0;
        double p1 = params.cosArctanXdivYMinusR;
        double p13 = p1 * p1 * p1;
        return xy(params.r * (p03 + p13), params.r * (p03 - p13));
    }
}
