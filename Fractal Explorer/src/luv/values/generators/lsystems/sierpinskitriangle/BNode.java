package luv.values.generators.lsystems.sierpinskitriangle;

import luv.graphics.images.TurtlePainter;
import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class BNode extends LSystemNode {

    public BNode(LSystem system) {
        super(system);
    }

    @Override
    public void drawSelf(TurtlePainter turtlePainter) {
        turtlePainter.forward(system.getLineLength());
    }

    @Override
    protected void applyRule() {
        addChild(new ANode(system));
        addChild(new PlusNode(system));
        addChild(new BNode(system));
        addChild(new PlusNode(system));
        addChild(new ANode(system));
    }
}
