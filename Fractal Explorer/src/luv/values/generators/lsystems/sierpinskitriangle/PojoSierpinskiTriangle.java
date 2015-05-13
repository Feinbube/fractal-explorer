package luv.values.generators.lsystems.sierpinskitriangle;

import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class PojoSierpinskiTriangle extends LSystem {

    public PojoSierpinskiTriangle() {
        angle = 60.0f;
    }

    @Override
    protected int getIterations() {
        return 8;
    }

    @Override
    protected LSystemNode getStartNode() {
        return new ANode(this);
    }

    @Override
    protected float getInitialAngle() {
        return 90;
    }
}
