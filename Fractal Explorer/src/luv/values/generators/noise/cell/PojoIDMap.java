package luv.values.generators.noise.cell;

public class PojoIDMap extends PojoCellNoise {

    @Override
    protected float getNoise(double x, double y) {
        return (cellDataStruct.ID[0] % 255) / 255.0f;
    }
}
