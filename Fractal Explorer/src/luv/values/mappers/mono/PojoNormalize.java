package luv.values.mappers.mono;

import java.util.List;
import luv.values.mappers.PojoValueMapper;

public class PojoNormalize extends PojoValueMapper {

    @Override
    protected float[] reduce(List<float[]> valuesList) {
        float[] result = valuesList.get(0);
        
        float minimum = Float.MAX_VALUE;
        float maximum = Float.MIN_VALUE;

        for (float value : result) {
            if (value < minimum) {
                minimum = value;
            } else if (value > maximum) {
                maximum = value;
            }
        }
        
        for(int i=0; i<result.length; i++) {
            result[i] = (result[i] - minimum) / (maximum - minimum);
        }
        return result;
    }
}
