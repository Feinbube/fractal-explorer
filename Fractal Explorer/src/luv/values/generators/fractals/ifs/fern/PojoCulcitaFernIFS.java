package luv.values.generators.fractals.ifs.fern;

// introcs.cs.princeton.edu/java/22library/

import luv.values.generators.fractals.ifs.IFS;

public class PojoCulcitaFernIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D("0.0200 0.8400 0.0700 0.0700");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                "   0.000  0.000  0.500\n"
                + "   0.850  0.020  0.075\n"
                + "   0.090 -0.280  0.455\n"
                + "  -0.090  0.280  0.545"
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                "0.000  0.250 -0.014\n"
                + "  -0.020  0.830  0.110\n"
                + "   0.300  0.110 -0.090\n"
                + "   0.300  0.090 -0.080"
        );
    }
}
