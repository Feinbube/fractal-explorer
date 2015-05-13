package luv.values.mappers.mono;

import java.util.List;
import luv.values.mappers.PojoValueMapper;

public class PojoInvert extends PojoValueMapper {
    
    @Override
    protected float[] reduce(List<float[]> valuesList) {
        float[] result = valuesList.get(0);
        
        for(int i=0; i<result.length; i++) {
            result[i] = 1.0f - result[i];
        }
        
        return result;
    }
}
