package luv.values.mappers;

import java.util.ArrayList;
import java.util.List;
import luv.values.Property;
import luv.values.generators.ValueGenerator;

public class PojoValueMapper extends ValueGenerator implements ValueMapper {
    
    protected ValueBag valueBag;
    
    public PojoValueMapper(ValueGenerator... generators) {
        this.valueBag = new ValueBag(generators);
    }
    
    @Override
    public float[] getValues(double x0, double y0, double x1, double y1, int w, int h) {
        List<float[]> values = new ArrayList<>();
        
        for(ValueGenerator generator : get()) {
            values.add(generator.getValues(x0, y0, x1, y1, w, h));
        }
        
        return reduce(values);
    }
    
    protected float[] reduce(List<float[]> valuesList) {
        return valuesList.get(0);
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
    public Property[] getProperties() {
        return valueBag.getProperties();
    }
    
    @Override
    public void setProperty(String name, float value) {
        valueBag.setProperty(name, value);
    }

    @Override
    public String getHierarchicalName() {
        return this.getName() + valueBag.getHierarchicalName();
    }
}
