package luv.values.mappers.multi;

import luv.values.mappers.HybridMultiValueMapper;
import luv.values.generators.ValueGenerator;
import luv.opencl.OpenCL;

public class HybridMax extends HybridMultiValueMapper {

    public HybridMax(OpenCL openCL, ValueGenerator... generators) {
        super(openCL, generators);
    }

    @Override
    protected String getKernelName2() {
        return "maxOf2";
    }

    @Override
    protected String getKernelName3() {
        return "maxOf3";
    }

    @Override
    protected String getKernelName4() {
        return "maxOf4";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoMax();
    }
}
