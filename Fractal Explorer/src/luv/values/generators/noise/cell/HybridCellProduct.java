package luv.values.generators.noise.cell;

import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridCellProduct extends HybridCellNoise {

    public HybridCellProduct(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getKernelName() {
        return "runCellProduct";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoCellProduct();
    }
}
