package luv.values.generators.noise.cell;

import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;
import luv.values.generators.ValueGenerator;

public class HybridNoisyNoise extends HybridCellNoise {

    int channel;

    public HybridNoisyNoise(OpenCL openCL, int channel) {
        super(openCL);
        this.channel = channel;
    }

    @Override
    protected String getKernelName() {
        return "runNoisyNoise";
    }

    @Override
    protected void runKernel(HybridMemory values, double x0, double y0, double x1, double y1, int w, int h) {
        kernel.run(w, h, 0, values, (float) (x0), (float) (y0), (float) (x1), (float) (y1), ((PojoCellNoise) getPojoImplementation()).distanceFunction, channel);
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoNoisyNoise(channel);
    }
}
