import ADTMatrix.*;
import java.util.*;
import java.io.*;

public class Determinan {
    public static double detKofaktor(Matrix m){
        int i, j, k;
        double det = 0;
        double x = 0;
        Matrix mTemp;
        int row = 0;
        int col = 0;

        row = m.getRowLength();
        col = m.getColLength();

        if (m.isSquare()){
            if(row == 1 || col == 1){
                return m.getElmt(0, 0);
            } else{
                for (i = 0; i < row; i++){
                    mTemp = new Matrix(row, col);
                    for (j = 0; j < row; j++){
                        for (k = 0; k < m.col; k++){
                            x = m.getElmt(j, k);
                            if (k > i){
                                mTemp.setElmt(j - 1, k - 1, x);
                            }
                            else if (k < i) {
                                mTemp.setElmt(j - 1, k, x);
                            }
                        }
                    }
                    det += Math.pow(-1,i) * m.getElmt(0, i) * detKofaktor(mTemp);
                }
                return det;
            }
        } else {
            return m.MARK;
        }
    }

    public double detOBE (Matrix m){
        int i, j;
        double det = 0;
        int row = 0;
        int col = 0;
        int count = 1;

        row = m.getRowLength();
        col = m.getColLength();

        if (m.isSquare()){
            if (row == 1 || col == 1){
                return m.getElmt(0, 0);
            } else {
                for (i = 0; i < row; i++){
                    if (m.matrix[i][i]== 0){
                        j = 0;
                        while (j < row && m.matrix[i][i] == 0){
                            j++;
                        }
                        if (j < row){
                            m.rowSwap(m, i, j);
                            count *= -1;
                        } else {
                            return 0;
                        }
                    }
                }
            }

        } else {
            return m.MARK;
        }
    }
}

