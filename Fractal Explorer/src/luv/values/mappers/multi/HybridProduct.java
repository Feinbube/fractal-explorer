package luv.values.mappers.multi;

import luv.values.mappers.HybridMultiValueMapper;
import luv.values.generators.ValueGenerator;
import luv.opencl.OpenCL;

public class HybridProduct extends HybridMultiValueMapper {

    public HybridProduct(OpenCL openCL, ValueGenerator... generators) {
        super(openCL, generators);
    }

    @Override
    protected String getKernelName2() {
        return "productOf2";
    }

    @Override
    protected String getKernelName3() {
        return "productOf3";
    }

    @Override
    protected String getKernelName4() {
        return "productOf4";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoProduct();
    }
}
