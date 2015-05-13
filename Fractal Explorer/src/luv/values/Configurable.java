package luv.values;

public abstract class Configurable extends Named {

    public abstract Property[] getProperties();

    public abstract void setProperty(String name, float value);

    public float getPropertyValue(String propertyName) {
        for (Property property : getProperties()) {
            if (property.name.equals(propertyName)) {
                return property.value;
            }
        }

        throw new RuntimeException("Property with name " + propertyName + " is not supported!");
    }
    
    public void copyPropertiesOf(Configurable other){
        for(Property property : other.getProperties()) {
            this.setProperty(property.name, property.value);
        }
    }

    @Override
    public String toString() {
        return super.toString() + detailInfo("[", getProperties(), "]");
    }
}
