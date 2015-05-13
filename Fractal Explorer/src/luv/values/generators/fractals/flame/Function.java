package luv.values.generators.fractals.flame;

import java.util.Random;

public class Function {    
    
    Random random;

    Variation[] variations;
    double[] weights;

    double a, b, c, d, e, f;
    double aPost, bPost, cPost, dPost, ePost, fPost;
    
    VariationParameters variationParameters;
    
    public Function(Random random) {
        this.random = random;

        this.a = getAffinetransformCoefficient(random);
        this.b = getAffinetransformCoefficient(random);
        this.c = getAffinetransformCoefficient(random);
        this.d = getAffinetransformCoefficient(random);
        this.e = getAffinetransformCoefficient(random);
        this.f = getAffinetransformCoefficient(random);
        
        this.aPost = getAffinetransformCoefficient(random);
        this.bPost = getAffinetransformCoefficient(random);
        this.cPost = getAffinetransformCoefficient(random);
        this.dPost = getAffinetransformCoefficient(random);
        this.ePost = getAffinetransformCoefficient(random);
        this.fPost = getAffinetransformCoefficient(random);
        
        variations = Variation.getVariations(random);
        weights = getWeights(random);
        
        variationParameters = new VariationParameters(a, b, c, d, e, f);
    }
    
     static protected double getAffinetransformCoefficient(Random random) {
        return random.nextDouble() * 2.0 - 1.0;
    }

    protected double[] getWeights(Random random) {
        double[] result = new double[variations.length];

        double weightsum = 0.0;
        for (int i = 0; i < variations.length; i++) {
            result[i] = random.nextDouble();
            weightsum += result[i];
        }

        for (int i = 0; i < result.length; i++) {
            result[i] /= weightsum;
        }
        return result;
    }

    public double[] getXY(double x, double y) {
        double xi = a*x + b*y + c;
        double yi = d*x + e*y + f;

        double rx = 0.0;
        double ry = 0.0;

        variationParameters.update(xi, yi);
        
        for (int i = 0; i < variations.length; i++) {
            double[] result = variations[i].getXY(variationParameters);
            rx += weights[i] * result[0];
            ry += weights[i] * result[1];
        }
        
        double xPost = aPost*rx + bPost*ry + cPost;
        double yPost = dPost*rx + ePost*ry + fPost;

        return new double[]{xPost, yPost};
    }
}
