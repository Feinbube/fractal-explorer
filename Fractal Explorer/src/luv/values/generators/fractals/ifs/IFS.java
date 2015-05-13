package luv.values.generators.fractals.ifs;

import java.util.Random;
import luv.values.Property;
import luv.values.generators.ValueGenerator;

// http://introcs.cs.princeton.edu/java/22library/IFS.java.html
public abstract class IFS extends ValueGenerator {

    Random random = new Random();
    int iterations;

    public IFS() {
        iterations = getIterations();
    }

    @Override
    protected float[] generateValues(double x0, double y0, double x1, double y1, int w, int h) {
        float[] result = new float[w * h];
        double[] dist = getRuleProbablisties();

        double[][] cx = getXMatrix();
        double[][] cy = getYMatrix();

        double x = 0.0, y = 0.0;

        for (int t = 0; t < iterations; t++) {

            // pick a random rule according to the probability distribution
            int r = getFunction(dist);

            // do the update
            double xt = cx[r][0] * x + cx[r][1] * y + cx[r][2];
            double yt = cy[r][0] * x + cy[r][1] * y + cy[r][2];
            x = xt;
            y = yt;

            int xPos = (int) ((x - x0) * w / (x1 - x0));
            int yPos = (int) (((1.0 - y) - y0) * h / (y1 - y0));

            if (t > 20 && xPos >= 0 && xPos < w && yPos >= 0 && yPos < h) {
                result[xPos + yPos * w] = 1.0f; // t / (float)iterations; //1.0f;
            }
        }

        return result;
    }

    protected abstract int getIterations();

    protected abstract double[] getRuleProbablisties();

    protected abstract double[][] getXMatrix();

    protected abstract double[][] getYMatrix();

    private int getFunction(double[] dist) {
        double rnd = random.nextDouble();

        double acc = 0.0f;
        for (int i = 0; i < dist.length; i++) {
            acc += dist[i];
            if (rnd < acc) {
                return i;
            }
        }
        return dist.length - 1;
    }

    protected double[] fromString1D(String s) {
        String[] resultString = s.trim().split("\\s+");
        double[] result = new double[resultString.length];
        for (int i = 0; i < resultString.length; i++) {
            result[i] = Double.parseDouble(resultString[i]);
        }

        return result;
    }

    protected double[][] fromString2D(String s) {
        String[] resultString = s.split("\n");
        double[][] result = new double[resultString.length][];
        for (int i = 0; i < resultString.length; i++) {
            result[i] = fromString1D(resultString[i]);
        }

        return result;
    }

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("iterations", iterations, 0, 1000000, 1, getIterations())
        };
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("iterations")) {
            iterations = (int) value;
        }
    }
}
