package luv.values.generators.noise.cell;

import luv.values.generators.HybridValueGenerator;
import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridCellNoise extends HybridValueGenerator {

    public HybridCellNoise(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getCLFileName() {
        return "src/luv/values/generators/noise/cell/KernelCellNoise.cl";
    }

    @Override
    protected String getKernelName() {
        return "runCellNoise";
    }

    @Override
    protected void runKernel(HybridMemory values, double x0, double y0, double x1, double y1, int w, int h) {
        kernel.run(w, h, 0, values, (float) (x0), (float) (y0), (float) (x1), (float) (y1), ((PojoCellNoise) getPojoImplementation()).distanceFunction);
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoCellNoise();
    }
}
