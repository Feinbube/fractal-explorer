package luv.values.colorizers.mono;

import luv.values.colorizers.HybridColorizer;
import java.awt.Color;
import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;
import luv.values.colorizers.Colorizer;

public class HybridLinearScale extends HybridColorizer {

    public HybridLinearScale(OpenCL openCL, Color c1, Color c2) {
        super(openCL);

        setPojoImplementation(new PojoLinearScale(c1, c2));
    }

    @Override
    protected void runKernel(HybridMemory pixels, int width, int height, HybridMemory values) {
        kernel.run(width, height, 0, values, pixels, ((PojoLinearScale) getPojoImplementation()).c1.hashCode(), ((PojoLinearScale) getPojoImplementation()).c2.hashCode());
    }

    @Override
    protected Colorizer getNewPOJOImplementation() {
        return new PojoLinearScale(((PojoLinearScale) getPojoImplementation()).c1, ((PojoLinearScale) getPojoImplementation()).c2);
    }

    @Override
    protected String getCLFileName() {
        return "src/luv/values/colorizers/mono/KernelMonoColorizers.cl";
    }

    @Override
    protected String getKernelName() {
        return "linearScale";
    }
}
