package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Disc extends Variation {

    public Disc(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double angleDivPi = params.arctanXdivY / Math.PI;
        double piR = Math.PI * params.r;
        return xy(angleDivPi * Math.sin(piR), angleDivPi * Math.cos(piR));
    }
}
