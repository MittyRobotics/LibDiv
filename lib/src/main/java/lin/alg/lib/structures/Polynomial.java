package lin.alg.lib.structures;

import lin.alg.lib.LinAlgLib;

public class Polynomial extends ColumnVector {
    public Polynomial() {
        super(new double[]{});
    }

    public Polynomial(int len) {
        super(len);
    }

    public Polynomial(double[] function) {
        super(function);
    }

    public Polynomial(int[] function) {
        super(function);
    }

    public double compute(double x) {
        double total = 0;
        for (int i = 0; i < getRows(); i++) {
            total += get(i) * Math.pow(x, i);
        }
        return total;
    }

    // maxRepetitions - how many times you want to run a Newton's method iteration (even 1 will usually suffice)
    public double solve(double guess, int maxRepetitions) {
        double x = guess;
        for (int i = 0; i < maxRepetitions; i++) {
            x = solve(0, x);
        }
        return x;
    }

    private double solve(int count, double x) {
        if (count > 7000) {
            return x;
        }

        Polynomial derivative = LinAlgLib.takeDerivative(this);
        double x1 = x - compute(x)/derivative.compute(x);
        return solve(count + 1, x1);
    }

    @Override
    public void print() {
        System.out.println("--------------------");
        double[] temp = new double[getRows()];
        for (int i = 0; i < getRows(); i++) {
            if (i == getRows() - 1) System.out.print("" + get(getRows() - i - 1) + " * x^" + (getRows() - i - 1) + "\n");
            else System.out.print("" + get(getRows() - i - 1) + " * x^" + (getRows() - i - 1) + "   +   ");
        }
        System.out.println("--------------------");
    }
}
