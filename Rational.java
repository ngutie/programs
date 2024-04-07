package exercise13_15;
import java.math.BigInteger;

public class Rational extends Number implements Comparable<Rational> {
    // Data fields for numerator and denominator
    private BigInteger numerator = BigInteger.ZERO;
    private BigInteger denominator = BigInteger.ONE;

    public Rational() {
        this(0, 1);
    }
    public Rational(long numerator) {
        this(numerator, 1);
    }
    public Rational(long numerator, long denominator) {
        BigInteger gcd = gcd(numerator, denominator);
        this.numerator = BigInteger.valueOf(((denominator > 0) ? 1 : -1) * numerator).divide(gcd);
        this.denominator = BigInteger.valueOf(Math.abs(denominator)).divide(gcd);
    }
    private static BigInteger gcd(long n, long d) {
        BigInteger b1 = BigInteger.valueOf(n);
        BigInteger b2 = BigInteger.valueOf(d);
        return b1.gcd(b2);
    }
    public long getNumerator() {
        return numerator.longValue();
    }
    public long getDenominator() {
        return denominator.longValue();
    }
    public Rational add(Rational secondRational) {
        long n = numerator.longValue() * secondRational.getDenominator() +
                denominator.longValue() * secondRational.getNumerator();
        long d = denominator.longValue() * secondRational.getDenominator();
        return new Rational(n, d);
    }
    public Rational subtract(Rational secondRational) {
        long n = numerator.longValue() * secondRational.getDenominator()
                - denominator.longValue() * secondRational.getNumerator();
        long d = denominator.longValue() * secondRational.getDenominator();
        return new Rational(n, d);
    }
    public Rational multiply(Rational secondRational) {
        long n = numerator.longValue() * secondRational.getNumerator();
        long d = denominator.longValue() * secondRational.getDenominator();
        return new Rational(n, d);
    }
    public Rational divide(Rational secondRational) {
        long n = numerator.longValue() * secondRational.getDenominator();
        long d = denominator.longValue() * secondRational.numerator.longValue();
        return new Rational(n, d);
    }
    @Override // Implement the doubleValue method in Number
    public double doubleValue() {
        return numerator.longValue() * 1.0 / denominator.longValue();
    }

    @Override // Implement the abstract longValue method in Number
    public long longValue() {
        return (long) doubleValue();
    }

    @Override // Implement the compareTo method in Comparable
    public int compareTo(Rational o) {
        if (this.subtract(o).getNumerator() > 0)
            return 1;
        else if (this.subtract(o).getNumerator() < 0)
            return -1;
        else
            return 0;
    }
}
