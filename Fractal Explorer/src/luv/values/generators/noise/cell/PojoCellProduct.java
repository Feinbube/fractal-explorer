package luv.values.generators.noise.cell;

public class PojoCellProduct extends PojoCellNoise {

    @Override
    protected float getNoise(double x, double y) {
        float sum = 1;

        for (int i = 0; i < 4; i++) {
            cellDataStruct.at[0] = (float) (0.1 * (i * 2 + 1) * (x + 20));
            cellDataStruct.at[1] = (float) (0.1 * (i * 2 + 1) * (y + 700));
            cellNoise.noise(cellDataStruct);
            sum *= cellDataStruct.F[0];
        }

        if (sum > 1.0f) {
            sum = 1.0f - (sum - 1.0f) / 2.0f;
        }

        return sum / 5.0f;
    }
}
