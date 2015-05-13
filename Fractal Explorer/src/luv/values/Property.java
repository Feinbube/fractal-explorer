package luv.values;

public class Property {

    public final String name;
    public final float value;

    public final float minValue;
    public final float maxValue;
    public final float defaultValue;
    public final float stepSize;

    public Property(String name, float value, float minValue, float maxValue, float stepSize, float defaultValue) {
        this.name = name;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.defaultValue = defaultValue;
        this.stepSize = stepSize;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
    
    public static Property[] Merge(Property[] properties, Property[] additionalProperties) {
        if(additionalProperties.length == 0) {
            return properties;
        }
        Property[] result = new Property[properties.length + additionalProperties.length];
        int i = 0;
        for(Property property : properties) {
            result[i++] = property;
        }
        for(Property property : additionalProperties) {
            result[i++] = property;
        }
        return result;
    }
}
