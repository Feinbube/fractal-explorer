package luv.values.generators.fractals.ifs;

// introcs.cs.princeton.edu/java/22library/
public class PojoSierpinskiIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D(".33 .33 .34");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                ".50 .00 .00 \n"
                + "  .50 .00 .50 \n"
                + "  .50 .00 .25 "
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                ".00 .50 .00 \n"
                + "  .00 .50 .00 \n"
                + "  .00 .50 .433 "
        );
    }
}
