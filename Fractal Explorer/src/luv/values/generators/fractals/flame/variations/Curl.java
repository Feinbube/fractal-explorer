package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Curl extends Variation {

    double c1;
    double c2;
    
    public Curl(Random random) {
        super(random);
        
        c1 = getAffinetransformCoefficient();
        c2 = getAffinetransformCoefficient();
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double t1 = 1.0 + c1 * params.x + c2 * params.xTimesXMinusYTimesY;
        double t2 = c1 * params.y + c2 * params.twoTimesXTimesY;
        double k = 1.0 / (t1 * t1 + t2 * t2);
        return xy(k * (params.x * t1 + params.y * t2), k * (params.y * t1 - params.x * t2));
    }
}
