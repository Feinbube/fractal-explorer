package luv.values.generators.noise.simplex;

import luv.values.Property;

public final class PojoValueNoise extends PojoSimplexNoise {

    double distortion = 10.0;

    public PojoValueNoise() {
        this(10.0);
    }

    public PojoValueNoise(double distortion) {
        super();

        this.distortion = distortion;
    }

    @Override
    public float at(double x, double y) {
        final double dx = super.at(x + 0.5, y) * this.distortion;
        final double dy = super.at(x, y + 0.5) * this.distortion;

        return super.at(x + dx, y + dy);
    }

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("distortion", (float) distortion, 1.0f, 100.0f, 1, 10.0f)
        };
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("distortion")) {
            distortion = value;
        }
    }
}
