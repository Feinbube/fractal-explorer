package luv.values.generators.lsystems.fractalplant;

import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class PojoFractalPlant extends LSystem {

    public PojoFractalPlant() {
        angle = 25.0f;
    }

    @Override
    protected int getIterations() {
        return 6;
    }

    @Override
    protected LSystemNode getStartNode() {
        return new XNode(this);
    }

    @Override
    protected float getInitialAngle() {
        return 180 - 25;
    }
}
