package luv.values.generators.lsystems.fractalplant;

import luv.values.generators.lsystems.LSystem;
import luv.values.generators.lsystems.LSystemNode;

class XNode extends LSystemNode {

    public XNode(LSystem system) {
        super(system);
    }

    @Override
    protected void applyRule() {
        addChild(new FNode(system));
        addChild(new MinusNode(system));
        addChild(new BracketsNode(system,
                new BracketsNode(system,
                        new XNode(system)),
                new PlusNode(system),
                new XNode(system)));
        addChild(new PlusNode(system));
        addChild(new FNode(system));
        addChild(new BracketsNode(system,
                new PlusNode(system),
                new FNode(system),
                new XNode(system)));
        addChild(new MinusNode(system));
        addChild(new XNode(system));
    }
}
