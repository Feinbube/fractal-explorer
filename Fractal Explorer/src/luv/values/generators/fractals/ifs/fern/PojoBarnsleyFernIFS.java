package luv.values.generators.fractals.ifs.fern;

// introcs.cs.princeton.edu/java/22library/

import luv.values.generators.fractals.ifs.IFS;

public class PojoBarnsleyFernIFS extends IFS {

    @Override
    protected int getIterations() {
        return 200000;
    }

    @Override
    protected double[] getRuleProbablisties() {
        return new double[] { 0.01, 0.85, 0.07, 0.07 };
    }

    @Override
    protected double[][] getXMatrix() {
        return new double[][] { 
            new double[] {0.00, 0.00, 0.500},
            new double[] {0.85, 0.04, 0.075},
            new double[] {0.20, -.26, 0.400},
            new double[] {-.15, 0.28, 0.575},
        };
    }

    @Override
    protected double[][] getYMatrix() {
        return new double[][] { 
            new double[] {0.00, 0.16, 0.000},
            new double[] {-.04, 0.85, 0.180},
            new double[] {0.23, 0.22, 0.045},
            new double[] {0.26, 0.24, -.086},
        };
    }
}
