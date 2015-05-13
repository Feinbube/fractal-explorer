package luv.values.mappers;

import java.util.List;
import luv.values.generators.ValueGenerator;

public interface ValueMapper {

    public void set(List<ValueGenerator> generators);

    public List<ValueGenerator> get();

    public void add(ValueGenerator generator);

    public void replace(int index, ValueGenerator generator);
    
    public void exchange(int index, ValueGenerator generator);

    public void remove(int index);

    public ValueGenerator get(int index);

    public int size();

    public String getHierarchicalName();
}
