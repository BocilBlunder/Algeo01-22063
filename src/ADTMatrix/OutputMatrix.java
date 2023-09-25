package ADTMatrix;

public class OutputMatrix {
    public static void printMatrix(Matrix matrix) {
        int i, j;
        for (i = 0; i < matrix.row; i++) {
            for (j = 0; j < matrix.col; j++) {
                System.out.print(matrix.getElmt(i, j));
            }
            System.out.println();
        }
    }
}
