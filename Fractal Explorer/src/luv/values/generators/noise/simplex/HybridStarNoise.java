package luv.values.generators.noise.simplex;

import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridStarNoise extends HybridSimplexNoise {

    public HybridStarNoise(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getKernelName() {
        return "runStarNoise";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoStarNoise();
    }
}
