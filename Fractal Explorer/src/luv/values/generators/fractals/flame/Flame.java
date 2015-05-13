package luv.values.generators.fractals.flame;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;
import luv.graphics.colors.Colors;

public class Flame {

    Random random;

    Function[] functions;
    Color[] functionColor;
    double[] probabilitiesToBeChosen;

    float[] imageColorR;
    float[] imageColorG;
    float[] imageColorB;
    double[] imageColorA;

    int iterations = 40;
    int alternatives = 10000;

    int bucketsPerPixel = 3; // super sampling
    int numberOfFunctions = 3; // max 9 at (add more colors if you want more functions ;) )

    public Flame() {
        this.random = new Random();
        randomFlame();
    }

    public void randomFlame() {
        imageColorA = null;

        functions = new Function[numberOfFunctions];

        probabilitiesToBeChosen = new double[functions.length];
        double weightsum = 0.0;
        for (int i = 0; i < functions.length; i++) {
            functions[i] = new Function(random);
            probabilitiesToBeChosen[i] = random.nextDouble();
            weightsum += probabilitiesToBeChosen[i];
        }
        for (int i = 0; i < probabilitiesToBeChosen.length; i++) {
            probabilitiesToBeChosen[i] /= weightsum;
        }

        functionColor = new Color[]{Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK, Color.CYAN};
    }

    public BufferedImage getImage(int w, int h) {
        int size = w * h * bucketsPerPixel * bucketsPerPixel;
        if (imageColorA == null || imageColorA.length != size) {
            imageColorR = new float[size];
            imageColorG = new float[size];
            imageColorB = new float[size];
            imageColorA = new double[size];
        }

        fillPoints(w, h);

        return createImage(w, h);
    }

    protected void fillPoints(int w, int h) {
        for (int alternative = 0; alternative < alternatives; alternative++) {

            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;

            iterateFrom(x, y, w, h);
        }
    }

    protected void iterateFrom(double x, double y, int w, int h) {
        for (int iteration = 0; iteration < iterations; iteration++) {
            int i = pickFunction();

            double[] xy = functions[i].getXY(x, y);
            x = xy[0];
            y = xy[1];

            //if (iteration > 20) {
            double[] xfyf = fFinal(x, y);
            plot(i, w, h, xfyf[0], xfyf[1]);
            //}
        }
    }

    protected BufferedImage getBlackImage(int w, int h) {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int data[] = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        for (int i = 0; i < data.length; i++) {
            data[i] = Color.BLACK.hashCode();
        }
        return image;
    }

    protected void plot(int functionIndex, int w, int h, double x, double y) {
        w *= bucketsPerPixel;
        h *= bucketsPerPixel;

        int w2 = w >> 1;
        int h2 = h >> 1;

        int xplot = (int) (w2 + x * w2);
        int yplot = (int) (h2 + y * h2);

        if (xplot >= 0 && xplot < w && yplot >= 0 && yplot < h) {
            int i = xplot + yplot * w;
            imageColorR[i] = (imageColorR[i] + functionColor[functionIndex].getRed() / 255.0f) / 2.0f;
            imageColorG[i] = (imageColorG[i] + functionColor[functionIndex].getGreen() / 255.0f) / 2.0f;
            imageColorB[i] = (imageColorB[i] + functionColor[functionIndex].getBlue() / 255.0f) / 2.0f;
            imageColorA[i] += 1.0;
        }
    }

    protected int pickFunction() {
        double weight = random.nextDouble();
        for (int i = 0; i < probabilitiesToBeChosen.length; i++) {
            weight -= probabilitiesToBeChosen[i];
            if (weight <= 0) {
                return i;
            }
        }
        throw new RuntimeException("Weights do not sum up to 1.0!");
    }

    protected double[] fFinal(double x, double y) {
        return new double[]{x * 0.9, y * 0.9};
    }

    protected BufferedImage createImage(int w, int h) {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int data[] = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        double max = Double.MIN_VALUE;
        for (int i = 0; i < imageColorA.length; i++) {
            if (imageColorA[i] > max) {
                max = imageColorA[i];
            }
        }

        double logMax = Math.log(max);

        double[] scales = new double[imageColorA.length];
        for (int i = 0; i < imageColorA.length; i++) {
            double value = imageColorA[i];
            scales[i] = value != 0.0 ? Math.log(value) / logMax : 0.0;
        }

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                data[x + y * w] = getAt(x, y, w, h, scales);
            }
        }

        return image;
    }

    protected int getAt(int x, int y, int w, int h, double[] scales) {
        int margin = bucketsPerPixel >> 1;

        x *= bucketsPerPixel;
        y *= bucketsPerPixel;
        w *= bucketsPerPixel;
        h *= bucketsPerPixel;

        double sumR = 0.0;
        double sumG = 0.0;
        double sumB = 0.0;

        for (int xf = x - margin; xf <= x + margin; xf++) {
            for (int yf = y - margin; yf <= y + margin; yf++) {
                int ix = xf < 0 ? 0 : xf >= w ? w - 1 : xf;
                int iy = yf < 0 ? 0 : yf >= h ? h - 1 : yf;
                int i = ix + iy * w;
                sumR += imageColorR[i] * scales[i];
                sumG += imageColorG[i] * scales[i];
                sumB += imageColorB[i] * scales[i];
            }
        }

        int bucketsSquared = bucketsPerPixel * bucketsPerPixel;
        return Colors.gamma(Colors.newColor(sumR / bucketsSquared, sumG / bucketsSquared, sumB / bucketsSquared)).hashCode();
    }
}
