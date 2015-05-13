package luv.values.generators.lsystems.sandboxsystem;

import java.util.Random;

import luv.graphics.images.TurtlePainter;
import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class CNode extends LSystemNode {

    public CNode(LSystem system) {
        super(system);
    }

    private final Random random = new Random();
    private final Boolean randomRule = true;

    @Override
    protected void applyRule() {
        final Boolean b = randomRule && random.nextBoolean();
        if (b) {
            addChild(new BNode(system));
            addChild(new MinusNode(system));
            addChild(new BNode(system));
        } else {
            addChild(new ANode(system));
            addChild(new MinusNode(system));
            addChild(new ANode(system));
        }
    }

    @Override
    protected void drawSelf(TurtlePainter turtlePainter) {
        turtlePainter.forward(system.getLineLength() / 2);
    }
}
