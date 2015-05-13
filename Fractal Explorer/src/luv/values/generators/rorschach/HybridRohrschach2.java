package luv.values.generators.rorschach;

import luv.values.generators.HybridValueGenerator;
import luv.opencl.HybridMemory;
import luv.opencl.HybridMemoryType;
import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridRohrschach2 extends HybridValueGenerator {

    public HybridRohrschach2(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getCLFileName() {
        return "src/luv/values/generators/rorschach/KernelRorschach.cl";
    }

    @Override
    protected String getKernelName() {
        return "runRohrschach2";
    }

    @Override
    protected void runKernel(HybridMemory values, double x0, double y0, double x1, double y1, int w, int h) {
        HybridMemory heatPointsX = new HybridMemory(getOpenCL(), ((PojoRohrschach2) getPojoImplementation()).heatPointsX, HybridMemoryType.ReadOnly);
        HybridMemory heatPointsY = new HybridMemory(getOpenCL(), ((PojoRohrschach2) getPojoImplementation()).heatPointsY, HybridMemoryType.ReadOnly);
        HybridMemory heatPointsR = new HybridMemory(getOpenCL(), ((PojoRohrschach2) getPojoImplementation()).heatPointsR, HybridMemoryType.ReadOnly);

        kernel.run(w, h, 0, heatPointsX, heatPointsY, heatPointsR, values, (float) (x0), (float) (y0), (float) (x1), (float) (y1),
                ((PojoRohrschach2) getPojoImplementation()).nrHeatPoints, ((PojoRohrschach2) getPojoImplementation()).slim);
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoRohrschach2();
    }
}