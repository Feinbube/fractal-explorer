package luv.values.generators.fractals.ifs.fractint;

// introcs.cs.princeton.edu/java/22library/
import luv.values.generators.fractals.ifs.IFS;

public class PojoSwirlIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D("0.9126750  0.0873250 ");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                "0.745455 -0.459091  0.2733004\n"
                + "  -0.424242 -0.065152  1.0930777"
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                "0.406061  0.887121 -0.1339233\n"
                + "  -0.175758 -0.218182  0.7620266"
        );
    }
}
