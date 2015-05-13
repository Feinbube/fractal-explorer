package luv.values.generators.fractals;

import luv.values.Property;
import luv.values.generators.ValueGenerator;

public class PojoMandelbrotSet extends ValueGenerator {

    int maxIterations = 50;

    @Override
    public float at(double x, double y) {
        final int numIterations = mandelbrotIterations((float) x, (float) y);
        return (float) numIterations / maxIterations;
    }

    private int mandelbrotIterations(float x, float y) {
        float absSquare = 0;
        int iterations = 0;

        float x0 = 0;
        float y0 = 0;
        float x1;
        float y1;

        while (absSquare <= 4.0f && iterations < maxIterations) {
            x1 = x0 * x0 - y0 * y0 + x;
            y1 = 2.f * x0 * y0 + y;

            x0 = x1;
            y0 = y1;

            absSquare = x0 * x0 + y0 * y0;

            ++iterations;
        }
        return iterations;
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
