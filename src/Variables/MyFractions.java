package Variables;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class MyFractions implements Operations<MyFractions> {
    private BigInteger numerator;
    private BigInteger denumerator;

    public MyFractions(BigInteger numerator, BigInteger denumerator) {
        this.numerator = numerator;
        this.denumerator = denumerator;
        simplifyFraction();
    }

    public MyFractions(long numerator) {
        this.numerator = BigInteger.valueOf(numerator);
        this.denumerator = BigInteger.ONE;
        simplifyFraction();
    }

    public MyFractions(MyFractions frac) {
        this.numerator = frac.numerator;
        this.denumerator = frac.denumerator;
        simplifyFraction();
    }

    @Override
    public void add(MyFractions frac) {
        this.numerator = this.numerator.multiply(frac.denumerator).add(frac.numerator.multiply(this.denumerator));
        this.denumerator = this.denumerator.multiply(frac.denumerator);
        simplifyFraction();
    }

    @Override
    public void subtract(MyFractions frac) {
        this.numerator = this.numerator.multiply(frac.denumerator).subtract(frac.numerator.multiply(this.denumerator));
        this.denumerator = this.denumerator.multiply(frac.denumerator);
        simplifyFraction();

    }

    @Override
    public void multiply(MyFractions frac) {
        this.numerator = frac.numerator.multiply(this.numerator);
        this.denumerator = frac.denumerator.multiply(this.denumerator);
        simplifyFraction();
    }

    @Override
    public void divide(MyFractions frac) {
        this.numerator = this.numerator.multiply(frac.denumerator);
        this.denumerator = this.denumerator.multiply(frac.numerator);
        simplifyFraction();
    }

    @Override
    public MyFractions initialize(MyFractions a) {
        return new MyFractions(a);
    }

    @Override
    public MyFractions absolute() {
        MyFractions newFrac = new MyFractions(this);
        newFrac.numerator = newFrac.numerator.abs();
        newFrac.denumerator = newFrac.denumerator.abs();
        return newFrac;
    }

    @Override
    public MyFractions initializeWithZero() {
        return new MyFractions(0L);
    }

    @Override
    public Integer compare(MyFractions a) {
        BigDecimal firstNumber = new BigDecimal(this.numerator).divide(new BigDecimal(this.denumerator), 10, RoundingMode.HALF_UP);
        BigDecimal secondNumber = new BigDecimal(a.numerator).divide(new BigDecimal(a.denumerator), 10, RoundingMode.HALF_UP);
        return firstNumber.compareTo(secondNumber);
    }

    @Override
    public BigDecimal returnValue() {
        simplifyFraction();
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denumerator), 16, RoundingMode.HALF_UP);
    }

    public void simplifyFraction() {
        BigInteger nwd = this.numerator.gcd(this.denumerator);
        this.numerator = this.numerator.divide(nwd);
        this.denumerator = this.denumerator.divide(nwd);
    }
}
