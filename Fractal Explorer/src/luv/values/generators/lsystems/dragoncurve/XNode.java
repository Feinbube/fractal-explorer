package luv.values.generators.lsystems.dragoncurve;

import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

class XNode extends LSystemNode {

    public XNode(LSystem system) {
        super(system);
    }

    @Override
    protected void applyRule() {
        addChild(new XNode(system));
        addChild(new PlusNode(system));
        addChild(new YNode(system));
        addChild(new FNode(system));
    }
}
