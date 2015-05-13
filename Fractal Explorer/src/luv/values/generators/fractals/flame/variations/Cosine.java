package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Cosine extends Variation {

    public Cosine(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double pix = Math.PI * params.x;
        return xy(Math.cos(pix) * Math.cosh(params.y), -Math.sin(pix) * Math.sinh(params.y));
    }
}
