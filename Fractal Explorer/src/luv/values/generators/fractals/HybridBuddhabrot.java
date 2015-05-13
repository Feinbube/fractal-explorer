package luv.values.generators.fractals;

import luv.values.generators.HybridValueGenerator;
import luv.opencl.ComputeKernel;
import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridBuddhabrot extends HybridValueGenerator {

    ComputeKernel normalize;

    public HybridBuddhabrot(OpenCL openCL) {
        this(openCL, 0, 50);
    }

    public HybridBuddhabrot(OpenCL openCL, int minIterations, int maxIterations) {
        super(openCL);

        this.setPojoImplementation(new PojoBuddhabrot(minIterations, maxIterations));
        
        if (openCL != null) {
            normalize = new ComputeKernel(openCL, getCLFileName(), "normalize0TO1");
        }
    }

    @Override
    protected String getCLFileName() {
        return "src/luv/values/generators/fractals/KernelBuddhabrot.cl";
    }

    @Override
    protected String getKernelName() {
        return "runBuddhabrot";
    }

    @Override
    protected void runKernel(HybridMemory values, double x0, double y0, double x1, double y1, int w, int h) {
        kernel.run(w, h, 0, 4, values, (float) (x0), (float) (y0), (float) (x1), (float) (y1),
                ((PojoBuddhabrot) getPojoImplementation()).minIterations, ((PojoBuddhabrot) getPojoImplementation()).maxIterations);
        normalize.run(w, h, 0, values, values, 0.0f, values.maxValue());
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return getPojoImplementation();
    }
}
