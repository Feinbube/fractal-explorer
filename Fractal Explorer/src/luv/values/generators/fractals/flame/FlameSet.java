package luv.values.generators.fractals.flame;

import java.awt.Color;

public class FlameSet extends FlameEven {

    int index = 0;

    public FlameSet() {
        steps = 1;
        iterations = 10;

        randomFlame();
    }

    @Override
    public void randomFlame() {
        imageColorA = null;

        //functions = new Function[]{new FunctionTest(random, index)};
        functions = new Function[]{
            new FunctionTest(random, random.nextInt(48)),
            new FunctionTest(random, random.nextInt(48)),
            new FunctionTest(random, random.nextInt(48)),
            new FunctionTest(random, random.nextInt(48)),
            new FunctionTest(random, random.nextInt(48))
        };
        functionColor = new Color[]{Color.WHITE, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
        probabilitiesToBeChosen = new double[]{0.2, 0.2, 0.2, 0.2, 0.2};

        index++;
    }

    @Override
    protected void fillPoints(int w, int h) {
        double addx = random.nextDouble() * steps - steps / 2.0;
        double addy = random.nextDouble() * steps - steps / 2.0;
        for (int xw = 0; xw < w; xw += steps) {
            for (int yh = 0; yh < h; yh += steps) {

                double x = ((xw + addx) / (double) w) * 2 - 1;
                double y = ((yh + addy) / (double) h) * 2 - 1;

                iterateFrom(x, y, w, h);
            }
        }
    }

    @Override
    protected double[] fFinal(double x, double y) {
        return new double[]{x * 0.5, y * 0.5};
    }
}
