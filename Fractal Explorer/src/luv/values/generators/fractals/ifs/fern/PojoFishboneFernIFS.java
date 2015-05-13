package luv.values.generators.fractals.ifs.fern;

// introcs.cs.princeton.edu/java/22library/
import luv.values.generators.fractals.ifs.IFS;

public class PojoFishboneFernIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D("0.0200 0.8400 0.0700 0.0700 ");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                "0.000  0.000  0.500\n" +
"   0.950  0.002  0.025\n" +
"   0.035 -0.110  0.478\n" +
"  -0.040  0.110  0.525"
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                "0.000  0.250 -0.040\n" +
"  -0.002  0.930  0.051\n" +
"   0.270  0.010 -0.135\n" +
"   0.270  0.010 -0.129"
        );
    }
}
