package luv.values.generators.fractals;

import luv.values.generators.HybridValueGenerator;
import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridJuliaSet extends HybridValueGenerator {

    public HybridJuliaSet(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getCLFileName() {
        return "src/luv/values/generators/fractals/KernelJuliaSet.cl";
    }

    @Override
    protected String getKernelName() {
        return "runJuliaSet";
    }

    @Override
    protected void runKernel(HybridMemory values, double x0, double y0, double x1, double y1, int w, int h) {
        kernel.run(w, h, 0, values, (float) (x0), (float) (y0), (float) (x1), (float) (y1),
                ((PojoJuliaSet) getPojoImplementation()).cRe, ((PojoJuliaSet) getPojoImplementation()).cIm, ((PojoJuliaSet) getPojoImplementation()).maxIterations);
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoJuliaSet();
    }
}
