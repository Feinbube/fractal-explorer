package luv.values.mappers;

import java.util.List;
import luv.values.generators.ValueGenerator;
import luv.opencl.HybridMemory;
import luv.opencl.HybridMemoryType;
import luv.opencl.OpenCL;
import luv.values.Property;
import luv.values.generators.HybridValueGenerator;

public abstract class HybridValueMapper extends HybridValueGenerator implements ValueMapper {

    protected ValueBag valueBag;

    public HybridValueMapper(OpenCL openCL, ValueGenerator... generators) {
        super(openCL);
        this.valueBag = new ValueBag(generators);
    }

    @Override
    protected Object[] getDetails() {
        return valueBag.get().toArray();
    }

    @Override
    public void set(List<ValueGenerator> generators) {
        valueBag.set(generators);
    }

    @Override
    public List<ValueGenerator> get() {
        return valueBag.get();
    }

    @Override
    public ValueGenerator get(int index) {
        return valueBag.get(index);
    }

    @Override
    public void add(ValueGenerator generator) {
        valueBag.add(generator);
    }

    @Override
    public void replace(int index, ValueGenerator generator) {
        valueBag.replace(index, generator);
    }
    
    @Override
    public void exchange(int index, ValueGenerator generator) {
        valueBag.exchange(index, generator);
    }
    
    @Override
    public void remove(int index) {
        valueBag.remove(index);
    }

    @Override
    public int size() {
        return valueBag.size();
    }

    @Override
    public String getHierarchicalName() {
        return this.getName() + valueBag.getHierarchicalName();
    }
    
    @Override
    public Property[] getProperties() {
        return getPojoImplementation().getProperties();
    }

    @Override
    public void setProperty(String name, float value) {
        getPojoImplementation().setProperty(name, value);
    }

    protected HybridMemory[] getValues(List<ValueGenerator> valueGenerators, double x0, double y0, double x1, double y1, int w, int h) {
        HybridMemory[] result = new HybridMemory[valueGenerators.size()];
        for (int valueGeneratorIndex = 0; valueGeneratorIndex < result.length; valueGeneratorIndex++) {
            result[valueGeneratorIndex] = valueGenerators.get(valueGeneratorIndex) instanceof HybridValueGenerator
                    ? ((HybridValueGenerator) valueGenerators.get(valueGeneratorIndex)).getHybridValues(x0, y0, x1, y1, w, h)
                    : new HybridMemory(getOpenCL(), valueGenerators.get(valueGeneratorIndex).getValues(x0, y0, x1, y1, w, h), HybridMemoryType.ReadOnly);
        }
        return result;
    }

    @Override
    public HybridMemory getHybridValues(double x0, double y0, double x1, double y1, int w, int h) {
        values = new HybridMemory(getOpenCL(), float.class, w * h, HybridMemoryType.WriteOnly);
        runKernel(values, x0, y0, x1, y1, w, h, getValues(valueBag.get(), x0, y0, x1, y1, w, h));

        return values;
    }

    protected abstract void runKernel(HybridMemory output, double x0, double y0, double x1, double y1, int w, int h, HybridMemory... inputs);

    @Override
    protected void runKernel(HybridMemory values, double x0, double y0, double x1, double y1, int w, int h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
