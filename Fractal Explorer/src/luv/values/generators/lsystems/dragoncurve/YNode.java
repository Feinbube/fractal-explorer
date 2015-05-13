package luv.values.generators.lsystems.dragoncurve;

import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

class YNode extends LSystemNode {

    public YNode(LSystem system) {
        super(system);
    }

    @Override
    protected void applyRule() {
        addChild(new FNode(system));
        addChild(new XNode(system));
        addChild(new MinusNode(system));
        addChild(new YNode(system));
    }
}
