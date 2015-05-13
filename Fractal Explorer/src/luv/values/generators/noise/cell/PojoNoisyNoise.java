package luv.values.generators.noise.cell;

public class PojoNoisyNoise extends PojoCellNoise {

    int channel;

    public PojoNoisyNoise(int channel) {
        this.channel = channel;
    }

    @Override
    protected float getNoise(double x, double y) {
        if (channel == 0) {
            return cellDataStruct.F[1] * 0.5f;
        } else if (channel == 2) {
            return cellDataStruct.F[0] * 0.5f;
        } else {
            return (cellDataStruct.F[1] + cellDataStruct.F[0]) * 0.5f;
        }
    }
}
