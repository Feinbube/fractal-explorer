package luv.values.mappers.mono;

import java.util.List;
import luv.values.Property;
import luv.values.mappers.PojoValueMapper;

public class PojoAdd extends PojoValueMapper {

    float add = 0.5f;

    @Override
    protected float[] reduce(List<float[]> valuesList) {
        float[] result = valuesList.get(0);

        for (int i = 0; i < result.length; i++) {
            result[i] = (float) Math.max(0.0, Math.min(1.0, result[i] + add));
        }

        return result;
    }

    @Override
    public void setProperty(String name, float value) {
        if(name.equals("add")) {
            add = value;
        }
    }

    @Override
    public Property[] getProperties() {
        return new Property[]{new Property("add", add, -1.0f, 1.0f, 0.01f, 0.5f)};
    }
}
