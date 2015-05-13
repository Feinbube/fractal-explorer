package luv.values.colorizers.mono;

import java.awt.Color;
import luv.values.colorizers.HybridColorizer;
import luv.opencl.HybridMemory;
import luv.opencl.HybridMemoryType;
import luv.opencl.OpenCL;
import luv.values.colorizers.Colorizer;

public class HybridColorMap extends HybridColorizer {

    HybridMemory colorMap;

    public HybridColorMap(OpenCL openCL) {
        super(openCL);

        if (openCL != null) {
            colorMap = new HybridMemory(openCL, getColors(), HybridMemoryType.ReadOnly);
        }
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

    protected int[] getColors() {
        return initColorMap(32, Color.RED, Color.GREEN, Color.BLUE);
    }

    /**
     * Creates the colorMap array which contains ARGB colors as integers,
     * interpolated through the given colors with colors.length * stepSize steps
     *
     * @param stepSize The number of interpolation steps between two colors
     * @param colors The colors for the map
     */
    protected int[] initColorMap(int stepSize, Color... colors) {
        int[] colorMap = new int[stepSize * (colors.length - 1) + 1];
        colorMap[0] = 0xFF000000;

        int index = 1;
        for (int i = 0; i < colors.length - 1; i++) {
            Color c0 = colors[i];
            int r0 = c0.getRed();
            int g0 = c0.getGreen();
            int b0 = c0.getBlue();

            Color c1 = colors[i + 1];
            int r1 = c1.getRed();
            int g1 = c1.getGreen();
            int b1 = c1.getBlue();

            int dr = r1 - r0;
            int dg = g1 - g0;
            int db = b1 - b0;

            for (int j = 0; j < stepSize; j++) {
                float alpha = (float) j / (stepSize - 1);
                int r = (int) (r0 + alpha * dr);
                int g = (int) (g0 + alpha * dg);
                int b = (int) (b0 + alpha * db);
                colorMap[index++] = 0xFF000000 | (r << 16) | (g << 8) | b;
            }
        }

        return colorMap;
    }

    @Override
    protected Colorizer getNewPOJOImplementation() {
        return new PojoColorMap();
    }
}
