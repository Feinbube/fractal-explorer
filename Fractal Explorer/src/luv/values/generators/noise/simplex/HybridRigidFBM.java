package luv.values.generators.noise.simplex;

import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridRigidFBM extends HybridSimplexNoise {

    public HybridRigidFBM(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getKernelName() {
        return "runRigidFBM";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoRigidFBM();
    }
}
