package luv.values.animators;

import java.util.Objects;
import luv.values.Configurable;
import luv.values.Property;

public class SinglePropertyAnimator extends Animator {

    String propertyName;

    public SinglePropertyAnimator(Configurable configurable, String propertyName) {
        super(configurable);
        this.propertyName = propertyName;
    }

    @Override
    protected void update(Configurable configurable, Property property) {
        if (property.name.equals(this.propertyName)) {
            super.update(configurable, property);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof SinglePropertyAnimator && ((SinglePropertyAnimator) obj).propertyName.equals(this.propertyName);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.configurable) + Objects.hashCode(this.propertyName);
        return hash;
    }
}
