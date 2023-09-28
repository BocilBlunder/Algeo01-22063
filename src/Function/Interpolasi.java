package Function;
import ADTMatrix.*;

public class Interpolasi {
    public static void interpolasiPolinomial(Matrix m){
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