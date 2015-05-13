package luv.values.mappers.multi;

import luv.values.mappers.HybridMultiValueMapper;
import luv.values.generators.ValueGenerator;
import luv.opencl.OpenCL;

public class HybridAverage extends HybridMultiValueMapper {

    public HybridAverage(OpenCL openCL, ValueGenerator... generators) {
        super(openCL, generators);
    }

    @Override
    protected String getKernelName2() {
        return "avgOf2";
    }

    @Override
    protected String getKernelName3() {
        return "avgOf3";
    }

    @Override
    protected String getKernelName4() {
        return "avgOf4";
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoAverage();
    }
}
