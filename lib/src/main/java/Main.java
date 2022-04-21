import lin.alg.lib.LinAlgLib;
import lin.alg.lib.structures.Function;
import lin.alg.lib.structures.Matrix;
import lin.alg.lib.structures.MatrixDimensionException;
import lin.alg.lib.structures.Polynomial;

public class Main {
    public static void main(String[] args) throws MatrixDimensionException {
        Matrix matrix = new Matrix(new int[][]{
                {1, 2},
                {3, 4}
        });
        Matrix.exponential(matrix, 2).print();
    }
}
