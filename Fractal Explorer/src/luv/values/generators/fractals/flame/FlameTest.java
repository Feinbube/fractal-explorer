package luv.values.generators.fractals.flame;

import java.awt.Color;

public class FlameTest extends FlameEven {

    // I think it is right (since that's what the others do as well), but it still looks weird
    // 9 22

    // I have no idea what clever parameters could be for
    // 38
    
    int index = 0;
    
    public FlameTest() {
        steps = 5;
        iterations = 1;
        
        randomFlame();
    }
    
    @Override
    public void randomFlame() {
        imageColorA = null;

        functions = new Function[] { new FunctionTest(random, index) };
        functionColor = new Color[] { Color.WHITE };
        probabilitiesToBeChosen = new double[] { 1.0 };
        
        index++;
    }

    @Override
    protected double[] fFinal(double x, double y) {
        return new double[]{x * 0.5, y * 0.5};
    }
}
