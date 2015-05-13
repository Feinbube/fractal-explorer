package luv.values.generators.fractals;

// http://www.cc.gatech.edu/~phlosoft/attractors/
import luv.values.Property;
import luv.values.generators.ValueGenerator;

//Attractors and Fractals
//Grant Schindler, 2008
// http://www.cc.gatech.edu/~phlosoft/attractors/
public class PojoAttractor extends ValueGenerator {

    int nrIterations = 1000000;
    float a = 1.78125f, b = -0.78125f, c = 1.90625f, d = 2.65625f;

    int channel;

    public PojoAttractor() {
        this(1);
    }

    public PojoAttractor(int channel) {
        this.channel = channel;
    }

    @Override
    public float[] generateValues(double x0, double y0, double x1, double y1, int w, int h) {
        float[] values = new float[w * h];
        float[] counters = new float[w * h];

        int xDiff = (int)((w / (x1 - x0)) * x0);
        int yDiff = (int)((h / (y1 - y0)) * y0);
        
        for (int yImage = 0; yImage < h; ++yImage) {
            for (int xImage = 0; xImage < w; ++xImage) {
                values[yImage * w + xImage] = 1.0f;
                counters[yImage * w + xImage] = 0.0f;
            }
        }

        //Variables and Coefficients (Initial Values) ------------------------------------
        float e = 0.7f, f = -1.1f; //Coefficients
        float x = 0.6f, y = 0.9f, z = 0.3f;//Variables
        float xn, yn, zn;                  //Temporary Copies of Variables, Coefficients

        int u, v;                          //Image Size, Pixel Coordinates
        float zoomX = (float) (w / (x1 - x0)); //Image Scaling Constant
        float zoomY = (float) (h / (y1 - y0));        

        float r = 0.0f;

        for (int i = 0; i < nrIterations; i++) {
            //Compute Next Point

            xn = (float) (Math.sin(a * y) - Math.cos(b * x)); //*************************************************
            yn = (float) (Math.sin(c * x) - Math.cos(d * y)); //** Update Temp Variables -- Magic Lies Here  ****
            zn = (float) (Math.sin(e * x) - Math.cos(f * z)); //*************************************************

            x = xn;
            y = yn;
            z = zn;       //Set Original to Temp

            u = (int) ((x + 2.5f) * zoomX) - xDiff;         //Convert to 2D Image Space for Plotting
            v = (int) ((y + 2.5f) * zoomY) - yDiff;

            if (channel == 1) {
                r = z * 0.9f + (1.0f - z) * 0.6f; //Map Z-Coordinate to Color
            } else if (channel == 2) {
                r = z * 0.2f + (1.0f - z) * 0.4f;

            } else if (channel == 3) {
                r = z * 0.5f + (1.0f - z) * 0.9f;
            } else {
                throw new RuntimeException("Channel nr. " + channel + " is not supported!");
            }
            if(u >= 0 && u < w && v >= 0 && v < h) {
                values[u + v * w] += r;
                counters[u + v * w] += 1.0f;
            }
        }

        float max = Float.MIN_VALUE;
        for (int yImage = 0; yImage < h; ++yImage) {
            for (int xImage = 0; xImage < w; ++xImage) {
                float value = counters[xImage + yImage * w];
                if (max < value) {
                    max = value;
                }
            }
        }

        //Adjust Values and Fill Image
        float logval, logmax = (float) Math.log(max);
        float M = logmax * logmax;  //Precomputation for ratio (log(val)/log(max))^2

        for (int yImage = 0; yImage < h; ++yImage) {
            for (int xImage = 0; xImage < w; ++xImage) {
                logval = (float) Math.log(values[xImage + yImage * w]);
                values[xImage + yImage * w] = (logval * logval / M);
            }
        }

        return values;
    }

    @Override
    protected Property[] getAdditionalProperties() {
        return new Property[]{
            new Property("a", a, -4.0f, 4.0f, 0.02f, 1.78125f),
            new Property("b", b, -4.0f, 4.0f, 0.02f, -0.78125f),
            new Property("c", c, -4.0f, 4.0f, 0.02f, 1.90625f),
            new Property("d", d, -4.0f, 4.0f, 0.02f, 2.65625f),
            new Property("nrIterations", nrIterations, 10000, Integer.MAX_VALUE, 100, 1000000)
        };
    }

    @Override
    protected void setAdditionalProperty(String name, float value) {
        if (name.equals("a")) {
            a = value;
        }
        if (name.equals("b")) {
            b = value;
        }
        if (name.equals("c")) {
            c = value;
        }
        if (name.equals("d")) {
            d = value;
        }
        if (name.equals("nrIterations")) {
            nrIterations = (int)value;
        }
    }
}
