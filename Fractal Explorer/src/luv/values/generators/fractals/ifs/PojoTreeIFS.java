package luv.values.generators.fractals.ifs;

// introcs.cs.princeton.edu/java/22library/
public class PojoTreeIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D("0.1  0.1  0.2  0.2  0.2  0.2");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                "0.00  0.00  0.550\n"
                + "  -0.05  0.00  0.525\n"
                + "   0.46 -0.15  0.270\n"
                + "   0.47 -0.15  0.265\n"
                + "   0.43  0.28  0.285\n"
                + "   0.42  0.26  0.290"
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                "   0.00  0.60  0.000\n"
                + "  -0.50  0.00  0.750\n"
                + "   0.39  0.38  0.105\n"
                + "   0.17  0.42  0.465\n"
                + "  -0.25  0.45  0.625\n"
                + "  -0.35  0.31  0.525"
        );
    }
}
