package luv.values.generators.fractals;

import luv.values.Property;
import luv.values.generators.ValueGenerator;

public class PojoBuddhabrot extends ValueGenerator {

    int minIterations = 0;
    int maxIterations = 50;
    
    public PojoBuddhabrot(int minIterations, int maxInterations) {
        this.minIterations = minIterations;
        this.maxIterations = maxInterations;
    }

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("minIterations", minIterations, 0, 10000, 1, 0),
            new Property("maxIterations", maxIterations, 0, 10000, 1, 50)
        };
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("minIterations")) {
            minIterations = (int) value;
        }
        if (name.equals("maxIterations")) {
            maxIterations = (int) value;
        }
    }
}
