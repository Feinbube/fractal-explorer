package luv.values.generators.lsystems.levycurve;

import luv.graphics.images.TurtlePainter;
import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class FNode extends LSystemNode {

    public FNode(LSystem system) {
        super(system);
    }

    @Override
    protected void applyRule() {
        addChild(new PlusNode(system));
        addChild(new FNode(system));
        addChild(new MinusNode(system));
        addChild(new MinusNode(system));
        addChild(new FNode(system));
        addChild(new PlusNode(system));
    }

    @Override
    protected void drawSelf(TurtlePainter turtlePainter) {
        turtlePainter.forward(system.getLineLength());
    }
}
