package luv.values.generators.fractals.ifs.fractint;

// introcs.cs.princeton.edu/java/22library/

import luv.values.generators.fractals.ifs.IFS;

public class PojoFractIntIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D("0.3333  0.3333  0.3334");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                "   0.5  0.0 -0.0063477\n"
                + "   0.5  0.0  0.4936544\n"
                + "   0.0 -0.5  0.9873085"
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                "   0.0  0.5 -0.0000003\n"
                + "   0.0  0.5 -0.0000003\n"
                + "   0.5  0.0  0.5063492"
        );
    }
}
