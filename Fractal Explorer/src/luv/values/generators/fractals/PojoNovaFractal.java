package luv.values.generators.fractals;

import luv.values.Property;
import luv.values.generators.ValueGenerator;

public class PojoNovaFractal extends ValueGenerator {

    int maxIterations = 50;

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("maxIterations", maxIterations, 0, 10000, 1, 50),};
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("maxIterations")) {
            maxIterations = (int) value;
        }
    }
}
