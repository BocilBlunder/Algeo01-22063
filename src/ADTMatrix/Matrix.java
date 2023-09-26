package ADTMatrix;

import Function.Determinan;

public class Matrix {
    public int row = 0;
    public int col = 0;
    public double matrix [][] ;
    public double MARK = Double.NaN;

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

}