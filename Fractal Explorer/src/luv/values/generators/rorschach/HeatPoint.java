package luv.values.generators.rorschach;

import java.util.Random;

public class HeatPoint {

    public double x;
    public double y;
    public double strength;
    public double maxHeat;

    public HeatPoint(Random random, double stretchfactor, int strengthVariation) {
        this(stretch(random.nextDouble(), stretchfactor), stretch(random.nextDouble(), stretchfactor),
                (random.nextInt(strengthVariation + 1) + 1.0) / (strengthVariation + 1.0));
    }

    public HeatPoint(Random random, int stretchfactor, float stretchVariance, int strengthVariation, HeatPoint other) {
        this(stretch(random.nextDouble(), stretchfactor * stretchVariance) + other.x, stretch(random.nextDouble(), stretchfactor * stretchVariance) + other.y,
                other.strength + stretch(random.nextDouble(), strengthVariation / 10.0));
    }

    public HeatPoint(HeatPoint other) {
        this(other.x, other.y, other.strength, other.maxHeat);
    }

    public HeatPoint(double x, double y, double strength) {
        this(x, y, strength, 1.0);
    }
    
    public HeatPoint(double x, double y, double strength, double maxHeat) {
        this.x = x;
        this.y = y;
        this.strength = strength < 0.0 ? 0.0 : strength > 1.0 ? 1.0 : strength;
        this.maxHeat = maxHeat;
    }

    private static double stretch(double value, double stretchfactor) {
        return stretchfactor / 2.0 - value * stretchfactor;
    }
}
