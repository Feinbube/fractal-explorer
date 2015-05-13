package luv.values.generators.lsystems.fractalplant;

import luv.graphics.images.TurtlePainter;
import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

class BracketsNode extends LSystemNode {

    public BracketsNode(LSystem system, LSystemNode... children) {
        super(system, children);
    }

    @Override
    public void draw(TurtlePainter turtlePainter) {

        double x = turtlePainter.getX();
        double y = turtlePainter.getY();
        double angle = turtlePainter.getRotation();

        for (LSystemNode child : children) {
            child.draw(turtlePainter);
        }

        turtlePainter.stopDrawing();
        turtlePainter.moveto(x, y);
        turtlePainter.setRotation(angle);
        turtlePainter.startDrawing();
    }
}
