package luv.values.generators.fractals.ifs.fractint;

// introcs.cs.princeton.edu/java/22library/
import luv.values.generators.fractals.ifs.IFS;

public class PojoSpiralIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D("0.895652  0.052174  0.052174");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                "0.787879 -0.424242  0.2819252\n"
                + "  -0.121212  0.257576 -0.1115594\n"
                + "   0.181818 -0.136364  1.0177017"
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                "0.242424  0.859848  0.0195945\n"
                + "   0.151515  0.053030  0.0619661\n"
                + "   0.090909  0.181818  0.1113490"
        );
    }
}
