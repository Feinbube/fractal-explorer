package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Bubble extends Variation {

    public Bubble(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double _4r4 = 4 / (params.r * params.r + 4);
        return xy(_4r4 * params.x, _4r4 * params.y);
    }
}
