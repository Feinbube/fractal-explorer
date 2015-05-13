package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Julia extends Variation {

    double zeroOrPi;
    
    public Julia(Random random) {
        super(random);
        
        zeroOrPi = getZeroOrPi();
    }

    @Override
    public double[] getXY(VariationParameters params) {  
        double sqrtR = Math.sqrt(params.r);
        double angle = params.arctanXdivYdiv2 + zeroOrPi;
        return xy(sqrtR * Math.cos(angle), sqrtR * Math.sin(angle));
    }
}
