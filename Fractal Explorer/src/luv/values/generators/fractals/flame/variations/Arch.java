package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Arch extends Variation {

    public Arch(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double v = rnd() * Math.PI;
        double sine = Math.sin(v);
        return xy(sine, (sine * sine) / Math.cos(v));
    }
}
