package luv.values.generators.rorschach;

import java.util.ArrayList;
import java.util.Random;
import luv.values.Property;

public class PojoRohrschach extends PojoHeatTransfer {

    int walkLength = 8;
    float stretchVariance = 0.2f;

    public PojoRohrschach() {
        setup();
    }

    @Override
    synchronized protected void setup() {
        Random random = new Random(randomSeed);
        heatPoints = new ArrayList<>();

        for (int i = 0; i < nrHeatPoints; i++) {
            heatPoints.add(new HeatPoint(random, 10, strengthVariation));
        }

        for (int w = 0; w < walkLength + 1; w++) {
            for (int i = 0; i < nrHeatPoints; i++) {
                heatPoints.add(new HeatPoint(random, 10, stretchVariance, strengthVariation, heatPoints.get(i + w * nrHeatPoints)));
            }
        }

        int size = heatPoints.size();
        for (int i = 0; i < size; i++) {
            HeatPoint other = new HeatPoint(heatPoints.get(i));
            other.x = -other.x;
            heatPoints.add(other);
        }
    }

    @Override
    protected Property[] getAdditionalProperties() {
        return Property.Merge(super.getAdditionalProperties(), new Property[]{
            new Property("stretchVariance", stretchVariance, 0.0f, 2.0f, 0.1f, 0.2f),
            new Property("walkLength", walkLength, 1, 100, 1, 8)
        });
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("walkLength")) {
            walkLength = (int) value;
            setup();
        }
        if (name.equals("stretchVariance")) {
            stretchVariance = value;
            setup();
        }
        super.setAdditionalProperty(name, value);
    }
}
