package luv.values.mappers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import luv.opencl.OpenCL;
import luv.values.Configurable;
import luv.values.Property;
import luv.values.generators.HybridValueGenerator;
import luv.values.generators.ValueGenerator;

public class ValueBag extends Configurable implements ValueMapper, Serializable {

	private static final long serialVersionUID = -5850217820447784068L;
	
	protected List<ValueGenerator> generators;

    public ValueBag(ValueGenerator... generators) {
        this.generators = new ArrayList<>();
        this.generators.addAll(Arrays.asList(generators));
    }

    public void setOpenCL(final OpenCL opencl) {
    	for (ValueGenerator generator : generators) {
    		if (generator instanceof HybridValueGenerator) {
    			((HybridValueGenerator)generator).setOpenCL(opencl);
    		}
    	}
    }
    
    @Override
    public void set(List<ValueGenerator> generators) {
        this.generators = generators;
    }

    @Override
    public List<ValueGenerator> get() {
        return generators;
    }

    @Override
    public void add(ValueGenerator generator) {
        generators.add(generator);
    }

    @Override
    public Property[] getProperties() {
        return new Property[]{};
    }

    @Override
    public void setProperty(String name, float value) {

    }

    @Override
    public void replace(int index, ValueGenerator generator) {
        generators.set(index, generator);
    }

    @Override
    public void exchange(int index, ValueGenerator generator) {
        if ((generator instanceof ValueMapper) && (generators.get(index) instanceof ValueMapper)) {
            for (ValueGenerator child : ((ValueMapper) generators.get(index)).get()) {
                ((ValueMapper) generator).add(child);
            }
        }

        generator.copyPropertiesOf(generators.get(index));
        replace(index, generator);
    }

    @Override
    public ValueGenerator get(int index) {
        return generators.get(index);
    }

    @Override
    public int size() {
        return generators.size();
    }

    @Override
    public void remove(int index) {
        ValueGenerator generator = generators.get(index);
        if ((generator instanceof ValueMapper)) {
            for (ValueGenerator child : ((ValueMapper) generator).get()) {
                generators.add(index, child);
            }
        }

        generators.remove(generator);
    }
    
    
    
    @Override
    public String getHierarchicalName() {
        String result = "";
        for (int i = 0; i < generators.size() - 1; i++) {
            result += generators.get(i) instanceof ValueMapper 
                    ? ((ValueMapper)generators.get(i)).getHierarchicalName()+ " "
                    : generators.get(i).getName()+ " ";
        }
        result += generators.get(generators.size()-1) instanceof ValueMapper 
                    ? ((ValueMapper)generators.get(generators.size() - 1)).getHierarchicalName()+ " "
                    : generators.get(generators.size() - 1).getName()+ " ";
        return "{" + result + "}";
    }
}
