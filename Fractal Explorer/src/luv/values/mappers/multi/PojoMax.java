package luv.values.mappers.multi;

import java.util.List;
import luv.values.mappers.PojoValueMapper;

public class PojoMax extends PojoValueMapper {

    @Override
    protected float[] reduce(List<float[]> valuesList) {
        if (valuesList.size() == 1) {
            return valuesList.get(0);
        }
        float[] result = new float[valuesList.get(0).length];
        for (int i = 0; i < result.length; i++) {
            float max = Float.MIN_VALUE;
            for (float[] values : valuesList) {
                if (values[i] > max) {
                    max = values[i];
                }
            }
            result[i] = max;
        }
        return result;
    }
}
