package luv.values.generators.noise.cell;

import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridMosaicNoise extends HybridCellNoise {

    public HybridMosaicNoise(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getKernelName() {
        return "runMosaicNoise";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoMosaicNoise();
    }
}
