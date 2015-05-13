package luv.values.generators.noise.perlin;

import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridTurbulenceArray extends HybridMonoFractal {

    public HybridTurbulenceArray(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getKernelName() {
        return "turbulenceArray";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoTurbulenceArray();
    }
}
