package luv.math;

import java.math.BigDecimal;

public class BigDecimalComplex {

    private final BigDecimal re;   // the real part
    private final BigDecimal im;   // the imaginary part

    // create a new object with the given real and imaginary parts
    public BigDecimalComplex(BigDecimal real, BigDecimal imag) {
        re = real;
        im = imag;
    }

    // return a string representation of the invoking Complex object
    public String toString() {
        if (im.doubleValue() == 0) {
            return re + "";
        }
        if (re.doubleValue() == 0) {
            return im + "i";
        }
        if (im.doubleValue() < 0) {
            return re + " - " + im.negate() + "i";
        }
        return re + " + " + im + "i";
    }

    // return abs/modulus/magnitude and angle/phase/argument
    public double abs() {
        return Math.hypot(re.doubleValue(), im.doubleValue());
    }  // Math.sqrt(re*re + im*im)

    public double phase() {
        return Math.atan2(im.doubleValue(), re.doubleValue());
    }  // between -pi and pi

    // return a new Complex object whose value is (this + b)
    public BigDecimalComplex plus(BigDecimalComplex b) {
        BigDecimalComplex a = this;             // invoking object
        BigDecimal real = a.re.add(b.re);
        BigDecimal imag = a.im.add(b.im);
        return new BigDecimalComplex(real, imag);
    }

    // return a new Complex object whose value is (this - b)
    public BigDecimalComplex minus(BigDecimalComplex b) {
        BigDecimalComplex a = this;
        BigDecimal real = a.re.subtract(b.re);
        BigDecimal imag = a.im.subtract(b.im);
        return new BigDecimalComplex(real, imag);
    }

    // return a new Complex object whose value is (this * b)
    public BigDecimalComplex times(BigDecimalComplex b) {
        BigDecimalComplex a = this;
        BigDecimal real = a.re.multiply(b.re).subtract(a.im.multiply(b.im));
        BigDecimal imag = a.re.multiply(b.im).add(a.im.multiply(b.re));
        return new BigDecimalComplex(real, imag);
    }

    // scalar multiplication
    // return a new object whose value is (this * alpha)
    public BigDecimalComplex times(BigDecimal alpha) {
        return new BigDecimalComplex(alpha.multiply(re), alpha.multiply(im));
    }

    // return a new Complex object whose value is the conjugate of this
    public BigDecimalComplex conjugate() {
        return new BigDecimalComplex(re, im.negate());
    }

    // return a new Complex object whose value is the reciprocal of this
    public BigDecimalComplex reciprocal() {
        BigDecimal scale = re.multiply(re).add(im.multiply(im));
        return new BigDecimalComplex(re.divide(scale), im.negate().divide(scale));
    }

    // return the real or imaginary part
    public BigDecimal re() {
        return re;
    }

    public BigDecimal im() {
        return im;
    }

    // return a / b
    public BigDecimalComplex divides(BigDecimalComplex b) {
        BigDecimalComplex a = this;
        return a.times(b.reciprocal());
    }

    // return a new Complex object whose value is the complex exponential of this
    public BigDecimalComplex exp() {
        return new BigDecimalComplex(
                new BigDecimal(Math.exp(re.doubleValue()) * Math.cos(im.doubleValue())),
                new BigDecimal(Math.exp(re.doubleValue()) * Math.sin(im.doubleValue())));
    }

    // return a new Complex object whose value is the complex sine of this
    public BigDecimalComplex sin() {
        return new BigDecimalComplex(
                new BigDecimal(Math.sin(re.doubleValue()) * Math.cosh(im.doubleValue())),
                new BigDecimal(Math.cos(re.doubleValue()) * Math.sinh(im.doubleValue())));
    }

    // return a new Complex object whose value is the complex cosine of this
    public BigDecimalComplex cos() {
        return new BigDecimalComplex(
                new BigDecimal(Math.cos(re.doubleValue()) * Math.cosh(im.doubleValue())),
                new BigDecimal(-Math.sin(re.doubleValue()) * Math.sinh(im.doubleValue())));
    }

    // return a new Complex object whose value is the complex tangent of this
    public BigDecimalComplex tan() {
        return sin().divides(cos());
    }

    // a static version of plus
    public static BigDecimalComplex plus(BigDecimalComplex a, BigDecimalComplex b) {
        BigDecimal real = a.re.add(b.re);
        BigDecimal imag = a.im.add(b.im);
        BigDecimalComplex sum = new BigDecimalComplex(real, imag);
        return sum;
    }
}
