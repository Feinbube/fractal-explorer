package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Blob extends Variation {

    double blobLow;
    double waves;
    
    double blobHighMinusBlobLowDivTwo;
    
    public Blob(Random random) {
        super(random);
        
        double blobHigh = rnd(0.2, 0.7);
        blobLow = rnd(0.8, 1.2);
        waves = (int) rnd(2, 7);
        
        blobHighMinusBlobLowDivTwo = (blobHigh - blobLow) / 2;
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double v = params.r * (blobLow + blobHighMinusBlobLowDivTwo * (Math.sin(waves * params.arctanXdivY) + 1));
        return xy(v * params.cosArctanXdivY, v * params.sinArctanXdivY);
    }
}
