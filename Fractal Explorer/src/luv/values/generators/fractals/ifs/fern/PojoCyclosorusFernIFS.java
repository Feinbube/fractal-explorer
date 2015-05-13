package luv.values.generators.fractals.ifs.fern;

// introcs.cs.princeton.edu/java/22library/

import luv.values.generators.fractals.ifs.IFS;

public class PojoCyclosorusFernIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D("0.02 0.84 0.07 0.07");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                "    0.000  0.000  0.500\n" +
"   0.950  0.005  0.025\n" +
"   0.035 -0.200  0.474\n" +
"  -0.040  0.200  0.528"
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                "0.000  0.250 -0.040\n" +
"  -0.005  0.930  0.053\n" +
"   0.160  0.040 -0.078\n" +
"   0.160  0.040 -0.068"
        );
    }
}
