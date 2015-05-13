package luv.values.generators.noise.cell;

import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridPointNoise extends HybridCellNoise {

    public HybridPointNoise(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getKernelName() {
        return "runPointNoise";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoPointNoise();
    }
}
