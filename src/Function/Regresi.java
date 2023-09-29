package Function;
import ADTMatrix.*;

public class Regresi {
    public static void regresiLinear (Matrix m){
        int i, j;
        double temp = 0;
        double result = 0;
        double sum = 0;
        Matrix mTemp;
        double [] x;

        x = new double [m.col - 1];
        System.out.println("Masukkan nilai x yang ingin ditaksir: ");
        for(i = 0; i < x.length; i++){
            x[i] = InputMatrix.input.nextDouble();
        }

        mTemp = new Matrix(m.col, m.col + 1);

        for(i = 0 ; i < mTemp.row ; i++){
            for(j = 0 ; j < mTemp.col ; j++){
                if(i == 0 && j == 0){
                    temp = m.row;
                    
                }
                else if (i == 0 && j > 0){
                    temp = Matrix.sumCol(m, j - 1);
                }
                else if (j == 0 && i > 0){
                    temp = Matrix.sumCol(m, i - 1);
                }
                else if (i > 0 && j > 0) {
                    temp = Matrix.sumMultiplyCol(m, i-1, j-1);
                }
                mTemp.setElmt(i, j, temp);
            }
        }
        
        mTemp = Matrix.gaussElimination(mTemp);
        double[] m1 = new double [mTemp.getRowLength()];
        Matrix.backSubstitution(mTemp, m1); 

        System.out.print("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            result = m1[i];
            sum += result;
            if (i == 0){
                if (m1[i] > 0){
                    System.out.printf("+ %.4f ", m1[i]);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4f ", m1[i]);
                }
            } else if ( i > 0 && i < mTemp.row - 1){
                if (m1[i] > 0){
                    System.out.printf("+ %.4fx%d ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx%d ", m1[i], i);
                }
            } else if (i == mTemp.row - 1){
                if (m1[i] > 0){
                    System.out.printf("+ %.4fx%d, ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx%d, ", m1[i], i);
                }
            }
        }
        System.out.printf("f(xk) = %.4f", sum);
    }
 }

