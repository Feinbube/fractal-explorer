package luv.values.generators.fractals;

import luv.values.generators.ValueGenerator;
import luv.math.Complex;
import luv.values.Property;

public class PojoNewtonFractal extends ValueGenerator {

    int maxIterations = 50;

    static final Complex a = new Complex(-0.7, 0.3);

    @Override
    public float at(double x, double y) {
        final int numIterations = newtonIterations(new Complex(x, y));
        return (float) numIterations / maxIterations;
    }

    private int newtonIterations(Complex z) {

        for (int t = 0; t < maxIterations; t++) {
            if (z.abs() > 2.0) {
                return t;
            }

            z = a.minus(polynomial(z).divides(polynomialDerivative(z)));
            //z = z.minus(z.times(z).times(z).times(a).minus(c));
        }
        return maxIterations - 1;
    }

    static private Complex polynomial(Complex a) {
        final Complex one = new Complex(1.0, 0.0);
        return a.times(a).times(a).times(a).minus(one);
    }

    static private Complex polynomialDerivative(Complex a) {
        return a.times(a).times(3);
    }

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("maxIterations", maxIterations, 0, 10000, 1, 50),};
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("maxIterations")) {
            maxIterations = (int) value;
        }
    }
}
