package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Rectangles extends Variation {

    double rectanglesX;
    double rectanglesY;
    
    public Rectangles(Random random) {
        super(random);
        
        rectanglesX = rnd(-0.3, 0.3);
        rectanglesY = rnd(-0.3, 0.3);
    }

    @Override
    public double[] getXY(VariationParameters params) {
        return xy((2.0 * trunc(params.x / rectanglesX) + 1) * rectanglesX - params.x, (2.0 * trunc(params.y / rectanglesY) + 1) * rectanglesY - params.y);
    }
}
