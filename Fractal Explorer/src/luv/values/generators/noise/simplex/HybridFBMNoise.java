package luv.values.generators.noise.simplex;

import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridFBMNoise extends HybridSimplexNoise {

    public HybridFBMNoise(OpenCL openCL) {
        this(openCL, 8, 0.5);
    }

    public HybridFBMNoise(OpenCL openCL, int octaves) {
        this(openCL, octaves, 0.5);
    }

    public HybridFBMNoise(OpenCL openCL, int octaves, double persistence) {
        super(openCL);

        setPojoImplementation(new PojoFBMNoise(octaves, persistence));
    }

    @Override
    protected String getKernelName() {
        return "runFBMNoise";
    }

    @Override
    protected void runKernel(HybridMemory values, double x0, double y0, double x1, double y1, int w, int h) {
        kernel.run(w, h, 0, values, (float) (x0), (float) (y0), (float) (x1), (float) (y1),
                ((PojoFBMNoise) getPojoImplementation()).octaves, (float) ((PojoFBMNoise) getPojoImplementation()).persistance);
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoFBMNoise(((PojoFBMNoise) getPojoImplementation()).octaves, ((PojoFBMNoise) getPojoImplementation()).persistance);
    }
}
