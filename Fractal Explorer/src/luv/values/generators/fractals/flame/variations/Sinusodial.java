package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Sinusodial extends Variation {

    public Sinusodial(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        return xy(params.sinX, Math.sin(params.y));
    }
}
