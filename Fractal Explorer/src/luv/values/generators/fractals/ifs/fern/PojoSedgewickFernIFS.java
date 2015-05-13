package luv.values.generators.fractals.ifs.fern;

// introcs.cs.princeton.edu/java/22library/
import luv.values.generators.fractals.ifs.IFS;

public class PojoSedgewickFernIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D(".02 .15 .13 .70 ");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                " .000  .000  .500 \n"
                + " -.139  .263  .570 \n"
                + "  .170 -.215  .408 \n"
                + "  .781  .034  .1075 "
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                " .000  .270  .000 \n"
                + "  .246  .224 -.036 \n"
                + "  .222  .176  .0893 \n"
                + " -.032  .739  .270 "
        );
    }
}
