package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Spherical extends Variation {

    public Spherical(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        return xy(params.x / params.rSquare, params.y / params.rSquare);
    }
}
