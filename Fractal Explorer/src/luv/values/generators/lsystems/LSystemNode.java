package luv.values.generators.lsystems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import luv.graphics.images.TurtlePainter;

public class LSystemNode {

    protected List<LSystemNode> children;
    protected LSystem system;

    public LSystemNode(LSystem system) {
        this.children = new ArrayList<>();
        this.system = system;
    }

    public LSystemNode(LSystem system, LSystemNode... children) {
        this.children = new ArrayList<>(Arrays.asList(children));
        this.system = system;
    }

    public void addChild(LSystemNode child) {
        children.add(child);
    }

    public void reset() {
        children.clear();
    }

    public void draw(TurtlePainter turtlePainter) {
        if (children.isEmpty()) {
            drawSelf(turtlePainter);
        } else {
            for (LSystemNode child : children) {
                child.draw(turtlePainter);
            }
        }
    }

    protected void drawSelf(TurtlePainter turtlePainter) {
    }

    public void applyRules() {
        if (children.isEmpty()) {
            applyRule();
        } else {
            for (LSystemNode child : children) {
                child.applyRules();
            }
        }
    }

    protected void applyRule() {
    }
}
