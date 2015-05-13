package luv.values.generators.noise.simplex;

import luv.values.generators.HybridValueGenerator;
import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridSimplexNoise extends HybridValueGenerator {

    public HybridSimplexNoise(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getCLFileName() {
        return "src/luv/values/generators/noise/simplex/KernelSimplexNoise.cl";
    }

    @Override
    protected String getKernelName() {
        return "runSimplexNoise";
    }

    @Override
    protected void runKernel(HybridMemory values, double x0, double y0, double x1, double y1, int w, int h) {
        kernel.run(w, h, 0, values, (float) (x0), (float) (y0), (float) (x1), (float) (y1));
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoSimplexNoise();
    }
}
