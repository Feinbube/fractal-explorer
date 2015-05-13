package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Fan extends Variation {

    public Fan(Random random) {
        super(random);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double cond = (params.arctanXdivY + params.f) / params.cSquareTimesPi;
        double angle = cond < 0 || cond - trunc(cond) <= 0.5 ? params.arctanXdivY + params.cSquareTimesPiDivTwo : params.arctanXdivY - params.cSquareTimesPiDivTwo;
        return xy(params.r * Math.cos(angle), params.r * Math.sin(angle));
    }
}
