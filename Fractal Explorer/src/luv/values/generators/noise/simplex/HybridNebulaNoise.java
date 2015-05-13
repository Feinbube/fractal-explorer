package luv.values.generators.noise.simplex;

import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridNebulaNoise extends HybridSimplexNoise {

    public HybridNebulaNoise(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getKernelName() {
        return "runNebulaNoise";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoNebulaNoise();
    }
}
