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
            System.out.print("SPL tidak dapat diselesaikan dengan metode invers.");
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

    public static void cramerSPL (Matrix mCramer){
        Matrix A;
        Matrix B;
        Matrix x;
        int i, j;
        Matrix detMatriks;
        double det;
        Matrix mTemp;

        if ((mCramer.getRowLength() != mCramer.getColLength()-1) || Determinan.detKofaktor(mCramer) == 0){
            System.out.print("SPL tidak dapat diselesaikan dengan metode cramer.");
            return;
        }

        A = new Matrix(mCramer.getRowLength(), mCramer.getColLength());
        B = new Matrix(mCramer.row, 1);
        x = new Matrix(mCramer.getRowLength(), 1);
        detMatriks = new Matrix(mCramer.row, 1);

        det = Determinan.detKofaktor(mCramer);

        for (i = 0; i < mCramer.getRowLength(); i++){
            for (j = 0; j < mCramer.getColLength()-1; j++){
                A.setElmt(i, j, mCramer.getElmt(i, j));
            }
        }

        for (i = 0; i < mCramer.getRowLength(); i++){
            for (j = mCramer.getColLength()-1; j < mCramer.getColLength(); j++){
                B.setElmt(i, j, A.getElmt(i, j));
            }
        }

        j = 0;
        mTemp = mCramer;
        while (j < mCramer.col){
            for (i = 0; i < mCramer.getRowLength(); i++){
                mTemp.setElmt(i, j, B.getElmt(i, j));
            }
            detMatriks.setElmt(i, 0, Determinan.detKofaktor(mTemp));
            x.setElmt(i, 0, (detMatriks.getElmt(i, 0))/det);
            j++;
        }

        for(i = 0; i < x.getRowLength(); i++){
            System.out.print("x"+Integer.toString(i+1)+" = "+Double.toString(x.getElmt(i, 0))+"\n");
        }
    }
}