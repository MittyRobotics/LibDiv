package lin.alg.lib.structures;

public class RowVector extends Matrix {

    public RowVector(int len) {
        super(1, len);
    }

    public RowVector(double[] vector) {
        super(new double[][]{
                vector
        });
    }

    public RowVector(int[] vector) {
        super(new int[][]{
                vector
        });
    }

    public void set(int x, double val) {
        set(0, x, val);
    }

    public double get(int x) {
        return get(0, x);
    }
}
