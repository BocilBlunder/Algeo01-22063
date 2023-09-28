package ADTMatrix;

import Function.Determinan;

public class Matrix {
    public int row = 0;
    public int col = 0;
    public double matrix [][] ;
    public double MARK = Double.NaN;

    // KONSTRUKTOR //
    public Matrix (double contents [][] , int rows, int cols) {
        this.matrix = contents;
        this.row = rows;
        this.col = cols;
    }

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
    public int getRowLength(){
        return this.row;
    }

    public int getColLength(){
        return this.col;
    }

    public int getLastRowIdx(){
        return this.row - 1;
    }

    public int getLastColIdx(){
        return this.col - 1;
    }

    public boolean isSquare(){
        return this.row == this.col;
    }
    public double getElmt(int i, int j){
        return this.matrix[i][j];
    }
    public void setElmt(int i, int j, double elmt){
        this.matrix[i][j] = elmt;
    }

    public void rowSwap(Matrix m, int rows1, int rows2){
		double temp;
		for (int i = 0; i < m.col; i++){
			temp = m.getElmt(rows1, i);
			m.setElmt(rows1, i, m.getElmt(rows2, i));
			m.setElmt(rows2, i, temp);
		}
    }

    // PRIMITIF //
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

    public static double detKofaktorIJ(Matrix m, int row, int col){
		int n = m.getRowLength();
        int i, j;
		Matrix temp = new Matrix(n-1, n-1);

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

    public static Matrix matriksKofaktor (Matrix m){
        int n = m.row;

		Matrix mKofaktor = new Matrix(n,n);
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				mKofaktor.setElmt(i, j, detKofaktorIJ(m,i,j));
				if ((i+j) % 2 == 1 && mKofaktor.getElmt(i,j) != 0)
					mKofaktor.setElmt(i, j, (mKofaktor.getElmt(i,j) * -1));
			}
		}

		return mKofaktor;
    }

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
        int n = matrix.getRowLength();
        int m = matrix.getColLength();

        for (int i = n - 1; i >= 0; i--) {
            X[i] = matrix.getElmt(i, m - 1);
            for (int j = i + 1; j < n; j++) {
                X[i] -= matrix.getElmt(i, j) * X[j];
            }
            X[i] /= matrix.getElmt(i, i);
        }
    }

    public static int SolutionType(Matrix matrix) {
        int n = matrix.getRowLength();
        int m = matrix.getColLength();
        int rank = 0;
        int augmentedColumns = m - 1;
        boolean hasNonZeroRow = false;

        for (int i = 0; i < n; i++) {
            boolean isZeroRow = true;
            for (int j = 0; j < augmentedColumns; j++) {
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
                        temp.append(String.format("%.2f", Math.abs(matrix.getElmt(i, matrix.getColLength() - 1))));
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
                                    temp.append(String.format("%.2f", Math.abs(matrix.getElmt(i, k))));
                                } else {
                                    temp.append(String.format(" - %.2f", Math.abs(matrix.getElmt(i, k))));
                                }
                            } else {
                                if (temp.length() == 0) {
                                    temp.append(String.format("%.2f", Math.abs(matrix.getElmt(i, k))));
                                } else {
                                    temp.append(String.format(" + %.2f", Math.abs(matrix.getElmt(i, k))));
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
                        System.out.printf("X%d = %c%n", j + 1, parametric[j]);
                        cur = (cur + 1) % 26;
                    }
                }
            }
        }
    }

    public static Matrix gaussElimination(Matrix matrix) {
        int n = matrix.getRowLength();
        int m = matrix.getColLength();
        double X[] = new double[n];

        for (int i = 0; i < n; i++) {
            int pivotRow = i;
            while (pivotRow < n && matrix.getElmt(pivotRow, i) == 0) {
                pivotRow++;
            }

            if (pivotRow == n) {
                continue;
            }

            if (matrix.getElmt(pivotRow, i) != 1) {
                double pivotValue = matrix.getElmt(pivotRow, i);
                for (int j = i; j < m; j++) {
                    matrix.setElmt(pivotRow, j, matrix.getElmt(pivotRow, j) / pivotValue);
                }
            }

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
        backSubstitution(matrix, X);
        return matrix;
    }

    public static Matrix gaussJordanElimination(Matrix A) {
        int n = A.getRowLength();
        int m = A.getColLength();
        
        for (int i = 0; i < n; i++) {
            // Cari pivot non-nol pertama dalam kolom
            int pivotRow = i;
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
