package luv.values.mappers.multi;

import java.util.List;
import luv.values.mappers.PojoValueMapper;

public class PojoProduct extends PojoValueMapper {

    @Override
    protected float[] reduce(List<float[]> valuesList) {
        if (valuesList.size() == 1) {
            return valuesList.get(0);
        }
        float[] result = new float[valuesList.get(0).length];
        for (float[] values : valuesList) {
            for (int i = 0; i < result.length; i++) {
                result[i] *= values[i];
            }
        }
        return result;
    }
}
