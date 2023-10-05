package ADTMatrix;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import Function.*;

public class Matrix {
    public int row = 0;
    public int col = 0;
    public double matrix [][] ;
    public double MARK = Double.NaN;

    // KONSTRUKTOR //
    // Membuat Sebuah Matriks kosong dari tipe double [][]
    public Matrix (double contents [][] , int rows, int cols) {
        this.matrix = contents;
        this.row = rows;
        this.col = cols;
    }

    // Membuat Sebuah Matriks kosong
    public Matrix (int rows, int cols){
        int i, j;
        matrix = new double[rows][cols];
        this.row = rows;
        this.col = cols;
        for (i = 0; i < rows; i++){
            for (j = 0; j < cols ; j++){
                setElmt(i, j, MARK);
            }
        }
    }

    // SELEKTOR //
    // Untuk Mencari Panjang Baris
    public int getRowLength(){
        return this.row;
    }

    // Untuk Mencari Panjang Kolom
    public int getColLength(){
        return this.col;
    }

    // Untuk Mengambil Indeks Terakhir dari Baris 
    public int getLastRowIdx(){
        return this.row - 1;
    }

    // Untuk Mengambil Indeks Terakhir dari Kolom 
    public int getLastColIdx(){
        return this.col - 1;
    }

    // Untuk Menentukan Apakah Matriks Berukuran MxM atau Tidak
    public boolean isSquare(){
        return this.row == this.col;
    }

    // Untuk Mengambil Elemen  pada Suatu Indeks
    public double getElmt(int i, int j){
        return this.matrix[i][j];
    }

    // Untuk Memasukkan Elemen ke Suatu Indeks
    public void setElmt(int i, int j, double elmt){
        this.matrix[i][j] = elmt;
    }

    // Untuk Menukar Baris
    public void rowSwap(Matrix m, int rows1, int rows2){
		double temp;
		for (int i = 0; i < m.col; i++){
			temp = m.getElmt(rows1, i);
			m.setElmt(rows1, i, m.getElmt(rows2, i));
			m.setElmt(rows2, i, temp);
		}
    }

    // Untuk Mengalikan 2 Matrix
    public static Matrix multiplyMatrix(Matrix m1, Matrix m2){
        int i, j, k;
        double temp;
        Matrix MMultiply;

        MMultiply = new Matrix(m1.row, m2.col);
        for (i = 0; i < MMultiply.row; i++) {
            for (j = 0; j < MMultiply.col; j++) {
                temp = 0;
                for(k = 0; k < m1.col; k++) {
                    temp = temp + (m1.getElmt(i, k) * m2.getElmt(k, j));
                }
                MMultiply.setElmt(i, j, temp);
            }
        }
        return MMultiply;
    }

    // Untuk Menjumlahkan Semua Elemen pada 1 Kolom
    public static double sumCol(Matrix m, int i){
        int j;
        double sum = 0;

        for(j = 0; j < m.row ;j++){
            sum += m.matrix[j][i];
        }
        return sum;
    }

    // Untuk Menjumlahkan Semua Elemen pada 2 Kolom yang Sudah Dikali
    public static double sumMultiplyCol(Matrix m, int i, int j){
        double sum = 0;
        int k = 0;
        for (k = 0; k < m.row ;k++){
            sum += m.matrix[k][i] * m.matrix[k][j];
        }
        return sum;
    }

    // Untuk Menambah Baris
    public static Matrix addRow(Matrix m, double[] newRow){
        Matrix mTemp;
        double[][] matrix;
        int i, j;

        matrix = new double[m.row + 1][m.col];
        for(i = 0; i < m.row; i++){
            for(j = 0; j < m.col; j++){
                matrix[i][j] = m.matrix[i][j];
            }
        }
        for(i = 0; i < m.col; i++){
            matrix[m.row][i] = newRow[i];
        }
        mTemp = new Matrix(matrix, m.row+1, m.col);
        return mTemp;
    }

    // Untuk Mendapatkan Det
    public static double detKofaktorIJ(Matrix m, int row, int col){
		int n = m.getRowLength();
        int i, j;
        Matrix temp;
        
		temp = new Matrix(n-1, n-1);

        i = 0;
        while (i < n){
            if (i == row){
                i++;
                continue;
            }
            j = 0;
            while (j < n){
                if (j == col){
                    j++;
                    continue;
                }

                if (i < row){
                    if (j < col){
						temp.setElmt(i, j, m.getElmt(i, j));
                    }
					else{
						temp.setElmt(i, j-1, m.getElmt(i, j));
                    }
				}
				else{
					if (j < col){
						temp.setElmt(i-1, j, m.getElmt(i, j));
                    }
					else{
						temp.setElmt(i-1, j-1, m.getElmt(i, j));
                    }
				}
                j++;
            }
            i++;
        }
		return Determinan.detKofaktor(temp);
    }

    // Untuk Mendapatkan Matriks Kofaktor
    public static Matrix matriksKofaktor (Matrix m){
        int n = m.row;
        Matrix mKofaktor;

		mKofaktor = new Matrix(n,n);
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				mKofaktor.setElmt(i, j, detKofaktorIJ(m,i,j));
				if ((i+j) % 2 == 1 && mKofaktor.getElmt(i,j) != 0)
					mKofaktor.setElmt(i, j, (mKofaktor.getElmt(i,j) * -1));
			}
		}

		return mKofaktor;
    }

    // Untuk Mendapatkan Matriks Adjoin
    public static Matrix Adjoin (Matrix m){
        Matrix mAdjoin;
        int i, j;
        
        m = Matrix.matriksKofaktor(m);
        mAdjoin = new Matrix(m.row, m.col);

        for (i = 0; i < m.row; i++){
            for (j = 0; j < m.col; j++){
                mAdjoin.setElmt(i, j, m.getElmt(j, i));
            }
        }

        return mAdjoin;
    }

    public static void backSubstitution(Matrix matrix, double[] X) {
        int i, j;
        int n, m;
        
        n = matrix.getRowLength();
        m = matrix.getColLength();

        for (i = n - 1; i >= 0; i--) {
            X[i] = matrix.getElmt(i, m - 1);
            for (j = i + 1; j < n; j++) {
                X[i] -= matrix.getElmt(i, j) * X[j];
            }
            X[i] /= matrix.getElmt(i, i);
        }
    }

    public static int SolutionType(Matrix matrix) {
        int i, j;
        int n, m;
        int rank = 0;
        int augmentedColumns;
        boolean hasNonZeroRow = false;
        
        n = matrix.getRowLength();
        m = matrix.getColLength();
        augmentedColumns = m - 1;

        for (i = 0; i < n; i++) {
            boolean isZeroRow = true;
            for (j = 0; j < augmentedColumns; j++) {
                if (matrix.getElmt(i, j) != 0) {
                    isZeroRow = false;
                    break;
                }
            }
            if (!isZeroRow) {
                rank++;
                hasNonZeroRow = true;
            } else if (matrix.getElmt(i, augmentedColumns) != 0) {
                return 0;
            }
        }

        if (!hasNonZeroRow || rank < augmentedColumns) {
            return 2;
        } else {
            return 1;
        }
    }

    //Fungsi jika matriksnya memiliki SPL berupa parametrik
    public static void solveManySolution(Matrix matrix) {
        int nEff = matrix.getColLength() - 1;
        boolean[] visited = new boolean[nEff];
        char[] parametric = new char[nEff];
        int cur = 17;

        for (int i = 0; i < nEff; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < matrix.getRowLength(); i++) {
            for (int j = i; j < nEff; j++) {
                if (matrix.getElmt(i, j) == 1) {
                visited[j] = true;
                StringBuilder temp = new StringBuilder();

                if (Math.abs(matrix.getElmt(i, matrix.getColLength() - 1)) > 1e-8) {
                    temp.append(String.format("%.4f", (matrix.getElmt(i, matrix.getColLength() - 1))));
                }

                for (int k = j + 1; k < nEff; k++) {
                    if (Math.abs(matrix.getElmt(i, k)) > 1e-8) {
                        if (!visited[k]) {
                            visited[k] = true;
                            parametric[k] = (char) ('a' + cur % 26);
                            System.out.printf("X%d = %c%n", k + 1, parametric[k]);
                            cur = (cur + 1) % 26;
                        }

                        if (matrix.getElmt(i, k) > 0) {
                            if (temp.length() == 0) {
                                temp.append(String.format("%.4f", Math.abs(matrix.getElmt(i, k))));
                            } else {
                                if (Math.abs(matrix.getElmt(i, k)) == 1) {
                                    temp.append(String.format(" - ", Math.abs(matrix.getElmt(i, k))));
                                }
                                else {
                                    temp.append(String.format(" - %.4f", Math.abs(matrix.getElmt(i, k))));
                                }
                            }
                        } else {
                            if (temp.length() == 0) {
                                temp.append(String.format("%.4f", Math.abs(matrix.getElmt(i, k))));
                            } else {
                                if (Math.abs(matrix.getElmt(i, k)) == 1) {
                                    temp.append(String.format(" + ", Math.abs(matrix.getElmt(i, k))));
                                }
                                else {
                                temp.append(String.format(" + %.4f", Math.abs(matrix.getElmt(i, k))));
                                }
                            }
                        }
                        temp.append(parametric[k]);
                    }
                }
                System.out.printf("X%d = %s%n", j + 1, temp.toString());
                break;
            } else {
                if (!visited[j]) {
                    visited[j] = true;
                    parametric[j] = (char) ('a' + cur % 26);
                    System.out.printf("X%d = %c%n", j + i, parametric[j]);
                    cur = (cur + 1) % 26;
                    }
                }
            }
        }
        
        //Untuk menyimpan dalam File
        Scanner input = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        int pil3 = OutputMatrix.printMenuOutput();
        if (pil3 == 1){
            String newfileName = "";
            System.out.print("Masukkan nama file: ");
            try{
                newfileName = inputFile.readLine();
                String path = "test/Output/" + newfileName;
            }
            catch(IOException err){
                err.printStackTrace();
            }
            try{
                FileWriter file = new FileWriter("test/Output/" + newfileName);
                file.write("Solusi parametrik:\n");
                nEff = matrix.getColLength() - 1;
                visited = new boolean[nEff];
                parametric = new char[nEff];
                cur = 17;

                for (int i = 0; i < nEff; i++) {
                    visited[i] = false;
                }
                for (int i = 0; i < matrix.getRowLength(); i++) {
                    for (int j = i; j < nEff; j++) {
                        if (matrix.getElmt(i, j) == 1) {
                        visited[j] = true;
                        StringBuilder temp = new StringBuilder();

                        if (Math.abs(matrix.getElmt(i, matrix.getColLength() - 1)) > 1e-8) {
                            temp.append(String.format("%.4f", (matrix.getElmt(i, matrix.getColLength() - 1))));
                        }

                        for (int k = j + 1; k < nEff; k++) {
                            if (Math.abs(matrix.getElmt(i, k)) > 1e-8) {
                                if (!visited[k]) {
                                    visited[k] = true;
                                    parametric[k] = (char) ('a' + cur % 26);
                                    file.write("X"+Integer.toString(k+1)+" = "+(parametric[k])+"\n");
                                    cur = (cur + 1) % 26;
                                }

                                if (matrix.getElmt(i, k) > 0) {
                                    if (temp.length() == 0) {
                                        temp.append(String.format("%.4f", Math.abs(matrix.getElmt(i, k))));
                                    } else {
                                        if (Math.abs(matrix.getElmt(i, k)) == 1) {
                                            temp.append(String.format(" - ", Math.abs(matrix.getElmt(i, k))));
                                        }
                                        else {
                                            temp.append(String.format(" - %.4f", Math.abs(matrix.getElmt(i, k))));
                                        }
                                    }
                                } else {
                                    if (temp.length() == 0) {
                                        temp.append(String.format("%.4f", Math.abs(matrix.getElmt(i, k))));
                                    } else {
                                        if (Math.abs(matrix.getElmt(i, k)) == 1) {
                                            temp.append(String.format(" + ", Math.abs(matrix.getElmt(i, k))));
                                        }
                                        else {
                                        temp.append(String.format(" + %.4f", Math.abs(matrix.getElmt(i, k))));
                                        }
                                    }
                                }
                                temp.append(parametric[k]);
                            }
                        }
                        file.write("X"+Integer.toString(j+1)+" = "+(temp.toString())+"\n");
                        break;
                    } else {
                        if (!visited[j]) {
                            visited[j] = true;
                            parametric[j] = (char) ('a' + cur % 26);
                            file.write("X"+Integer.toString(j+1)+" = "+(parametric[j])+"\n");
                            cur = (cur + 1) % 26;
                            }
                        }
                    }
                }
                file.close();
            }
            catch(IOException err){
                err.printStackTrace();
            }
        }
    }

    //Menghasilkan matriks eselon baris
    public static Matrix gaussElimination(Matrix matrix) {
        int n = matrix.getRowLength();
        int m = matrix.getColLength();
        double X[] = new double[n];
        // Cari pivot non-nol pertama dalam kolom
        for (int i = 0; i < n; i++) {
            int pivotRow = i;
            if (i < matrix.col){
            while (pivotRow < n && matrix.getElmt(pivotRow, i) == 0) {
                pivotRow++;
            }
            //Pivot ketermu
            if (pivotRow == n) {
                continue;
            }

            if (matrix.getElmt(pivotRow, i) != 1) {
                double pivotValue = matrix.getElmt(pivotRow, i);
                for (int j = i; j < m; j++) {
                    matrix.setElmt(pivotRow, j, matrix.getElmt(pivotRow, j) / pivotValue);
                }
            }
            
            // Tukar baris pivot dengan baris saat ini
            for (int j = i; j < m; j++) {
                double temp = matrix.getElmt(i, j);
                matrix.setElmt(i, j, matrix.getElmt(pivotRow, j));
                matrix.setElmt(pivotRow, j, temp);
            }

            for (int j = i + 1; j < n; j++) {
                double factor = matrix.getElmt(j, i);
                for (int k = i; k < m; k++) {
                    matrix.setElmt(j, k, matrix.getElmt(j, k) - factor * matrix.getElmt(i, k));
                }
            }
        }
    }

        for (int i = 0 ; i < n ; i++) {
            int allZero = 1 ;
            for (int j = 0; j < m - 1 ; j++) {
                if (matrix.getElmt(i,j) != 0) {
                    allZero = 0;
                    break;
                }
            }
            if (allZero == 1) {
                for (int k = i ; k < n - 1 ; k++) {
                    for (int j = 0 ; j < m ; j++) {
                        matrix.rowSwap(matrix, k, k+1);
                }
            }
        }
    }

        for (int i = 0; i < n; i++) {
            int pivotColl = 0;
            while (pivotColl < m - 1 && matrix.getElmt(i, pivotColl) == 0) {
                pivotColl++;
            }

            if (pivotColl == m - 1) {
                continue;
            }
            
            if (matrix.getElmt(i, pivotColl) != 1) {
                double pivotValue2 = matrix.getElmt(i, pivotColl);
                for (int k = pivotColl; k < m; k++) {
                    matrix.setElmt(i, k, matrix.getElmt(i, k) / pivotValue2);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sameAbsValue = 1;
                for (int k = 0 ; k < m - 1; k++) {
                    if (Math.abs(matrix.getElmt(i, k)) != Math.abs(matrix.getElmt(j, k) )) {
                        sameAbsValue = 0;
                        break;
                    }
                }
                if (sameAbsValue == 1) {
                    if (matrix.getElmt(i, m-2) < 0 && matrix.getElmt(i, m-2) > 0) {
                        for (int k = 0; k < m ; k++) {
                            matrix.setElmt(j, k, matrix.getElmt(j, k) + matrix.getElmt(i, k));
                        }
                    }
                    else if (matrix.getElmt(i, m-2) > 0 && matrix.getElmt(i, m-2) < 0) {
                        for (int k = 0; k < m ; k++) {
                            matrix.setElmt(j, k, matrix.getElmt(j, k) + matrix.getElmt(i, k));
                }
            }
                    else if (matrix.getElmt(i, m-2) > 0 && matrix.getElmt(i, m-2) > 0) {
                        for (int k = 0; k < m ; k++) {
                            matrix.setElmt(j, k, matrix.getElmt(j, k) - matrix.getElmt(i, k));
                }
            }
                    else if (matrix.getElmt(i, m-2) < 0 && matrix.getElmt(i, m-2) < 0) {
                        for (int k = 0; k < m ; k++) {
                            matrix.setElmt(j, k, matrix.getElmt(j, k) - matrix.getElmt(i, k));
                }
            }
        }
            }
        }
        if ( n > m) {
            Matrix.solveManySolution(matrix);
        }
        else {
            backSubstitution(matrix, X);
        }
        return matrix;
    }
    
    //Menghasilkan matriks eselon baris tereduksi
    public static Matrix gaussJordanElimination(Matrix A) {
        int n = A.getRowLength();
        int m = A.getColLength();
        
        for (int i = 0; i < n; i++) {
            // Cari pivot non-nol pertama dalam kolom
            int pivotRow = i;
            if (i < A.col){
                while (pivotRow < n && A.getElmt(pivotRow, i) == 0) {
                    pivotRow++;
                }

                // Pivot ketemu
                if (pivotRow == n) {
                    continue;
                }

                // Tukar baris pivot dengan baris saat ini
                A.rowSwap(A, i, pivotRow);

                // Buat pivot jadi 1
                double pivot = A.getElmt(i, i);
                for (int j = i; j < m; j++) {
                    A.setElmt(i, j, A.getElmt(i, j) / pivot);
                }

                // Eliminasi baris lainnya
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        double factor = A.getElmt(j, i);
                        for (int k = i; k < m; k++) {
                            A.setElmt(j, k, A.getElmt(j, k) - factor * A.getElmt(i, k));
                        }
                    }
                }
            }
        }
        
        // Jika ada baris dengan semua elemen nol, swap ke paling bawah
        for (int i = 0; i < n; i++) {
            int allZero = 1;
            for (int j = 0; j < m - 1; j++) {
                if (A.getElmt(i, j) != 0) {
                    allZero = 0;
                    break;
                }
            }
            if (allZero == 1) {
                for (int k = i; k < n - 1; k++) {
                    A.rowSwap(A, k, k + 1);
                }
            }
        }

        // Jika ada baris dengan semua elemen nol, swap ke paling bawah
        for (int i = 0; i < n; i++) {
            // Cari pivot non-nol pertama dalam kolom
            int pivotCol = 0;
            while (pivotCol < m - 1 && A.getElmt(i, pivotCol) == 0) {
                pivotCol++;
            }

            // Pivot ketemu
            if (pivotCol == m - 1) {
                continue;
            }

            // Jika elemen pivot bukan 1, bagi seluruh baris oleh elemen pivot
            if (A.getElmt(i, pivotCol) != 1) {
                double pivotValue = A.getElmt(i, pivotCol);
                for (int k = pivotCol; k < m; k++) {
                    A.setElmt(i, k, A.getElmt(i, k) / pivotValue);
                }
            }
        }
        return A;
    }
}
