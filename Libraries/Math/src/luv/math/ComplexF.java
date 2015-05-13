package luv.math;

public class ComplexF {

    private final float re;   // the real part
    private final float im;   // the imaginary part

    // create a new object with the given real and imaginary parts
    public ComplexF(float real, float imag) {
        re = real;
        im = imag;
    }

    // return a string representation of the invoking Complex object
    @Override
    public String toString() {
        if (im == 0) {
            return re + "";
        }
        if (re == 0) {
            return im + "i";
        }
        if (im < 0) {
            return re + " - " + (-im) + "i";
        }
        return re + " + " + im + "i";
    }

    // return abs/modulus/magnitude and angle/phase/argument
    public float abs() {
        return (float) Math.hypot(re, im);
    }  // Math.sqrt(re*re + im*im)

    public float phase() {
        return (float) Math.atan2(im, re);
    }  // between -pi and pi

    // return a new Complex object whose value is (this + b)
    public ComplexF plus(ComplexF b) {
        ComplexF a = this;             // invoking object
        float real = a.re + b.re;
        float imag = a.im + b.im;
        return new ComplexF(real, imag);
    }

    // return a new Complex object whose value is (this - b)
    public ComplexF minus(ComplexF b) {
        ComplexF a = this;
        float real = a.re - b.re;
        float imag = a.im - b.im;
        return new ComplexF(real, imag);
    }

    // return a new Complex object whose value is (this * b)
    public ComplexF times(ComplexF b) {
        ComplexF a = this;
        float real = a.re * b.re - a.im * b.im;
        float imag = a.re * b.im + a.im * b.re;
        return new ComplexF(real, imag);
    }

    // scalar multiplication
    // return a new object whose value is (this * alpha)
    public ComplexF times(float alpha) {
        return new ComplexF(alpha * re, alpha * im);
    }

    // return a new Complex object whose value is the conjugate of this
    public ComplexF conjugate() {
        return new ComplexF(re, -im);
    }

    // return a new Complex object whose value is the reciprocal of this
    public ComplexF reciprocal() {
        float scale = re * re + im * im;
        return new ComplexF(re / scale, -im / scale);
    }

    // return the real or imaginary part
    public float re() {
        return re;
    }

    public float im() {
        return im;
    }

    // return a / b
    public ComplexF divides(ComplexF b) {
        ComplexF a = this;
        return a.times(b.reciprocal());
    }

    // return a new Complex object whose value is the complex exponential of this
    public ComplexF exp() {
        return new ComplexF((float) (Math.exp(re) * Math.cos(im)), (float) (Math.exp(re) * Math.sin(im)));
    }

    // return a new Complex object whose value is the complex sine of this
    public ComplexF sin() {
        return new ComplexF((float) (Math.sin(re) * Math.cosh(im)), (float) (Math.cos(re) * Math.sinh(im)));
    }

    // return a new Complex object whose value is the complex cosine of this
    public ComplexF cos() {
        return new ComplexF((float) (Math.cos(re) * Math.cosh(im)), (float) (-Math.sin(re) * Math.sinh(im)));
    }

    // return a new Complex object whose value is the complex tangent of this
    public ComplexF tan() {
        return sin().divides(cos());
    }

    // a static version of plus
    public static ComplexF plus(ComplexF a, ComplexF b) {
        float real = a.re + b.re;
        float imag = a.im + b.im;
        ComplexF sum = new ComplexF(real, imag);
        return sum;
    }
}
