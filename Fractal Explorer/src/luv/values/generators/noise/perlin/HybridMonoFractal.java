package luv.values.generators.noise.perlin;

import luv.opencl.HybridMemory;
import luv.opencl.OpenCL;
import luv.values.generators.HybridValueGenerator;
import luv.values.generators.ValueGenerator;

public class HybridMonoFractal extends HybridValueGenerator {

    public HybridMonoFractal(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected String getCLFileName() {
        return "src/luv/values/generators/noise/perlin/KernelPerlinNoise.cl";
    }

    @Override
    protected String getKernelName() {
        return "monoFractalArray";
    }

    @Override
    protected void runKernel(HybridMemory values, double x0, double y0, double x1, double y1, int w, int h) {
        kernel.run(w, h, 0, values, new float[]{(float) x0, (float) y0}, new float[]{(float) (x1 - x0), (float) (y1 - y0)},
                ((PojoMonoFractal) getPojoImplementation()).lacunarity, ((PojoMonoFractal) getPojoImplementation()).increment,
                ((PojoMonoFractal) getPojoImplementation()).octaves, ((PojoMonoFractal) getPojoImplementation()).amplitude);
    }

    @Override
    protected ValueGenerator getNewPOJOImplementation() {
        return new PojoMonoFractal();
    }
}
