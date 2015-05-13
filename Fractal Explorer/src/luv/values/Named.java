package luv.values;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Named {

    String categoryName = null;
    String className = null;

    public String getName() {
        if (className == null) {
            beautifulName();
        }
        return className;
    }

    public String getCategoryName() {
        if (categoryName == null) {
            beautifulName();
        }
        return categoryName;
    }
    
    public String getDetailedName() {
        if (className == null) {
            beautifulName();
        }
        return (categoryName.equals("") ? "" : categoryName + "/") + className + detailInfo("(", getDetails(), ")");
    }

    @Override
    public String toString() {
        return getDetailedName();
    }

    protected void beautifulName() {
        String result = this.getClass().getName();
        result = result.replace("luv.values.generators.", "").replace("luv.values.colorizers.", "").replace("luv.values.mappers.", "");
        result = result.replace("Hybrid", "").replace("Pojo", "J");
        String[] subStrings = result.split("\\.");

        className = subStrings[subStrings.length - 1];

        categoryName = "";
        for (int i = 0; i < subStrings.length - 1; i++) {
            String category = subStrings[i].substring(0, 1).toUpperCase() + subStrings[i].substring(1);
            if (!className.toLowerCase().equals(category.toLowerCase()) && !className.toLowerCase().equals("j" + category.toLowerCase())) {
                categoryName += category + "/";
            }
        }
        if (categoryName.length() >= 1) {
            categoryName = categoryName.substring(0, categoryName.length() - 1);
        }
    }

    protected Object[] getDetails() {
        return null;
    }

    protected String detailInfo(String before, Object[] objects, String after) {
        if (objects == null || objects.length == 0) {
            return "";
        }

        String result = "";
        for (int i = 0; i < objects.length - 1; i++) {
            result += objects[i].toString() + "; ";
        }
        result += objects[objects.length - 1].toString();
        return before + result + after;
    }

    @Override
    public int hashCode() {
        return this.getCategoryName().hashCode() + this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Named other = (Named) obj;
        if (!Objects.equals(this.getCategoryName(), other.getCategoryName())) {
            return false;
        }
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        return true;
    }

    public static List<String> getCategories(final List<? extends Named> configurables) {
        List<String> result = new ArrayList<>();

        for (Named configurable : configurables) {
            if (!result.contains(configurable.getCategoryName())) {
                result.add(configurable.getCategoryName());
            }
        }

        return result;
    }

    public static <T extends Named> List<T> getWithCategory(final List<T> configurables, final String category) {
        List<T> result = new ArrayList<>();

        for (T configurable : configurables) {
            if (configurable.getCategoryName().equals(category)) {
                result.add((T) configurable);
            }
        }

        return result;
    }

    public static <T extends Named> T getByName(final List<T> named, final String name) {
        for (final T c : named) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }
}
