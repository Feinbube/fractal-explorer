package luv.values.generators.lsystems.fractalplant;

import luv.graphics.images.TurtlePainter;
import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

class MinusNode extends LSystemNode {

    public MinusNode(LSystem system) {
        super(system);
    }

    @Override
    protected void drawSelf(TurtlePainter turtlePainter) {
        turtlePainter.turn(-system.getAngle());
    }
}
