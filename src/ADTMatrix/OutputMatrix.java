package ADTMatrix;

public class OutputMatrix {
    double matrix [][] ;
    double MARK = Double.NaN;
    int row = 0;
    int col = 0;

    public static void printMatrix(){
        int i, j;
        Matrix matrix;
        for (i = 0; i < matrix.row; i++){
            for (j = 0; j < matrix.col; j++){
                System.out.print(matrix.matrix[i][j]+" ");
            }
            System.out.print("\n");
        }
    }
}
