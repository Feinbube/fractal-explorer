package luv.values.generators.noise.simplex;

import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridValueNoise extends HybridSimplexNoise {

    public HybridValueNoise(OpenCL openCL) {
        this(openCL, 10.0);
    }

    public HybridValueNoise(OpenCL openCL, double distortion) {
        super(openCL);

        setPojoImplementation(new PojoValueNoise(distortion));
    }

    @Override
    protected String getKernelName() {
        return "runValueNoise";
    }

    @Override
    protected void runKernel(HybridMemory values, double x0, double y0, double x1, double y1, int w, int h) {
        kernel.run(w, h, 0, values, (float) (x0), (float) (y0), (float) (x1), (float) (y1), (float) ((PojoValueNoise) getPojoImplementation()).distortion);
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoValueNoise(((PojoValueNoise) getPojoImplementation()).distortion);
    }
}
