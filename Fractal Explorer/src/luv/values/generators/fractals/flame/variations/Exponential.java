package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Exponential extends Variation {

    public Exponential(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double exp = Math.exp(params.x - 1);
        double piy = Math.PI * params.y;
        return xy(exp * Math.cos(piy), exp * Math.sin(piy));
    }
}
