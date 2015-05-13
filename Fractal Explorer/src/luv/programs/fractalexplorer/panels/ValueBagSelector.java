package luv.programs.fractalexplorer.panels;

import java.util.Arrays;
import java.util.List;
import luv.values.Configurable;
import luv.values.mappers.ValueBag;

public class ValueBagSelector extends ConfigurableSelector {

    public ValueBagSelector(List<ValueBag> configurables) {
        super(Arrays.asList(configurables.toArray(new Configurable[]{})));
    }
    
    @Override
    protected boolean newButtonRowRequired(int i) {
        return true;
    }

    @Override
    protected String getButtonText(List<Configurable> configurables, int i) {
        return ((ValueBag) configurables.get(i)).getHierarchicalName();
    }
}
