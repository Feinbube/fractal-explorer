package luv.values.generators.noise.cell;

import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridIDMap extends HybridCellNoise {

    public HybridIDMap(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getKernelName() {
        return "runIDMap";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoIDMap();
    }
}
