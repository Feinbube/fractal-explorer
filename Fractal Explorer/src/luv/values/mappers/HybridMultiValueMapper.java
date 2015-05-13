package luv.values.mappers;

import luv.values.generators.ValueGenerator;
import luv.opencl.ComputeKernel;
import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;

public abstract class HybridMultiValueMapper extends HybridValueMapper {

    protected ComputeKernel kernel3;
    protected ComputeKernel kernel4;

    public HybridMultiValueMapper(OpenCL openCL, ValueGenerator... generators) {
        super(openCL, generators);

        this.kernel3 = new ComputeKernel(openCL, getCLFileName(), getKernelName3());
        this.kernel4 = new ComputeKernel(openCL, getCLFileName(), getKernelName4());
    }

    @Override
    protected String getCLFileName() {
        return "src/luv/values/mappers/multi/KernelMultiMappers.cl";
    }

    @Override
    protected String getKernelName() {
        return getKernelName2();
    }

    protected abstract String getKernelName2();

    protected abstract String getKernelName3();

    protected abstract String getKernelName4();

    @Override
    protected void runKernel(HybridMemory output, double x0, double y0, double x1, double y1, int w, int h, HybridMemory... input) {
        if (input.length == 1) {
            kernel.run(w, h, 0, input[0], input[0], output);
        } else if (input.length == 2) {
            kernel.run(w, h, 0, input[0], input[1], output);
        } else if (input.length == 3) {
            kernel3.run(w, h, 0, input[0], input[1], input[2], output);
        } else if (input.length == 4) {
            kernel4.run(w, h, 0, input[0], input[1], input[2], input[3], output);
        } else if (input.length > 4) {
            runKernel(output, x0, y0, x1, y1, w, h, new HybridMemory[]{input[0], input[1], input[2], input[3]});
            runKernel(input[0], x0, y0, x1, y1, w, h, newInputs(4, input, output));
            output.become(input[0]);
        } else {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private HybridMemory[] newInputs(int skip, HybridMemory[] input, HybridMemory output) {
        HybridMemory[] result = new HybridMemory[input.length - skip + 1];
        for (int i = skip; i < input.length; i++) {
            result[i - skip] = input[i];
        }
        result[result.length - 1] = output;
        return result;
    }
}
