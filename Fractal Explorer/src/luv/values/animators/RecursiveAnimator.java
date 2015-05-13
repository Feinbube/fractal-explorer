package luv.values.animators;

import luv.values.Configurable;
import luv.values.generators.ValueGenerator;
import luv.values.mappers.ValueMapper;

public class RecursiveAnimator extends Animator {

    public RecursiveAnimator(Configurable configurable) {
        super(configurable);
    }

    @Override
    protected boolean animateConfigurable(Configurable configurable) {
        boolean result = super.animateConfigurable(configurable);

        if (configurable instanceof ValueMapper) {
            for (ValueGenerator child : ((ValueMapper) configurable).get()) {
                result = animateConfigurable(child) || result;
            }
        }
        return result;
    }
}
