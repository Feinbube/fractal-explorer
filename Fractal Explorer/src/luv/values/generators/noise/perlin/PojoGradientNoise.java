package luv.values.generators.noise.perlin;

import luv.values.Property;
import luv.values.generators.ValueGenerator;

public class PojoGradientNoise extends ValueGenerator {

    float amplitude = 1.0f;

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("amplitude", amplitude, 0.1f, 5.0f, 0.1f, 1.0f)
        };
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("amplitude")) {
            amplitude = value;
        }
    }
}
