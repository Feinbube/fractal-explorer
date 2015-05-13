package luv.values.generators.lsystems.sandboxsystem;

import luv.graphics.images.TurtlePainter;
import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class PlusNode extends LSystemNode {

    public PlusNode(LSystem system) {
        super(system);
    }

    @Override
    protected void drawSelf(TurtlePainter turtlePainter) {
        turtlePainter.turn(-system.getAngle());
    }
}
