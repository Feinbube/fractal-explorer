package luv.values.generators.rorschach;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import luv.values.Property;
import luv.values.generators.ValueGenerator;

public class PojoHeatTransfer extends ValueGenerator {

    List<HeatPoint> heatPoints = new ArrayList<>();

    int nrIterations = 20000;
    int nrHeatPoints = 10;
    int randomSeed = 1;
    int strengthVariation = 10;

    public PojoHeatTransfer() {
        setup();
    }

    synchronized protected void setup() {
        Random random = new Random(randomSeed);
        heatPoints = new ArrayList<>();
        for (int i = 0; i < nrHeatPoints; i++) {
            heatPoints.add(new HeatPoint(random, 10, strengthVariation));
        }
    }

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("randomSeed", randomSeed, 1, Integer.MAX_VALUE, 1, 1),
            new Property("nrHeatPoints", nrHeatPoints, 1, 100, 1, 10),
            new Property("nrIterations", nrIterations, 1, 50000, 1000, 20000),
            new Property("strengthVariation", strengthVariation, 0, 100, 1, 10)
        };
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("nrIterations")) {
            nrIterations = (int) value;
        }
        if (name.equals("randomSeed")) {
            randomSeed = (int) value;
            setup();
        }
        if (name.equals("nrHeatPoints")) {
            nrHeatPoints = (int) value;
            setup();
        }
        if (name.equals("strengthVariation")) {
            strengthVariation = (int) value;
            setup();
        }
    }

    @Override
    protected float at(double x, double y) {
        return atXY(x, y);
    }

    synchronized float atXY(double x, double y) {
        double result = 0.0;
        for (HeatPoint heatPoint : heatPoints) {
            double d = distance(x, y, heatPoint.x, heatPoint.y);
            if (d > 0.0) {
                result += heatPoint.strength / d;
            }
        }

        result = (nrIterations * result * 0.000001);
        return result < 0.0 ? 0.0f : result > 1.0 ? 1.0f : (float) result;
    }

    private double distance(double x0, double y0, double x1, double y1) {
        return Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
    }
}
