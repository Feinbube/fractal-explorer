package luv.values.generators.noise.simplex;

public class PojoStarNoise extends PojoFBMNoise {

    @Override
    public float at(double x, double y) {
        return (float) Math.min(1.0f, Math.max(0.0f, ((Math.pow(super.at(x, y), 4) * 2 - 0.8) * 2)));
    }
}
