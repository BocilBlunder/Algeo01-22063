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

        // Menentukan Apakah Matriks Berukuran MxM
        if (m.isSquare()){
            if(row == 1 || col == 1){
                return m.matrix[0][0];
            } else{

                // Kofaktor Dengan Baris 0
                for (i = 0; i < col; i++){

                    // Membuat Matriks Kofaktor
                    mTemp = new Matrix(row - 1, col - 1);
                    for (j = 1; j < col; j++){
                        for (k = 0; k < row; k++){

                            // Menentukan Indeks yang Masuk ke Matriks Kofaktor
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
        int swap = 1;
        double det = 1;
        double x = 0;
        double y = 0;
        int row = 0;
        int col = 0;

        row = m.getRowLength();
        col = m.getColLength();

        // Menentukan Apakah Matriks Berukuran MxM
        if (m.isSquare()){
            if (row == 1 || col == 1){
                return m.matrix[0][0];
            } else {
                // Mencari Ujung Matriks yang 0
                for (i = 0; i < row - 1; i++){
                    double temp = m.getElmt(i, i);
                    if (temp == 0){
                        for (j = i + 1; j < row; j++){
                            double tes = m.matrix[j][i];
                            if (tes != 0){
                                for (k = 0; k < row; k++){
                                    m.rowSwap(m, i, j);
                                }
                                swap *= -1;
                                break;
                            }
                        }
                    }
                    for(j = i + 1; j < row; j++){
                        x = m.matrix[j][i] / m.matrix[i][i];
                        x *= -1;
                        for (k = 0; k < row; k++){
                            y = m.matrix[i][k] * x;
                            m.setElmt(j, k, m.getElmt(j, k) + y);
                        }
                    }
                }
                int res = swap;
                for (i = 0; i < row; i++){
                    res *= m.getElmt(i, i);
                }
                return res;
            }
        } else {
            return m.MARK;
        }
    }
}