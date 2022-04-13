package lin.alg.lib.structures;

public class Integral extends Matrix {
    public Integral(int len) {
        super(len + 1, len);
        for (int i = 0; i < len + 1; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j + 1) {
                    set(i, j, 1. / ((double) i));
                }
            }
        }
    }
}
