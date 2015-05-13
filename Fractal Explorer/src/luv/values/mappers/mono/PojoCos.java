package luv.values.mappers.mono;

import java.util.List;
import luv.values.Property;
import luv.values.mappers.PojoValueMapper;

public class PojoCos extends PojoValueMapper {

    float stretch = 3.14159f;

    @Override
    protected float[] reduce(List<float[]> valuesList) {
        float[] result = valuesList.get(0);

        for (int i = 0; i < result.length; i++) {
            result[i] = (float) Math.max(0.0, Math.min(1.0, Math.cos(result[i] * stretch)));
        }

        return result;
    }

    @Override
    public void setProperty(String name, float value) {
        if(name.equals("stretch")) {
            stretch = value;
        }
    }

    @Override
    public Property[] getProperties() {
        return new Property[]{new Property("stretch", stretch, 0.1f, 100.0f, 0.1f, 3.14159f)};
    }
}
