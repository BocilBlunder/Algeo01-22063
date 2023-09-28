package Function;
import ADTMatrix.*;

public class Interpolasi {
    // Gauss-Elimination
    public static void interpolasiPolinomial1(Matrix m){
        int i, j;
        double result = 0;
        double x, temp;
        Matrix mTemp;

        System.out.print("Masukkan nilai yang ingin ditaksir (x): ");
        x = InputMatrix.input.nextDouble();

        mTemp = new Matrix(m.row, m.row + 1);

        if ( m.row >= 1){
            for (i = 0; i < mTemp.row; i++){
                for (j = 2; j < mTemp.col; j++){
                    mTemp.setElmt(i, 0, 1);
                    mTemp.setElmt(i, 1, m.getElmt(i, 0));
                    if (j < mTemp.col - 1){
                        mTemp.setElmt(i, j, Math.pow(m.getElmt(i, 0), j));
                    } else {
                        mTemp.setElmt(i, mTemp.col - 1, m.getElmt(i, 1));
                    }
                }
            }
        }

        mTemp = Matrix.gaussElimination(mTemp);
        double[] m1 = new double [mTemp.getRowLength()];
        Matrix.backSubstitution(mTemp, m1);

        System.out.print("f(x) = ");
        for (i = m.row - 1; i >= 0; i--) {
            temp = (m1[i] * Math.pow(x, i));
            result += temp;
            if (i > 1){
                if (m1[i] > 0){
                    System.out.printf("+ %.4fx^%d ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx^%d ", m1[i], i);
                }
            } else if ( i == 1){
                if (m1[i] > 0){
                    System.out.printf("+ %.4fx ", m1[i]);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx ", m1[i]);
                }
            } else{
                if (m1[i] > 0){
                    System.out.printf("+ %.4f, ", m1[i]);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4f, ", m1[i]);
                }
            }
        }
        System.out.printf("f(%.4f) = %.4f", x, result);
    }

    // Gauss-Jordan-Elimination
    public static void interpolasiPolinomial2(Matrix m){
        int i, j;
        double result = 0;
        double x, temp;
        Matrix mTemp;

        System.out.print("Masukkan nilai yang ingin ditaksir (x): ");
        x = InputMatrix.input.nextDouble();

        mTemp = new Matrix(m.row, m.row + 1);

        if ( m.row >= 1){
            for (i = 0; i < mTemp.row; i++){
                for (j = 2; j < mTemp.col; j++){
                    mTemp.setElmt(i, 0, 1);
                    mTemp.setElmt(i, 1, m.getElmt(i, 0));
                    if (j < mTemp.col - 1){
                        mTemp.setElmt(i, j, Math.pow(m.getElmt(i, 0), j));
                    } else {
                        mTemp.setElmt(i, mTemp.col - 1, m.getElmt(i, 1));
                    }
                }
            }
        }

        Matrix.gaussJordanElimination(mTemp);
        System.out.print("f(x) = ");
        for (i = m.row - 1; i >= 0; i--) {
            temp = (mTemp.matrix[i][mTemp.col - 1] * Math.pow(x, i));
            result += temp;
            if (i > 1){
                if (mTemp.matrix[i][mTemp.col - 1] > 0){
                    System.out.printf("+ %.4fx^%d ", mTemp.matrix[i][mTemp.col - 1], i);
                } else {
                    mTemp.matrix[i][mTemp.col - 1] *= -1;
                    System.out.printf("- %.4fx^%d ", mTemp.matrix[i][mTemp.col - 1], i);
                }
            } else if ( i == 1){
                if (mTemp.matrix[i][mTemp.col - 1] > 0){
                    System.out.printf("+ %.4fx ", mTemp.matrix[i][mTemp.col - 1]);
                } else {
                    mTemp.matrix[i][mTemp.col - 1] *= -1;
                    System.out.printf("- %.4fx ", mTemp.matrix[i][mTemp.col - 1]);
                }
            } else{
                if (mTemp.matrix[i][mTemp.col - 1] > 0){
                    System.out.printf("+ %.4f, ", mTemp.matrix[i][mTemp.col - 1]);
                } else {
                    mTemp.matrix[i][mTemp.col - 1] *= -1;
                    System.out.printf("- %.4f, ", mTemp.matrix[i][mTemp.col - 1]);
                }
            }
        }
        System.out.printf("f(%.4f) = %.4f", x, result);
    }
}
