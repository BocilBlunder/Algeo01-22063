package ADTMatrix;
import Function.*;

public class Main{
    public static void main (String[] args){
        double [][]m = InputMatrix.readMatrixFile();
        Matrix m1 = new Matrix(m, m.length, m[0].length);
        OutputMatrix.printMatrix(m1);

    }
}