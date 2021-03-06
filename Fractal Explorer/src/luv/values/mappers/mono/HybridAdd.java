package luv.values.mappers.mono;

import luv.values.mappers.HybridValueMapper;
import luv.values.generators.ValueGenerator;
import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;

public class HybridAdd extends HybridValueMapper {

    public HybridAdd(OpenCL openCL, ValueGenerator... generators) {
        super(openCL, generators);
    }

    @Override
    protected String getCLFileName() {
        return "src/luv/values/mappers/mono/KernelMonoMappers.cl";
    }

    @Override
    protected String getKernelName() {
        return "add";
    }

    @Override
    protected void runKernel(HybridMemory output, double x0, double y0, double x1, double y1, int w, int h, HybridMemory... input) {
        kernel.run(w, h, 0, input[0], output, ((PojoAdd)getPojoImplementation()).add);
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoAdd();
    }
}
