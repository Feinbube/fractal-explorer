package luv.values.generators.fractals.ifs.fractint;

// introcs.cs.princeton.edu/java/22library/

import luv.values.generators.fractals.ifs.IFS;

public class PojoDragonIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D("0.787473  0.212527");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                "0.824074  0.281482 -0.1002660\n"
                + "   0.088272  0.520988  0.5344000"
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                " -0.212346  0.864198  0.0951123\n"
                + "  -0.463889 -0.377778  1.0415240"
        );
    }
}
