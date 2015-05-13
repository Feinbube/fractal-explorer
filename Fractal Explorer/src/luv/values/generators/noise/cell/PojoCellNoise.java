package luv.values.generators.noise.cell;

import luv.values.Property;
import luv.values.generators.ValueGenerator;

public class PojoCellNoise extends ValueGenerator {

    int distanceFunction = CellNoiseImpl.EUCLIDEAN;

    CellNoiseImpl cellNoise = null;
    CellDataStruct cellDataStruct = null;

    public PojoCellNoise() {
    }

    @Override
    public float at(double x, double y) {
        noise(x, y);
        return getNoise(x, y);
    }

    protected void noise(double x, double y) {
        if (cellNoise == null) {
            cellNoise = new CellNoiseImpl();
        }

        if (cellDataStruct == null) {
            cellDataStruct = new CellDataStruct(new float[]{0, 0}, distanceFunction);
        }

        cellDataStruct.at = new float[]{(float) x, (float) y};
        cellNoise.noise(cellDataStruct);
    }

    protected float getNoise(double x, double y) {
        float sum = 1;

        for (int i = 0; i < 4; i++) {
            cellDataStruct.at[0] = (float) (0.1 * (i * 2 + 1) * (x + 20));
            cellDataStruct.at[1] = (float) (0.1 * (i * 2 + 1) * (y + 700));
            cellNoise.noise(cellDataStruct);
            sum += cellDataStruct.F[0];
        }
        return sum / 10.0f;
    }

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("distanceFunction", distanceFunction, 1, 4, 1, 1)
        };
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("distanceFunction")) {
            distanceFunction = (int) value;
        }
    }
}
