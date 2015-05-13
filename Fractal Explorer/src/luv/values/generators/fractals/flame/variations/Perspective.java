package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Perspective extends Variation {

    double angle;
    double dist;
    
    public Perspective(Random random) {
        super(random);
        
        angle = rnd(0, Math.PI / 2);
        dist = rnd(1.0, 3.0);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double v = dist / (dist - params.y * Math.sin(angle));
        return xy(v * params.x, v * params.y * Math.cos(angle));
    }
}
