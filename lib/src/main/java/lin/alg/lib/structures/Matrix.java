package lin.alg.lib.structures;

import java.io.Serializable;

public class Matrix implements Serializable {
    private double[][] matrix;

    public Matrix(int x, int y) {
        matrix = new double[x][y];
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(int[][] matrix) {
        this.matrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public void set(int x, int y, double val) {
        matrix[x][y] = val;
    }

    public double get(int x, int y) {
        return matrix[x][y];
    }

    public int getRows() {
        return matrix.length;
    }

    public int getColumns() {
        return matrix[0].length;
    }

    public boolean isSquare() {
        return getRows() == getColumns();
    }

    public void print() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%10f  ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
    }

    public static Matrix add(Matrix m1, Matrix m2) throws MatrixDimensionException {
        if (!(m1.getRows() == m2.getRows() && m1.getColumns() == m2.getColumns())) throw new MatrixDimensionException();
        else {
            Matrix returnM = new Matrix(m1.getRows(), m2.getColumns());
            for (int i = 0; i < m1.getRows(); i++) {
                for (int j = 0; j < m2.getRows(); j++) {
                    returnM.set(i, j, m1.get(i, j) + m2.get(i, j));
                }
            }
            return returnM;
        }
    }

    public static Matrix multiply(double scalar, Matrix matrix) {
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                matrix.set(i, j, matrix.get(i, j) * scalar);
            }
        }
        return matrix;
    }

    public static Matrix multiply(Matrix m1, Matrix m2) throws MatrixDimensionException {
        if (m1.getColumns() != m2.getRows()) throw new MatrixDimensionException();
        else {
            Matrix returnM = new Matrix(m1.getRows(), m2.getColumns());
            double val1, val2, sum;
            for (int i = 0; i < m1.getRows(); i++) {
                for (int j = 0; j < m2.getColumns(); j++) {
                    sum = 0;
                    for (int k = 0; k < m1.getColumns(); k++) {
                        val1 = m1.get(i, k); val2 = m2.get(k, j);
                        sum += val1*val2;
                    }
                    returnM.set(i, j, sum);
                }
            }
            return returnM;
        }
    }

    public double getDeterminant() throws MatrixDimensionException {
        if (getColumns() == 2 && getRows() == 2) return get(0, 0) * get(1, 1) - get(0, 1) * get(1, 0);

        if (!isSquare()) throw new MatrixDimensionException();

        Matrix subMatrix = new Matrix(getRows() - 1, getColumns() - 1);
        int index;
        double sum = 0;
        for (int a = 0; a < getColumns(); a++) {
            for (int i = 1; i < getRows(); i++) {
                index = 0;
                for (int j = 0; j < getColumns(); j++) {
                    if (a == j) continue;
                    subMatrix.set(i - 1, index, get(i, j));
                    index++;
                }
            }
            sum += Math.pow(-1, a) * get(0, a) * subMatrix.getDeterminant();
        }
        return sum;
    }

    public Matrix getInverse() throws NoInverseException, MatrixDimensionException {
        if (!isSquare()) throw new NoInverseException();

        //Matrix of Minors
        Matrix matrixOfMinors = new Matrix(getRows(), getColumns());
        Matrix subMatrix = new Matrix(getRows() - 1, getColumns() - 1);
        int indexR, indexC;

        for (int a = 0; a < getRows(); a++) {
            for (int b = 0; b < getColumns(); b++) {

                indexR = 0;
                for (int i = 0; i < getRows(); i++) {
                    if (i == a) continue;
                    indexC = 0;
                    for (int j = 0; j < getColumns(); j++) {
                        if (j == b) continue;
                        subMatrix.set(indexR, indexC, get(i, j));
                        indexC++;
                    }
                    indexR++;
                }

                matrixOfMinors.set(a, b, subMatrix.getDeterminant());
            }
        }

        //Matrix of Cofactors
        Matrix matrixOfCofactors = new Matrix(getRows(), getColumns());
        int sign = 1;
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                matrixOfCofactors.set(i, j, sign * matrixOfMinors.get(i, j));
                sign *= -1;
            }
        }

        //Adjugate
        Matrix adjugated = new Matrix(getRows(), getColumns());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                adjugated.set(i, j, matrixOfCofactors.get(j, i));
            }
        }

        //Determinant
        if (getDeterminant() == 0) throw new NoInverseException();
        return Matrix.multiply(1./getDeterminant(), adjugated);
    }
}
