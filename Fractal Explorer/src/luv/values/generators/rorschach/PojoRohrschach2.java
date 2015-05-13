package luv.values.generators.rorschach;

import java.util.Random;
import luv.values.Property;
import luv.values.generators.ValueGenerator;

public class PojoRohrschach2 extends ValueGenerator {

    int nrHeatPoints;
    float heatPointsX[];
    float heatPointsY[];
    float heatPointsR[];

    int randomSeed = 1;
    float slim = 0.35f;
    float maxJump = 0.5f;
    float maxHeatChange = 0.04f;
    float stretch = 10;

    int nrHeatPointsMin = 1000;
    int nrHeatPointsMax = 5000;

    public PojoRohrschach2() {
        setup();
    }

    synchronized protected void setup() {
        Random random = new Random(randomSeed);

        nrHeatPoints = random.nextInt(nrHeatPointsMax - nrHeatPointsMin) + nrHeatPointsMin;
        heatPointsX = new float[nrHeatPoints];
        heatPointsY = new float[nrHeatPoints];
        heatPointsR = new float[nrHeatPoints];

        heatPointsX[0] = change(random, 0, stretch / 2);
        heatPointsY[0] = change(random, 0, stretch);
        heatPointsR[0] = random.nextFloat();
        for (int i = 1; i < nrHeatPoints; i++) {
            heatPointsX[i] = change(random, heatPointsX[i - 1], maxJump);
            heatPointsY[i] = change(random, heatPointsY[i - 1], maxJump);
            heatPointsR[i] = random.nextFloat();

            if (heatPointsX[i] < 0 || heatPointsX[i] > stretch / 2) {
                heatPointsX[i] = change(random, 0, stretch / 2);
            }
            if (heatPointsY[i] < -stretch / 2 || heatPointsY[i] > stretch / 2) {
                heatPointsY[i] = change(random, 0, stretch);
            }
        }
    }

    private float change(Random random, float value, float range) {
        return value + random.nextFloat() * range - range / 2.0f;
    }

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("randomSeed", randomSeed, 1, Integer.MAX_VALUE, 1, 1),
            new Property("slim", slim, 0.001f, 2.0f, 0.1f, 0.35f),
            new Property("maxJump", maxJump, 0.01f, 5.0f, 0.1f, 0.5f),
            new Property("maxHeatChange", maxHeatChange, 0.001f, 2.0f, 1, 1),
            new Property("stretch", stretch, 1, 50, 1, 10),
            new Property("nrHeatPointsMin", nrHeatPointsMin, 1, 10000, 100, 1000),
            new Property("nrHeatPointsMax", nrHeatPointsMax, 1, 10000, 100, 1000),
            
        };
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {      
        if (name.equals("randomSeed")) {
            randomSeed = (int) value;
        }
        if (name.equals("slim")) {
            slim = value;
        }
        if (name.equals("maxJump")) {
            maxJump = value;
        }
        if (name.equals("maxHeatChange")) {
            maxHeatChange = value;
        }
        if (name.equals("stretch")) {
            stretch = value;
        }
        if (name.equals("nrHeatPointsMin")) {
            nrHeatPointsMin = (int) value;
        }
        if (name.equals("nrHeatPointsMax")) {
            nrHeatPointsMax = (int) value;
        }

        setup();
    }

    @Override
    protected float at(double x, double y) {
        return atXY((float) x, (float) y);
    }

    synchronized float atXY(float x, float y) {
        if (x < 0) {
            x = -x;
        }
        float result = 0.0f;
        for (int i = 0; i < nrHeatPoints; i++) {
            float d = distanceSQR(x, y, heatPointsX[i], heatPointsY[i]);
            float hr = heatPointsR[i] * slim;
            if (d < hr * hr) {
                result = 1.0f;
                break;
            }
        }

        return result < 0.0f ? 0.0f : result > 1.0f ? 1.0f : (float) result;
    }

    private float distanceSQR(float x0, float y0, float x1, float y1) {
        return ((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
    }
}
