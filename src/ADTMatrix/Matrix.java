package ADTMatrix;

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
		for (int i = 0; i < m.getColLength(); i++){
			temp = m.getElmt(rows1, i);
			m.setElmt(rows1, i, m.getElmt(rows2, i));
			m.setElmt(rows2, i, temp);
		}
    }


}