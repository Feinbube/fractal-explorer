package luv.values.generators.noise.simplex;

public class PojoRigidFBM extends PojoSimplexNoise {

    @Override
    public float at(double x, double y) {
        return (float) (1.0 - Math.abs(super.at(x, y)));
    }
}
