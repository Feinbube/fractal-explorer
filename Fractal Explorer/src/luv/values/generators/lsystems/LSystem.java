package luv.values.generators.lsystems;

import luv.values.generators.ValueGenerator;
import luv.graphics.images.TurtlePainter;
import luv.values.Property;

public abstract class LSystem extends ValueGenerator {

    private final LSystemNode startNode;

    protected int iterations = 10;
    protected float lineLength = 3.0f;
    protected float angle = 90.0f;

    public LSystem() {
        startNode = getStartNode();
        iterations = getIterations();
        angle = getInitialAngle();
    }

    protected abstract LSystemNode getStartNode();

    protected abstract int getIterations();

    protected float getInitialAngle() {
        return 180;
    }

    public float getLineLength() {
        return lineLength;
    }

    public float getAngle() {
        return angle;
    }

    @Override
    protected float[] generateValues(double x0, double y0, double x1, double y1, int w, int h) {
        startNode.reset();
        for (int i = 0; i < iterations; ++i) {
            startNode.applyRules();
        }

        TurtlePainter turtlePainter = new TurtlePainter(w, h);

        turtlePainter.setZoom(w / (x1 - x0) / 100, h / (y1 - y0) / 100);
        turtlePainter.moveto(-x0 * w / (x1 - x0), -y0 * h / (y1 - y0));
        turtlePainter.setRotation(angle);

        turtlePainter.startDrawing();
        startNode.draw(turtlePainter);
        turtlePainter.stopDrawing();

        return toValues(turtlePainter.getImage());
    }

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("angle", angle, 0, 360, 0.5f, getInitialAngle()),
            new Property("lineLength", lineLength, 1.0f, 100.0f, 0.5f, 3.0f),
            new Property("iterations", iterations, 1, 20, 1, getIterations()),};
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("angle")) {
            angle = value;
        }
        if (name.equals("lineLength")) {
            lineLength = value;
        }
        if (name.equals("iterations")) {
            iterations = (int) value;
        }
    }
}
