package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class RadialBlur extends Variation {

    double angle;
    double sinAngle;
    double cosAngle;
    
    public RadialBlur(Random random) {
        super(random);
        
        angle = getAffinetransformCoefficient() * (Math.PI / 2.0);
        sinAngle = Math.sin(angle);
        cosAngle = Math.cos(angle);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double t1 = rnd() + rnd() + rnd() + rnd() - 2;
        double t2 = params.arctanYdivX + t1 * sinAngle;
        double t3 = t1 * cosAngle - 1;

        return xy(1 / angle * (params.r * Math.cos(t2) + t3 * params.x), 1 / angle * (params.r * Math.sin(t2) + t3 * params.y));
    }
}
