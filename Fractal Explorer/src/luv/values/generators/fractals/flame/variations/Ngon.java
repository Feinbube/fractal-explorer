package luv.values.generators.fractals.flame.variations;

import luv.values.generators.fractals.flame.VariationParameters;
import luv.values.generators.fractals.flame.Variation;
import java.util.Random;

public class Ngon extends Variation {

    double power;
    double sides;
    double corners;
    double circle;

    double sidesDiv2;
    
    public Ngon(Random random) {
        super(random);

        power = 3;
        sides = 2 * Math.PI / 10;
        corners = 0.0;
        circle = rnd();
        
        sidesDiv2 = sides / 2;
    }

    @Override
    public double[] getXY(VariationParameters params) {
        double t3 = params.arctanYdivX - sides * trunc(params.arctanYdivX / sides);
        double t4 = (t3 > sidesDiv2) ? t3 : t3 - sides;
        double k = (corners * (1.0 / Math.cos(t4) - 1.0) + circle) / Math.pow(params.r, power);

        return xy(k * params.x, k * params.y);
    }
}
