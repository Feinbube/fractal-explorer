package luv.values.generators.fractals.flame;

import java.util.Random;
import luv.values.Named;
import luv.values.generators.fractals.flame.variations.*;

public abstract class Variation extends Named {

    Random random;

    public Variation(Random random) {
        this.random = random;
    }

    protected double rnd() {
        return random.nextDouble();
    }

    protected double rnd(double min, double max) {
        return min + random.nextDouble() * (max - min);
    }

    protected double getMinusOrOne() {
        return rnd() < 0.5 ? -1 : 1;
    }

    protected double getZeroOrPi() {
        return rnd() < 0.5 ? 0 : Math.PI;
    }

    protected double getAffinetransformCoefficient() {
        return rnd(-1.0, 1.0);
    }

    protected double trunc(double value) {
        return (int) value;
    }

    protected double[] xy(double x, double y) {
        return new double[]{x, y};
    }

    public abstract double[] getXY(VariationParameters params);

    public static Variation[] getVariations(Random random) {
        return new Variation[]{
            new Linear(random),
            new Sinusodial(random),
            new Spherical(random),
            new Swirl(random),
            new Polar(random),
            new Handkerchief(random),
            new Heart(random),
            new Disc(random),
            new Spiral(random),
            new Hyperbolic(random),
            new Diamond(random),
            new Ex(random),
            new Julia(random),
            new Bent(random),
            new Waves(random),
            new Fisheye(random),
            new Popcorn(random),
            new Exponential(random),
            new Power(random),
            new Cosine(random),
            new Rings(random),
            new Fan(random),
            new Blob(random),
            new PDJ(random),
            new Fan2(random),
            new Rings2(random),
            new Eyefish(random),
            new Bubble(random),
            new Cylinder(random),
            new Perspective(random),
            new Noise(random),
            new JuliaN(random),
            new JuliaScope(random),
            new Blur(random),
            new Gaussian(random),
            new RadialBlur(random),
            new Pie(random),
            new Ngon(random),
            new Curl(random),
            new Rectangles(random),
            new Arch(random),
            new Tangent(random),
            new Square(random),
            new Rays(random),
            new Blade(random),
            new Secant(random),
            new Twintrian(random),
            new Cross(random)
        };
    }
}
