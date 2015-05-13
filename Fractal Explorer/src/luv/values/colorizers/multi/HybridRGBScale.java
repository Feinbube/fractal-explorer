package luv.values.colorizers.multi;

import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;
import luv.values.colorizers.Colorizer;
import luv.values.colorizers.HybridColorizer;

public class HybridRGBScale extends HybridColorizer {

    public HybridRGBScale(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected void runKernel(HybridMemory pixels, int width, int height, HybridMemory... values) {
        kernel.run(width, height, 0, values[0], values[1 % values.length], values[2 % values.length], pixels);
    }

    @Override
    protected void runKernel(HybridMemory pixels, int width, int height, HybridMemory values) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getCLFileName() {
        return "src/luv/values/colorizers/multi/KernelMultiColorizers.cl";
    }

    @Override
    protected String getKernelName() {
        return "rgbScale";
    }

    @Override
    protected Colorizer getNewPOJOImplementation() {
        return new PojoRGBScale();
    }
}
