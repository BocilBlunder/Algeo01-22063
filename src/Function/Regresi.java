package Function;
import ADTMatrix.*;

public class Regresi {
    public static void regresiLinear (Matrix m){
        Matrix mTemp;
        int i, j;
        double temp = 0;
        Matrix MRes = new Matrix(m.col, m.col + 1);
        for(i = 0 ; i < MRes.row ; i++){
            for(j = 0 ; j < MRes.col ; j++){
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
                MRes.setElmt(i, j, temp);
            }
        }
        MRes = Matrix.gaussElimination(MRes);
        double[] m1 = new double [MRes.getRowLength()];
        Matrix.backSubstitution(MRes, m1); 
    }
 }

