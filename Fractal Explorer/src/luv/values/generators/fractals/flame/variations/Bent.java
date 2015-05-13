package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Bent extends Variation {

    public Bent(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        return xy(params.x >= 0 ? params.x : 2 * params.x, params.y >= 0 ? params.y : params.y / 2);
    }
}
