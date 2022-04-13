package lin.alg.lib.structures;

public class ColumnVector extends Matrix {

    public ColumnVector(int len) {
        super(len, 1);
    }

    public ColumnVector(double[] vector) {
        this(vector.length);
        for (int i = 0; i < vector.length; i++) {
            set(i, vector[i]);
        }
    }

    public ColumnVector(int[] vector) {
        this(vector.length);
        for (int i = 0; i < vector.length; i++) {
            set(i, vector[i]);
        }
    }

    public void set(int x, double val) {
        set(x, 0, val);
    }

    public double get(int x) {
        return get(x, 0);
    }
}
