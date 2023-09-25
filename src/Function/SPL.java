import ADTMatrix.Matrix;
import java.util.*;
import java.io.*;

public class SPL {
    public void metodeInvers (Matrix MatrixSPL){
        Matrix A;
        Matrix B;
        Matrix x;
        Matrix matrixInvers;

        if ((MatrixSPL.getRowLength() != MatrixSPL.getColLength()-1) || Determinan.detKofaktor(MatrixSPL) == 0){
            System.out.print("Matrix tidak memiliki balikan.");
            return;
        }

        A = new Matrix(MatrixSPL.getRowLength(), MatrixSPL.getColLength());
        B = new Matrix(MatrixSPL.getRowLength(), 1);
        x = new Matrix(MatrixSPL.getRowLength(), 1);

        matrixInvers = Invers.inversIdentitas(A);

        x = Matrix.multiplyMatrix(matrixInvers, B);
        
        for(int i = 0; i < x.getRowLength(); i++){
            System.out.print("x"+Integer.toString(i+1)+" = "+Double.toString(x.getElmt(i, 1))+"\n");
        }
    }
}
