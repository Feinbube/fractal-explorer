package luv.values.generators.lsystems.fractalplant;

import luv.graphics.images.TurtlePainter;
import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

class FNode extends LSystemNode {

    public FNode(LSystem system) {
        super(system);
    }

    @Override
    protected void drawSelf(TurtlePainter turtlePainter) {
        turtlePainter.forward(system.getLineLength());
    }

    @Override
    protected void applyRule() {
        addChild(new FNode(system));
        addChild(new FNode(system));
    }
}
