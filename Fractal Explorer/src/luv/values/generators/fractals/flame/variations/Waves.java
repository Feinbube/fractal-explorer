package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Waves extends Variation {

    public Waves(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        return xy(params.x + params.b * Math.sin(params.y / (params.cSquare)), params.y + params.e * Math.sin(params.x / (params.f * params.f)));
    }
}
