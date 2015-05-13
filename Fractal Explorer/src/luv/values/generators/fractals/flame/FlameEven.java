package luv.values.generators.fractals.flame;

public class FlameEven extends Flame {

    int steps = 1;

    @Override
    protected void fillPoints(int w, int h) {
        for (int xw = 0; xw < w; xw += steps) {
            for (int yh = 0; yh < h; yh += steps) {

                double x = (xw / (double) w) * 2 - 1;
                double y = (yh / (double) h) * 2 - 1;

                iterateFrom(x, y, w, h);
            }
        }
    }
}
