package luv.values.generators.fractals.ifs;

// introcs.cs.princeton.edu/java/22library/
public class PojoCoralIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return fromString1D("0.40 0.15 0.45");
    }

    @Override
    protected double[][] getXMatrix() {
        return fromString2D(
                ".3077 -.5315 .8863\n"
                + ".3077 -.0769 .2166\n"
                + ".0000 .5455 .0106"
        );
    }

    @Override
    protected double[][] getYMatrix() {
        return fromString2D(
                "-.4615 -.2937 1.0962\n"
                + ".1538 -.4476 .3384\n"
                + ".6923 -.1958 .3808"
        );
    }
}
