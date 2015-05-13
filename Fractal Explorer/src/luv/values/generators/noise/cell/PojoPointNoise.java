package luv.values.generators.noise.cell;

public class PojoPointNoise extends PojoCellNoise {

    @Override
    protected float getNoise(double x, double y) {
        return (float) Math.max(1.0, 1.0 - this.cellDataStruct.F[0]);
    }
}
