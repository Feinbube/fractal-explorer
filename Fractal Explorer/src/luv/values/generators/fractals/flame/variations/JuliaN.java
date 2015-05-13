package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class JuliaN extends Variation {

    double power;
    double distDivPower;
    
    public JuliaN(Random random) {
        super(random);
        
        power = rnd(2, 6);
        double dist = rnd(0.1, 1.0);
        distDivPower = dist / power;
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double p3 = trunc(Math.abs(power) * rnd());
        final double t = (params.arctanYdivX + params.twoPi * p3) / power;
        final double r_ = Math.pow(params.r, distDivPower);
        return xy(r_ * Math.cos(t), r_ * Math.sin(t));
    }
}
