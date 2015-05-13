package luv.values.generators.noise.perlin;

import luv.values.Property;
import luv.values.generators.ValueGenerator;

public class PojoMonoFractal extends ValueGenerator {

    float lacunarity = 2.02f;
    float increment = 1.0f;
    float octaves = 3.3f;
    float amplitude = 1.0f;

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("lacunarity", lacunarity, 0.1f, 5.0f, 0.1f, 2.02f),
            new Property("increment", increment, 0.1f, 5.0f, 0.1f, 1.0f),
            new Property("octaves", octaves, 0.1f, 5.0f, 0.1f, 3.3f),
            new Property("amplitude", amplitude, 0.1f, 5.0f, 0.1f, 1.0f)
        };
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("lacunarity")) {
            lacunarity = value;
        }
        if (name.equals("increment")) {
            increment = value;
        }
        if (name.equals("octaves")) {
            octaves = value;
        }
        if (name.equals("amplitude")) {
            amplitude = value;
        }
    }
}
