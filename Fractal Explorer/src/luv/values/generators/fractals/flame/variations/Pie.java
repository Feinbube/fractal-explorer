package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Pie extends Variation {

    double slices;
    double rotation;
    double thickness;
    
    double twoPiDivSlices;

    public Pie(Random random) {
        super(random);
        
        slices = rnd(2, 6);
        rotation = getAffinetransformCoefficient();
        thickness = rnd();
        
        twoPiDivSlices = 2 * Math.PI / slices;
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double t1 = trunc(rnd() * slices + 0.5);
        double t2 = rotation + twoPiDivSlices * (t1 + rnd() * thickness);
        double rand = rnd();
        return xy(rand * Math.cos(t2), rand * Math.sin(t2));
    }
}
