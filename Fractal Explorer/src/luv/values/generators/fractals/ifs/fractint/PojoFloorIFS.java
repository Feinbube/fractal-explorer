package luv.values.generators.fractals.ifs.fractint;

// introcs.cs.princeton.edu/java/22library/
import luv.values.generators.fractals.ifs.IFS;

public class PojoFloorIFS extends IFS {

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
                "0.0 -0.5  0.3267634\n"
                + "   0.5  0.0  0.2472109\n"
                + "   0.0  0.5  0.6620804"
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                "0.5  0.0  0.0866182\n"
                + "   0.0  0.5  0.5014877\n"
                + "  -0.5  0.0  0.5810401"
        );
    }
}
