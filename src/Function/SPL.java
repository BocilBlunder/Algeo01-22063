package Function;

import ADTMatrix.Matrix;
import ADTMatrix.OutputMatrix;

public class SPL {
    public static void inversSPL (Matrix MatrixSPL){
        Matrix A;
        Matrix B;
        Matrix x;
        Matrix matrixInvers;
        int i, j;

        if ((MatrixSPL.getRowLength() != MatrixSPL.getColLength()-1) || Determinan.detKofaktor(MatrixSPL) == 0){
            System.out.print("SPL tidak dapat diselesaikan dengan metode invers.");
            return;
        }

        A = new Matrix(MatrixSPL.row, MatrixSPL.row);
        B = new Matrix(MatrixSPL.row, 1);
        x = new Matrix(MatrixSPL.row, 1);

        for (i = 0; i < A.getRowLength(); i++){
            for (j = 0; j < MatrixSPL.getColLength(); j++){
                if (j != MatrixSPL.getColLength()-1){
                    A.setElmt(i, j, MatrixSPL.getElmt(i, j));
                }
                else if (j == MatrixSPL.getColLength()-1){
                    B.setElmt(i, 0, MatrixSPL.getElmt(i, j));
                }
                
            }
        }

        matrixInvers = Invers.inversIdentitas(A);

        x = Matrix.multiplyMatrix(matrixInvers, B);
        
        for(i = 0; i < x.getRowLength(); i++){
            System.out.print("x"+Integer.toString(i+1)+" = "+Double.toString(x.getElmt(i, 0))+"\n");
        }
    }

    public static void cramerSPL (Matrix mCramer){
        Matrix A;
        Matrix B;
        Matrix x;
        int i, j;
        double det;
        Matrix mTemp;

        if ((mCramer.getRowLength() != mCramer.getColLength()-1) || Determinan.detKofaktor(mCramer) == 0){
            System.out.print("SPL tidak dapat diselesaikan dengan metode cramer.");
            return;
        }

        A = new Matrix(mCramer.getRowLength(), mCramer.getColLength()-1);
        B = new Matrix(mCramer.row, 1);
        x = new Matrix(mCramer.getRowLength(), 1);

        for (i = 0; i < mCramer.getRowLength(); i++){
            for (j = 0; j < mCramer.getColLength(); j++){
                if (j != mCramer.getColLength()-1){
                    A.setElmt(i, j, mCramer.getElmt(i, j));
                }
                else{
                    B.setElmt(i, 0, mCramer.getElmt(i, j));
                }
                
            }
        }
        
        det = Determinan.detKofaktor(A);
        mTemp = A;
        for (j = 0; j < A.row; j++){
            for (i = 0; i < A.row; i++){
                mTemp.setElmt(i, j, mCramer.getElmt(i, A.row));
            }
            double solution = Determinan.detKofaktor(mTemp)/det;
            System.out.print("x"+Integer.toString(j+1)+" = "+Double.toString(solution)+"\n");
            for (i = 0; i < A.row; i++){
                mTemp.setElmt(i, j, mCramer.getElmt(i, j));
            }
        }
    }
}