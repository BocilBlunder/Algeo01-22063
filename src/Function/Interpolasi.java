package Function;
import ADTMatrix.*;

public class Interpolasi {
    // Gauss-Elimination
    public static void interpolasiPolinomialKeyboard(Matrix m){
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
            if (i == m.row - 1){
                if (m1[i] > 0){
                    System.out.printf("%.4fx^%d ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx^%d ", m1[i], i);
                }
            }else if (i > 1 && i < m.row - 1){
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

    public static void interpolasiPolinomialFile(Matrix m){
        int i, j;
        double result = 0;
        double temp;
        Matrix mTemp;
        Matrix m1;
        double[] m2;
        double[] matrix;

        m1 = new Matrix(m.row - 1, 2);
        for(i = 0; i < m.row - 1; i++){
            for(j = 0; j < 2; j++){
                m1.setElmt(i, j, m.matrix[i][j]);
            }
        }
        OutputMatrix.printMatrix(m1);

        m2 = new double[1];
        m2[0] = m.getElmt(m.row - 1, 0);


        mTemp = new Matrix(m1.row, m1.row + 1);

        if ( m1.row >= 1){
            for (i = 0; i < mTemp.row; i++){
                for (j = 2; j < mTemp.col; j++){
                    mTemp.setElmt(i, 0, 1);
                    mTemp.setElmt(i, 1, m1.getElmt(i, 0));
                    if (j < mTemp.col - 1){
                        mTemp.setElmt(i, j, Math.pow(m1.getElmt(i, 0), j));
                    } else {
                        mTemp.setElmt(i, mTemp.col - 1, m1.getElmt(i, 1));
                    }
                }
            }
        }

        mTemp = Matrix.gaussElimination(mTemp);
        matrix = new double [mTemp.getRowLength()];
        Matrix.backSubstitution(mTemp, matrix);

        System.out.print("f(x) = ");
        for (i = m1.row - 1; i >= 0; i--) {
            temp = (matrix[i] * Math.pow(m2[0], i));
            result += temp;
            if (i == m1.row - 1){
                if (matrix[i] > 0){
                    System.out.printf("%.4fx^%d ", matrix[i], i);
                } else {
                    matrix[i] *= -1;
                    System.out.printf("- %.4fx^%d ", matrix[i], i);
                }
            } else if (i > 1 && i < m1.row - 1){
                if (matrix[i] > 0){
                    System.out.printf("+ %.4fx^%d ", matrix[i], i);
                } else {
                    matrix[i] *= -1;
                    System.out.printf("- %.4fx^%d ", matrix[i], i);
                }
            } else if ( i == 1){
                if (matrix[i] > 0){
                    System.out.printf("+ %.4fx ", matrix[i]);
                } else {
                    matrix[i] *= -1;
                    System.out.printf("- %.4fx ", matrix[i]);
                }
            } else{
                if (matrix[i] > 0){
                    System.out.printf("+ %.4f, ", matrix[i]);
                } else {
                    matrix[i] *= -1;
                    System.out.printf("- %.4f, ", matrix[i]);
                }
            }
        }
        System.out.printf("f(%.4f) = %.4f", m2[0], result);
    }
}