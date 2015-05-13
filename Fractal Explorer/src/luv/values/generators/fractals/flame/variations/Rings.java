package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Rings extends Variation {

    public Rings(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double rcccrc = (params.r + params.cSquare) % (2 * params.cSquare) - params.cSquare + params.r * (1 - params.cSquare);
        return xy(rcccrc * params.cosArctanXdivY, rcccrc * params.sinArctanXdivY);
    }
}
