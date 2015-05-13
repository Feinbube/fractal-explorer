package luv.values.generators.lsystems.dragoncurve;

import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class PojoDragonCurve extends LSystem {

    @Override
    protected int getIterations() {
        return 14;
    }

    @Override
    protected LSystemNode getStartNode() {
        LSystemNode startNode = new LSystemNode(this);
        startNode.addChild(new FNode(this));
        startNode.addChild(new XNode(this));
        return startNode;
    }
}
