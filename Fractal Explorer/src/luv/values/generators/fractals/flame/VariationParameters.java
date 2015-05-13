package luv.values.generators.fractals.flame;

public class VariationParameters {

    public double x;
    public double y;

    public double sinX;
    public double xTimesXMinusYTimesY;
    public double twoTimesXTimesY;
    
    public double r;
    public double rSquare;
    public double rReciprocal;
    public double sinR;
    public double cosR;
    public double twoDivRPlusOneTimesX;
    public double twoDivRPlusOneTimesY;

    public double twoPi;

    public double arctanXdivY;
    public double arctanXdivYdivPi;
    public double arctanXdivYdiv2;
    public double sinArctanXdivY;
    public double cosArctanXdivY;
    public double sinArctanXdivYPlusR;
    public double cosArctanXdivYMinusR;

    public double arctanYdivX;

    public final double a;
    public final double b;
    public final double c;
    public final double d;
    public final double e;
    public final double f;
    
    public final double cSquare;
    public final double cSquareTimesPi;
    public final double cSquareTimesPiDivTwo;

    public VariationParameters(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        
        cSquare = c * c + Double.MIN_VALUE;
        cSquareTimesPi = cSquare * Math.PI;
        cSquareTimesPiDivTwo = cSquareTimesPi / 2.0;
    }

    public void update(double x, double y) {
        this.x = x;
        this.y = y;

        sinX = Math.sin(x);
        xTimesXMinusYTimesY = x * x  - y * y;
        twoTimesXTimesY = 2 * x * y;
        
        rSquare = x * x + y * y + Double.MIN_VALUE;
        r = Math.sqrt(rSquare) + Double.MIN_VALUE;
        rReciprocal = 1.0 / r;
        sinR = Math.sin(r);
        cosR = Math.cos(r);
        double twoDivRPlusOne = 2.0 / (r + 1.0);
        twoDivRPlusOneTimesX = twoDivRPlusOne * x;
        twoDivRPlusOneTimesY = twoDivRPlusOne * y;        

        arctanXdivY = Math.atan2(x, y + Double.MIN_VALUE);
        arctanXdivYdivPi = arctanXdivY / Math.PI;
        arctanXdivYdiv2 = arctanXdivY / 2.0;
        sinArctanXdivY = Math.sin(arctanXdivY);
        cosArctanXdivY = Math.cos(arctanXdivY);
        cosArctanXdivYMinusR = Math.cos(arctanXdivY);
        sinArctanXdivYPlusR = Math.sin(arctanXdivY + r);
        cosArctanXdivYMinusR = Math.cos(arctanXdivY - r);

        arctanYdivX = Math.atan2(y, x + Double.MIN_VALUE);

        twoPi = 2 * Math.PI;
    }
}
