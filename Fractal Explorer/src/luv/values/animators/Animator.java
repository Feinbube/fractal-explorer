package luv.values.animators;

import java.util.Objects;
import luv.values.Configurable;
import luv.values.Property;

public class Animator {

    final Configurable configurable;

    public Animator(Configurable configurable) {
        this.configurable = configurable;
    }

    public boolean animate() {
        return animateConfigurable(configurable);
    }

    protected boolean animateConfigurable(Configurable configurable) {
        Property[] properties = configurable.getProperties();
        if (properties.length == 0) {
            return false;
        }

        for (Property property : properties) {
            update(configurable, property);
        }

        return true;
    }

    protected void update(Configurable configurable, Property property) {
        float value = property.value + property.stepSize;
        if (value > property.maxValue) {
            value = property.minValue;
        }
        configurable.setProperty(property.name, value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Animator && ((Animator) obj).configurable.equals(this.configurable);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.configurable);
        return hash;
    }
}
