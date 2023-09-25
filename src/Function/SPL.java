package Function;

import ADTMatrix.Matrix;

public class SPL {
    public static void inversSPL (Matrix MatrixSPL){
        Matrix A;
        Matrix B;
        Matrix x;
        Matrix matrixInvers;
        int i, j;

        if ((MatrixSPL.getRowLength() != MatrixSPL.getColLength()-1) || Determinan.detKofaktor(MatrixSPL) == 0){
            System.out.print("Matrix tidak memiliki balikan.");
            return;
        }

        A = new Matrix(MatrixSPL.getRowLength(), MatrixSPL.getColLength());
        B = new Matrix(MatrixSPL.col, 1);
        x = new Matrix(MatrixSPL.getRowLength(), 1);

        for (i = 0; i < MatrixSPL.getRowLength(); i++){
            for (j = 0; j < MatrixSPL.getColLength()-1; j++){
                A.setElmt(i, j, MatrixSPL.getElmt(i, j));
            }
        }

        for (i = 0; i < MatrixSPL.getRowLength(); i++){
            for (j = MatrixSPL.getColLength()-1; j < MatrixSPL.getColLength(); j++){
                B.setElmt(i, j, A.getElmt(i, j));
            }
        }

        matrixInvers = Invers.inversIdentitas(A);

        x = Matrix.multiplyMatrix(matrixInvers, B);
        
        for(i = 0; i < x.getRowLength(); i++){
            System.out.print("x"+Integer.toString(i+1)+" = "+Double.toString(x.getElmt(i, 0))+"\n");
        }
    }
}
