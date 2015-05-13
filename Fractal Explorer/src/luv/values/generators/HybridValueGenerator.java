package luv.values.generators;

import luv.opencl.ComputeKernel;
import luv.opencl.HybridMemory;
import luv.opencl.HybridMemoryType;
import luv.opencl.OpenCL;
import luv.values.Property;

public abstract class HybridValueGenerator extends ValueGenerator {

    private transient OpenCL openCL;
    protected transient ComputeKernel kernel;

    protected transient HybridMemory values;

    private ValueGenerator pojoImplementation;

    public HybridValueGenerator(OpenCL openCL) {
        this.setOpenCL(openCL);

        if (openCL != null) {
            kernel = new ComputeKernel(openCL, getCLFileName(), getKernelName());
        }

        this.pojoImplementation = null;
    }

    protected abstract String getCLFileName();

    protected abstract String getKernelName();

    protected abstract ValueGenerator getNewPOJOImplementation();

    @Override
    public float[] getValues(double x0, double y0, double x1, double y1, int w, int h) {
        if (getOpenCL() != null) {
            return getHybridValues(x0, y0, x1, y1, w, h).toArray();
        } else {
            return this.getPojoImplementation().getValues(x0, y0, x1, y1, w, h);
        }
    }

    public HybridMemory getHybridValues(double x0, double y0, double x1, double y1, int w, int h) {
        if (getOpenCL() == null) {
            throw new UnsupportedOperationException("openCL must not be null!");
        }

        values = new HybridMemory(getOpenCL(), float.class, w * h, HybridMemoryType.WriteOnly);
        
        x0 = (x0 - ((ValueGenerator) getPojoImplementation()).xOffset) / ((ValueGenerator) getPojoImplementation()).zoom;
        x1 = (x1 - ((ValueGenerator) getPojoImplementation()).xOffset) / ((ValueGenerator) getPojoImplementation()).zoom;
        y0 = (y0 - ((ValueGenerator) getPojoImplementation()).yOffset) / ((ValueGenerator) getPojoImplementation()).zoom;
        y1 = (y1 - ((ValueGenerator) getPojoImplementation()).yOffset) / ((ValueGenerator) getPojoImplementation()).zoom;
        
        runKernel(values, x0, y0, x1, y1, w, h);

        return values;
    }

    protected abstract void runKernel(HybridMemory values, double x0, double y0, double x1, double y1, int w, int h);

    @Override
    public Property[] getProperties() {
        return getPojoImplementation().getProperties();
    }

    @Override
    public void setProperty(String name, float value) {
        getPojoImplementation().setProperty(name, value);
    }

    public ValueGenerator getPojoImplementation() {
        if (pojoImplementation == null) {
            pojoImplementation = getNewPOJOImplementation();
        }
        return pojoImplementation;
    }

    public void setPojoImplementation(ValueGenerator pojoImplementation) {
        this.pojoImplementation = pojoImplementation;
    }

	public OpenCL getOpenCL() {
		return openCL;
	}

	public void setOpenCL(OpenCL openCL) {
		this.openCL = openCL;
	}
}
