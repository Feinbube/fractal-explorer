package luv.values.mappers.multi;

import luv.values.mappers.HybridMultiValueMapper;
import luv.values.generators.ValueGenerator;
import luv.opencl.OpenCL;

public class HybridMin extends HybridMultiValueMapper {

    public HybridMin(OpenCL openCL, ValueGenerator... generators) {
        super(openCL, generators);
    }

    @Override
    protected String getKernelName2() {
        return "minOf2";
    }

    @Override
    protected String getKernelName3() {
        return "minOf3";
    }

    @Override
    protected String getKernelName4() {
        return "minOf4";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoMin();
    }
}
