package Function;

import ADTMatrix.Matrix;
import ADTMatrix.OutputMatrix;

public class Determinan {
    // Mencari Det dengan Memakai Kofaktor
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

                // Kofaktor Dengan Baris 0 / Baris Pertama
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

    // Mencari Det dengan Memakai OBE
    public static double detOBE (Matrix m){
        int i, j, k, l;
        double det = 1;
        double x = 0;
        double y = 0;
        int row = 0;
        int col = 0;

        row = m.getRowLength();
        col = m.getColLength();

        // Menentukan Apakah Matriks Berukuran MxM
        if (m.isSquare()){
            if(row == 1 || col == 1){
                return m.matrix[0][0];
            } else{
                // Mencari Ujung Matriks yang 0
                for (i = 0; i < row; i++){
                    if (i < row){
                        if (m.matrix[i][i] == 0){
                            j = i + 1;

                            while (j < row && m.matrix[j][i] == 0){
                                j++;
                            }
                            
                            // Melakukan Pertukaran Baris
                            if (j < row){
                                det *= -1;
                                double temp;
                                for (k = 0; k < row; k++){
                                    temp = m.matrix[i][k];
                                    m.matrix[i][k] = m.matrix[j][k];
                                    m.matrix[j][k] = temp;
                                }
                            } else {
                                det = 0;
                                break;
                                
                            }
                        }
                        
                        // Membuat Ujung Kiri Baris Menjadi tidak 0
                        x = m.matrix[i][i];
                        det *= x;
                        if (x != 0){
                            for (j = 0; j < row; j++){
                                m.matrix[i][j] /= x;
                            }
                        }
                            
                        // Membuat Kolom di Bawah Angka pertama Menjadi 0
                        for (k = i + 1; k < row; k++){
                            y = m.matrix[k][i];
                            for (l = 0; l < row; l++){
                                m.matrix[k][l] -= m.matrix[i][l] * y;
                            }
                        }
                    }
                }
                return det;
            }
        } else {
            return m.MARK;
        }
    }
}
