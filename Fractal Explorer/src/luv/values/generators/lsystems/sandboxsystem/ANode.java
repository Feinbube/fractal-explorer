package luv.values.generators.lsystems.sandboxsystem;

import luv.graphics.images.TurtlePainter;
import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class ANode extends LSystemNode {

    public ANode(LSystem system) {
        super(system);
    }

    @Override
    protected void applyRule() {
        addChild(new BNode(system));
        addChild(new PlusNode(system));
        addChild(new CNode(system));
        addChild(new PlusNode(system));
        addChild(new BNode(system));
    }

    @Override
    protected void drawSelf(TurtlePainter turtlePainter) {
        turtlePainter.forward(system.getLineLength());
    }
}
