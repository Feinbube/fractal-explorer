package luv.values.mappers.mono;

import java.util.List;
import luv.values.Property;
import luv.values.mappers.PojoValueMapper;

public class PojoCrop extends PojoValueMapper {

    float min = 0.1f;
    float max = 0.9f;

    @Override
    protected float[] reduce(List<float[]> valuesList) {
        float[] result = valuesList.get(0);

        for (int i = 0; i < result.length; i++) {
            float value = result[i];
            if(value > max) {
                result[i] = max;
            }
            if(value < min) {
                result[i] = min;
            }
        }

        return result;
    }

    @Override
    public void setProperty(String name, float value) {
        if (name.equals("min")) {
            min = value > max ? max : value;
        }
        if (name.equals("max")) {
            max = value < min ? min : value;
        }
    }

    @Override
    public Property[] getProperties() {
        return new Property[]{
            new Property("min", min, 0.0f, 1.0f, 0.01f, 0.1f),
            new Property("max", max, 0.0f, 1.0f, 0.01f, 0.9f)
        };
    }
}
