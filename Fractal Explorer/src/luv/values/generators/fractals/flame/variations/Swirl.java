package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Swirl extends Variation {

    public Swirl(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double sinR2 = Math.sin(params.rSquare);
        double cosR2 = Math.cos(params.rSquare);
        return xy(params.x * sinR2 - params.y * cosR2, params.x * cosR2 + params.y * sinR2);
    }
}
