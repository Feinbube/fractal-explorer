package luv.values.generators.fractals.flame;

import java.util.Random;

public class FunctionTest extends Function {

    public FunctionTest(Random random, int variation) {
        super(random);        

        variations = new Variation[1];
        Variation[] allVariations = Variation.getVariations(random);
        variations[0] = allVariations[variation % allVariations.length];
        weights = new double[]{1.0};
    }

    @Override
    public double[] getXY(double x, double y) {
        double xi = x;
        double yi = y;

        double rx = 0.0;
        double ry = 0.0;

        variationParameters.update(xi, yi);
        
        for (int i = 0; i < variations.length; i++) {
            double[] result = variations[i].getXY(variationParameters);
            rx += weights[i] * result[0];
            ry += weights[i] * result[1];
        }

        return new double[]{rx, ry};
    }
}
