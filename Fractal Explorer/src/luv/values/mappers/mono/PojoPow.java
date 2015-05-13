package luv.values.mappers.mono;

import java.util.List;
import luv.values.Property;
import luv.values.mappers.PojoValueMapper;

public class PojoPow extends PojoValueMapper {

    float pow = 0.5f;

    @Override
    protected float[] reduce(List<float[]> valuesList) {
        float[] result = valuesList.get(0);

        for (int i = 0; i < result.length; i++) {
            result[i] = (float) Math.max(0.0, Math.min(1.0, Math.pow(result[i], pow)));
        }

        return result;
    }

    @Override
    public void setProperty(String name, float value) {
        if(name.equals("pow")) {
            pow = value;
        }
    }

    @Override
    public Property[] getProperties() {
        return new Property[]{new Property("pow", pow, 0.1f, 100.0f, 0.1f, 2.0f)};
    }
}
