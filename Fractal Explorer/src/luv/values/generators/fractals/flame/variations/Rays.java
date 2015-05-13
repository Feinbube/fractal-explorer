package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Rays extends Variation {

    public Rays(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double k = (Math.tan(rnd() * Math.PI)) / params.rSquare;
        return xy(k * Math.cos(params.x), k * Math.sin(params.y));
    }
}
