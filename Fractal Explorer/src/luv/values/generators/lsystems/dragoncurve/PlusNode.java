package luv.values.generators.lsystems.dragoncurve;

import luv.graphics.images.TurtlePainter;
import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

class PlusNode extends LSystemNode {

    public PlusNode(LSystem system) {
        super(system);
    }

    @Override
    protected void drawSelf(TurtlePainter turtlePainter) {
        turtlePainter.turn(system.getAngle());
    }
}
