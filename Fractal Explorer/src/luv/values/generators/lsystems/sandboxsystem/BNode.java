package luv.values.generators.lsystems.sandboxsystem;

import luv.graphics.images.TurtlePainter;
import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class BNode extends LSystemNode {

    public BNode(LSystem system) {
        super(system);
    }

    @Override
    protected void applyRule() {
        addChild(new ANode(system));
        addChild(new MinusNode(system));
        addChild(new CNode(system));
        addChild(new MinusNode(system));
        addChild(new ANode(system));
    }

    @Override
    protected void drawSelf(TurtlePainter turtlePainter) {
        turtlePainter.forward(system.getLineLength());
    }
}
