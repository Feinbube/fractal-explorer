package luv.values.generators.lsystems.sierpinskitriangle;

import luv.graphics.images.TurtlePainter;
import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

public class PlusNode extends LSystemNode {

    public PlusNode(LSystem system) {
        super(system);
    }

    @Override
    public void drawSelf(TurtlePainter turtlePainter) {
        turtlePainter.turn(-system.getAngle());
    }
}
