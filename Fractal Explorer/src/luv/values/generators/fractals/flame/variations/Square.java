package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Square extends Variation {

    public Square(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        return xy(rnd() - 0.5, rnd() - 0.5);
    }
}
