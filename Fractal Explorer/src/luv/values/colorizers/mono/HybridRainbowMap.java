package luv.values.colorizers.mono;

import java.awt.Color;
import luv.opencl.OpenCL;
import luv.values.colorizers.Colorizer;

public class HybridRainbowMap extends HybridColorMap {

    public HybridRainbowMap(OpenCL openCL) {
        super(openCL);
    }

    @Override
    protected int[] getColors() {
        return new int[]{
            new Color(255, 0, 0).hashCode(),
            new Color(255, 255, 0).hashCode(),
            new Color(0, 255, 0).hashCode(),
            new Color(0, 255, 255).hashCode(),
            new Color(0, 0, 255).hashCode(),
            new Color(255, 0, 255).hashCode()
        };
    }

    @Override
    protected Colorizer getNewPOJOImplementation() {
        return new PojoRainbowMap();
    }
}
