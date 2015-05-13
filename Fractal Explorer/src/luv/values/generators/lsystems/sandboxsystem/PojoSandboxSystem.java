package luv.values.generators.lsystems.sandboxsystem;

import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class PojoSandboxSystem extends LSystem {

    public PojoSandboxSystem() {
        lineLength = 5.0f;
        angle = 0.0f;
    }

    @Override
    protected int getIterations() {
        return 10;
    }

    @Override
    protected LSystemNode getStartNode() {
        LSystemNode startNode = new LSystemNode(this);
        startNode.addChild(new CNode(this));
        return startNode;
    }
}
