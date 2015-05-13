package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Blade extends Variation {

    public Blade(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double v = rnd() * params.r;
        double cos = Math.cos(v);
        double sin = Math.sin(v);
        return xy(params.x * (cos + sin), params.x * (cos - sin));
    }
}
