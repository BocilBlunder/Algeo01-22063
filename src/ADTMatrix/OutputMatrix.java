package ADTMatrix;

public class OutputMatrix {
    double [][] matrix ;
    double MARK = Double.NaN;
    int row = 0;
    int col = 0;
    
    public Matrix (double [][] contents , int rows, int cols) {
        this.matrix = contents;
        this.row = rows;
        this.col = cols;
    }
    public void printMatrix(){
        int i, j;
        Matrix matrix;
        for (i = 0; i < this.matrix.length; i++){
            for (j = 0; j < this.matrix[0].length; j++){
                System.out.print(this.matrix[i][j]+" ");
            }
            System.out.print("\n");
        }
    }
}
