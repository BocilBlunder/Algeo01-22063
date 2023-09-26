package Function;

import ADTMatrix.Matrix;

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
                    mTemp = new Matrix(row - 1, col - 1);
                    for (j = 1; j < row; j++){
                        for (k = 0; k < col; k++){
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

    // blm selesai
    public static double detOBE (Matrix m){
        int i, j, k;
        double det = 1;
        int row = 0;
        int col = 0;

        row = m.getRowLength();
        col = m.getColLength();

        if (m.isSquare()){
            if (row == 1 || col == 1){
                return m.getElmt(0, 0);
            } else {
                for (i = 0; i < row; i++){
                    j = i;

                    while (j < row && m.matrix[j][i] == 0){
                        j++;
                    }
                    

                    if (j != i){
                        for (k = 0; k < col; k++){
                            m.rowSwap(m, i, j);
                        }
                        det *= -1;
                    } 

                    if (j == row){
                        return 0;
                    }
                    
                    det *= m.matrix[i][i];
                    for (j = i + 1; j < row; j++){
                        double pengurang = m.matrix[j][i] / m.getElmt(i, i);
                        for (k = 0; k < row; k++){
                            m.setElmt(j, k, m.getElmt(j, k) - pengurang * m.getElmt(i, k));
                        }
                        
                    }
                }
            }
        }
        return (Math.round(det));
    }
}


