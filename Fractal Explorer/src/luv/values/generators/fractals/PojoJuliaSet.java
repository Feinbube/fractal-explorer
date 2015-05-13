package luv.values.generators.fractals;

import luv.values.generators.ValueGenerator;
import luv.math.ComplexF;
import luv.values.Property;

public class PojoJuliaSet extends ValueGenerator {

    int maxIterations = 50;

    float cRe = -0.7f;
    float cIm = 0.27015f;

    @Override
    public float at(double x, double y) {
        final int numIterations = juliaIterations(new ComplexF(cRe, cIm), new ComplexF((float) x, (float) y));
        return (float) numIterations / maxIterations;
    }

    private int juliaIterations(ComplexF c, ComplexF z) {
        for (int t = 0; t < maxIterations; t++) {
            if (z.abs() > 2.0) {
                return t;
            }
            z = z.times(z).plus(c);
        }
        return maxIterations - 1;
    }

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("maxIterations", maxIterations, 0, 10000, 1, 50),
            new Property("cRe", cRe, -10f, 10f, 0.01f, -0.7f),
            new Property("cIm", cIm, -10f, 10f, 0.01f, 0.27015f)
        };
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("maxIterations")) {
            maxIterations = (int) value;
        }
        if (name.equals("cRe")) {
            cRe = value;
        }
        if (name.equals("cIm")) {
            cIm = value;
        }
    }
}
