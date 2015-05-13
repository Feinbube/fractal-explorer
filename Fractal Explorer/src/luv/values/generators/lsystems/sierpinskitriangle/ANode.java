package luv.values.generators.lsystems.sierpinskitriangle;

import luv.graphics.images.TurtlePainter;
import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class ANode extends LSystemNode {

    public ANode(LSystem system) {
        super(system);
    }

    @Override
    public void drawSelf(TurtlePainter turtlePainter) {
        turtlePainter.forward(system.getLineLength());
    }

    @Override
    protected void applyRule() {
        addChild(new BNode(system));
        addChild(new MinusNode(system));
        addChild(new ANode(system));
        addChild(new MinusNode(system));
        addChild(new BNode(system));
    }

}
