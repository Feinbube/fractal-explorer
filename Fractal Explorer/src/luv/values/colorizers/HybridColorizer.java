package luv.values.colorizers;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import luv.opencl.ComputeKernel;
import luv.opencl.HybridMemory;
import luv.opencl.HybridMemoryType;
import luv.opencl.OpenCL;
import luv.values.Property;
import luv.values.generators.HybridValueGenerator;
import luv.values.generators.ValueGenerator;

public abstract class HybridColorizer extends Colorizer {

    protected OpenCL openCL;
    protected ComputeKernel kernel;

    private Colorizer pojoImplementation;

    public HybridColorizer(OpenCL openCL) {
        this.openCL = openCL;
        this.pojoImplementation = null;

        if (openCL != null) {
            kernel = new ComputeKernel(openCL, getCLFileName(), getKernelName());
        }
    }

    protected abstract String getCLFileName();

    protected abstract String getKernelName();

    @Override
    public BufferedImage getImage(double x0, double y0, double x1, double y1, int w, int h, ValueGenerator... valueGenerators) {
        if (openCL != null) {
            return getImage(w, h, getValues(valueGenerators, x0, y0, x1, y1, w, h));
        } else {
            return this.pojoImplementation.getImage(x0, y0, x1, y1, w, h, valueGenerators);
        }
    }

    @Override
    public BufferedImage blendImage(BufferedImage image, double x0, double y0, double x1, double y1, ValueGenerator... valueGenerators) {
        if (openCL != null) {
            return blendImage(image, getValues(valueGenerators, x0, y0, x1, y1, image.getWidth(), image.getHeight()));
        } else {
            return this.pojoImplementation.blendImage(image, x0, y0, x1, y1, valueGenerators);
        }
    }

    protected HybridMemory[] getValues(ValueGenerator[] valueGenerators, double x0, double y0, double x1, double y1, int w, int h) {
        HybridMemory[] values = new HybridMemory[valueGenerators.length];
        for (int valueGeneratorIndex = 0; valueGeneratorIndex < values.length; valueGeneratorIndex++) {
            values[valueGeneratorIndex] = valueGenerators[valueGeneratorIndex] instanceof HybridValueGenerator
                    ? ((HybridValueGenerator) valueGenerators[valueGeneratorIndex]).getHybridValues(x0, y0, x1, y1, w, h)
                    : new HybridMemory(openCL, valueGenerators[valueGeneratorIndex].getValues(x0, y0, x1, y1, w, h), HybridMemoryType.ReadOnly);
        }
        return values;
    }

    protected BufferedImage getImage(int w, int h, HybridMemory... values) {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        int data[] = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        HybridMemory pixels = new HybridMemory(openCL, float.class, w * h, HybridMemoryType.WriteOnly);

        runKernel(pixels, w, h, values);
        pixels.readTo(data);
        return image;
    }

    protected BufferedImage blendImage(BufferedImage image, HybridMemory... values) {

        int data[] = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        HybridMemory pixels = new HybridMemory(openCL, data, HybridMemoryType.ReadWrite);

        runKernel(pixels, image.getWidth(), image.getHeight(), values);
        pixels.readTo(data);
        return image;
    }

    protected void runKernel(HybridMemory pixels, int width, int height, HybridMemory... values) {
        runKernel(pixels, width, height, values[0]);
    }

    protected abstract void runKernel(HybridMemory pixels, int width, int height, HybridMemory values);

    protected abstract Colorizer getNewPOJOImplementation();

    @Override
    public Property[] getProperties() {
        return getPojoImplementation().getProperties();
    }

    @Override
    public void setProperty(String name, float value) {
        getPojoImplementation().setProperty(name, value);
    }

    public Colorizer getPojoImplementation() {
        if (pojoImplementation == null) {
            pojoImplementation = getNewPOJOImplementation();
        }
        return pojoImplementation;
    }

    public void setPojoImplementation(Colorizer pojoImplementation) {
        this.pojoImplementation = pojoImplementation;
    }
}
