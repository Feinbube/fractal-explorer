package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Cross extends Variation {

    public Cross(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double k = Math.sqrt(1.0 / (params.xTimesXMinusYTimesY * params.xTimesXMinusYTimesY));
        return xy(k * params.x, k * params.y);
    }
}
