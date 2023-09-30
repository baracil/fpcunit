package net.femtoparsec.units.core;

/**
 * @author Bastien Aracil
 */
public class Ratio implements Comparable<Ratio> {

    public static final Ratio ZERO = new Ratio(0, 1);
    public static final Ratio ONE = new Ratio(1, 1);


    public static Ratio multiple(Ratio lhs, Ratio rhs) {
        return new Ratio(lhs.numerator*rhs.numerator, lhs.denominator*rhs.denominator);
    }

    public static Ratio divide(Ratio lhs, Ratio rhs) {
        return new Ratio(lhs.numerator*rhs.denominator, lhs.denominator*rhs.numerator);
    }

    public static Ratio add(Ratio lhs, Ratio rhs) {
        return new Ratio(lhs.numerator*rhs.denominator+lhs.denominator*rhs.numerator, lhs.denominator*rhs.denominator);
    }

    private int numerator;

    private int denominator;

    public Ratio(int numerator) {
        this(numerator, 1);
    }

    public Ratio(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Invalid denominator : "+denominator);
        }
        final int gcd = gcd(numerator, denominator);
        this.denominator = denominator/gcd;
        this.numerator = numerator/gcd;
    }

    public Ratio multiply(Ratio other) {
        return multiple(this, other);
    }

    public Ratio inverse() {
        return new Ratio(denominator, numerator);
    }

    public double multiply(double value) {
        return (value*numerator)/denominator;
    }

    public double divide(double value) {
        return (value*denominator)/numerator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Ratio))
            return false;

        Ratio ratio = (Ratio) o;

        return denominator == ratio.denominator && numerator == ratio.numerator;
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    @Override
    public int compareTo(Ratio o) {
        return this.numerator*o.denominator - this.denominator*o.numerator;
    }

    public static int gcd(int a, int b) {
        int aux;
        while (b != 0) {
            aux = b;
            b = a%b;
            a = aux;
        }
        return a;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }
}
