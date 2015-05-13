package luv.values.generators.noise.simplex;

public class PojoNebulaNoise extends PojoFBMNoise {

    @Override
    public float at(double x, double y) {
        return (float) (Math.pow(super.at(x, y), 2) * 0.6);
    }
}
