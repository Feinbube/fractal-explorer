package luv.values.generators.noise.simplex;

import luv.values.Property;

public class PojoFBMNoise extends PojoSimplexNoise {

    int octaves;
    double persistance;

    public PojoFBMNoise() {
        this(8, 0.5);
    }

    public PojoFBMNoise(int octaves) {
        this(octaves, 0.5);
    }

    public PojoFBMNoise(int octaves, double persistance) {
        this.octaves = octaves;
        this.persistance = persistance;
    }

    @Override
    public float at(double x, double y) {
        double total = 0.0;

        double frequency = 1.0;
        double amplitude = this.persistance;

        for (int i = 0; i < this.octaves; ++i) {
            total += super.at(x * frequency, y * frequency) * amplitude;

            frequency *= 2.0;
            amplitude *= this.persistance;
        }

        return (float) total;
    }

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("octaves", octaves, 1, 32, 1, 8),
            new Property("persistance", (float) persistance, 0.0f, 1.0f, 0.02f, 0.5f)
        };
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("octaves")) {
            octaves = (int) value;
        }
        if (name.equals("persistance")) {
            persistance = value;
        }
    }
}
