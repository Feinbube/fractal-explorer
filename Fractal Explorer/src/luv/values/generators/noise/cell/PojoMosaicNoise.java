package luv.values.generators.noise.cell;

public class PojoMosaicNoise extends PojoCellNoise {

    @Override
    protected float getNoise(double x, double y) {
        return (float) Math.min(1.0f, (this.cellDataStruct.F[1] - this.cellDataStruct.F[0]) / 3.0f);
    }
}
