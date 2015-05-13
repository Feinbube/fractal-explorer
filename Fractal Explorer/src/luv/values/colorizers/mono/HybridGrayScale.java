package luv.values.colorizers.mono;

import luv.values.colorizers.HybridColorizer;
import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;
import luv.values.colorizers.Colorizer;

public class HybridGrayScale extends HybridColorizer {

    public HybridGrayScale(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected void runKernel(HybridMemory pixels, int width, int height, HybridMemory values) {
        kernel.run(width, height, 0, values, pixels);
    }

    @Override
    protected Colorizer getNewPOJOImplementation() {
        return new PojoGrayScale();
    }

    @Override
    protected String getCLFileName() {
        return "src/luv/values/colorizers/mono/KernelMonoColorizers.cl";
    }

    @Override
    protected String getKernelName() {
        return "grayScale";
    }
}
