package luv.values.generators.fractals.ifs.fractint;

// introcs.cs.princeton.edu/java/22library/
import luv.values.generators.fractals.ifs.IFS;

public class PojoKochIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D("0.151515  0.253788  0.253788  0.151515  0.189394");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                "0.307692 -0.000000  0.7580704\n"
                + "   0.192308 -0.205882  0.3349620\n"
                + "   0.192308  0.205882  0.4707040\n"
                + "   0.307692 -0.000000 -0.0674990\n"
                + "   0.307692 -0.000000  0.3453822"
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                "0.000000  0.294118  0.1604278\n"
                + "   0.653846  0.088235  0.2709686\n"
                + "  -0.653846  0.088235  0.9231744\n"
                + "   0.000000  0.294118  0.1604278\n"
                + "   0.000000 -0.294118  0.2941176"
        );
    }
}
