package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Fan2 extends Variation {

    double fany;

    double p1;
    double p12;

    public Fan2(Random random) {
        super(random);

        double fanx = getAffinetransformCoefficient();
        fany = getAffinetransformCoefficient();

        p1 = Math.PI * fanx * fanx;
        p12 = p1 / 2;
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double t = params.arctanXdivY + fany - p1 * trunc((params.arctanXdivY + fany) / p1);
        double angle = t > p12 ? params.arctanXdivY - p12 : params.arctanXdivY + p12;
        return xy(params.r * Math.sin(angle), params.r * Math.cos(angle));
    }
}
