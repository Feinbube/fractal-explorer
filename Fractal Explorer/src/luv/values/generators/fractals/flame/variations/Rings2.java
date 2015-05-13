package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Rings2 extends Variation {

    double valSquared;
    double twoTimesValSquared;
    double oneMinusValSquared;

    public Rings2(Random random) {
        super(random);

        double val = rnd(0.0, 2.0);
        valSquared = val * val + Double.MIN_VALUE;
        twoTimesValSquared = 2 * valSquared;
        oneMinusValSquared = 1- valSquared;
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double t = params.r - twoTimesValSquared * trunc((params.r + valSquared) / twoTimesValSquared) + params.r * oneMinusValSquared;
        return xy(t * params.sinArctanXdivY, t * params.cosArctanXdivY);
    }
}
