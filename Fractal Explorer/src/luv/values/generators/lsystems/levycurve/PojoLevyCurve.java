package luv.values.generators.lsystems.levycurve;

import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class PojoLevyCurve extends LSystem {

    @Override
    protected int getIterations() {
        return 13;
    }

    @Override
    protected LSystemNode getStartNode() {
        return new FNode(this);
    }

    public PojoLevyCurve() {
        lineLength = 8.0f;
        angle = 45.0f;
    }

    @Override
    protected float getInitialAngle() {
        return 90;
    }
}
