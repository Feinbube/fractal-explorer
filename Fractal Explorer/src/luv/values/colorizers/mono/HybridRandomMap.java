package luv.values.colorizers.mono;

import luv.values.colorizers.HybridColorizer;
import java.awt.Color;
import java.util.Random;
import luv.opencl.HybridMemory;
import luv.opencl.HybridMemoryType;
import luv.opencl.OpenCL;
import luv.values.colorizers.Colorizer;

public class HybridRandomMap extends HybridColorizer {

    HybridMemory colorMap;

    public HybridRandomMap(OpenCL openCL, int numColors) {
        super(openCL);

        if (openCL != null) {
            colorMap = new HybridMemory(openCL, getColors(numColors), HybridMemoryType.ReadOnly);
        }

        setPojoImplementation(new PojoRandomMap(numColors));
    }

    @Override
    protected void runKernel(HybridMemory pixels, int width, int height, HybridMemory values) {
        kernel.run(width, height, 0, values, pixels, colorMap, colorMap.getSize());
    }

    @Override
    protected String getCLFileName() {
        return "src/luv/values/colorizers/mono/KernelMonoColorizers.cl";
    }

    @Override
    protected String getKernelName() {
        return "colorScaleMap";
    }

    protected int[] getColors(int numColors) {
        Random random = new Random(System.currentTimeMillis());

        int[] result = new int[numColors];

        final int mixR = random.nextInt(256);
        final int mixG = random.nextInt(256);
        final int mixB = random.nextInt(256);

        for (int i = 1; i <= numColors; ++i) {

            result[i - 1] = new Color(
                    (random.nextInt(768) + mixR) / 4,
                    (random.nextInt(768) + mixG) / 4,
                    (random.nextInt(768) + mixB) / 4).hashCode();
        }
        return result;
    }

    @Override
    protected Colorizer getNewPOJOImplementation() {
        return null; // this should never be called since we set the pojo implementation in the constructor
    }
}
