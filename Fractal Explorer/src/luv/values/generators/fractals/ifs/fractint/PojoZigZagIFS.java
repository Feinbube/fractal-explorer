package luv.values.generators.fractals.ifs.fractint;

// introcs.cs.princeton.edu/java/22library/
import luv.values.generators.fractals.ifs.IFS;

public class PojoZigZagIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D("0.888128  0.111872");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                "-0.632407 -0.614815  1.2002857\n"
                + "  -0.036111  0.444444  0.7251636"
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                "-0.545370  0.659259  0.4009171\n"
                + "   0.210185  0.037037  0.7279627"
        );
    }
}
