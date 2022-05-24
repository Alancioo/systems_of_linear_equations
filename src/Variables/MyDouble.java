package Variables;

import java.math.BigDecimal;

public class MyDouble implements Operations<MyDouble> {
    private Double num;

    public MyDouble(Double num) {
        this.num = num;
    }

    @Override
    public void add(MyDouble a) {
        this.num = this.num + a.num;
    }

    @Override
    public void subtract(MyDouble a) {
        this.num = this.num - a.num;

    }

    @Override
    public void multiply(MyDouble a) {
        this.num = this.num * a.num;

    }

    @Override
    public void divide(MyDouble a) {
        this.num = this.num / a.num;

    }

    @Override
    public MyDouble initialize(MyDouble a) {
        return new MyDouble(a.num);
    }

    @Override
    public MyDouble absolute() {
        return new MyDouble(Math.abs(this.num));
    }

    @Override
    public MyDouble initializeWithZero() {
        return new MyDouble(0D);
    }

    @Override
    public Integer compare(MyDouble a) {
        return this.num.compareTo(a.num);
    }

    @Override
    public BigDecimal returnValue() {
        return BigDecimal.valueOf(this.num);
    }

    public Double getValue() {
        return this.num;
    }
}
