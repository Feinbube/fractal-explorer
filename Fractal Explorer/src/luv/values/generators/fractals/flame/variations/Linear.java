package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Linear extends Variation {

    public Linear(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        return xy(params.x, params.y);
    }
}
