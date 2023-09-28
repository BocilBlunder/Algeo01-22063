package Function;

import ADTMatrix.Matrix;

public class Determinan {
    public static double detKofaktor(Matrix m){
        int i, j, k;
        Matrix mTemp;
        double det = 0;
        double x = 0;
        int row = 0;
        int col = 0;

        row = m.getRowLength();
        col = m.getColLength();

        if (m.isSquare()){
            if(row == 1 || col == 1){
                return m.matrix[0][0];
            } else{
                for (i = 0; i < row; i++){
                    mTemp = new Matrix(row - 1, col - 1);
                    for (j = 1; j < row; j++){
                        for (k = 0; k < row; k++){
                            x = m.matrix[j][k];
                            if (k > i){
                                mTemp.setElmt(j - 1, k - 1, x);
                            }
                            else if (k < i) {
                                mTemp.setElmt(j - 1, k, x);
                            }
                        }
                    }
                    det += Math.pow(-1,i) * m.matrix[0][i] * detKofaktor(mTemp);
                }
                return det;
            }
        } else {
            return m.MARK;
        }
    }

    public static double detOBE (Matrix m){
        int i, j, k, l;
        double det = 1;
        double x = 0;
        double y = 0;
        int row = 0;
        int col = 0;

        row = m.getRowLength();
        col = m.getColLength();

        if (m.isSquare()){
            if (row == 1 || col == 1){
                return m.matrix[0][0];
            } else {
                for (i = 0; i < row; i++){
                    if (m.matrix[i][i] == 0){
                        j = i + 1;

                        while (j < row && m.matrix[j][i] == 0){
                            j++;
                        }
                    
                        if (j >= row){
                            det = 0;
                        } else {
                            for (k = 0; k < row; k++){
                                m.rowSwap(m, i, j);
                            }
                            det *= -1;
                        }
                    }
                    
                    x = m.matrix[i][i];
                    det *= x;
                    if (x != 0){
                        for (k = 0; k < row; k++){
                            m.matrix[i][k] /= x;
                        }
                        
                        for (k = i + 1; k < row; k++){
                            y = m.matrix[k][i];
                            for (l = 0; l < row; l++){
                                m.setElmt(k, l, m.matrix[k][l] - y * m.matrix[i][l]);
                            }
                        }    
                    }
                }
                if (det == -0 || det == 0){
                    det = 0;
                }
                return det;
            }
        
        } else {
            return m.MARK;
        }
    }
}




