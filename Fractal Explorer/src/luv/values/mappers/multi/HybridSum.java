package luv.values.mappers.multi;

import luv.values.mappers.HybridMultiValueMapper;
import luv.values.generators.ValueGenerator;
import luv.opencl.OpenCL;

public class HybridSum extends HybridMultiValueMapper {

    public HybridSum(OpenCL openCL, ValueGenerator... generators) {
        super(openCL, generators);
    }

    @Override
    protected String getKernelName2() {
        return "sumOf2";
    }

    @Override
    protected String getKernelName3() {
        return "sumOf3";
    }

    @Override
    protected String getKernelName4() {
        return "sumOf4";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoSum();
    }
}
