package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class PDJ extends Variation {

    double a, b, c, d;
    
    public PDJ(Random random) {
        super(random);
        
        a = rnd(-3, 3);
        b = rnd(-3, 3);
        c = rnd(-3, 3);
        d = rnd(-3, 3);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        return xy(Math.sin(a * params.y) - Math.cos(b * params.x), Math.sin(c * params.x) - Math.cos(d * params.y));
    }
}
