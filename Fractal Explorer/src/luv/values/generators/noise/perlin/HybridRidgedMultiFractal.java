package luv.values.generators.noise.perlin;

import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridRidgedMultiFractal extends HybridMonoFractal {

    public HybridRidgedMultiFractal(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getKernelName() {
        return "ridgedMultiFractalArray";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoRidgedMultiFractal();
    }
}
