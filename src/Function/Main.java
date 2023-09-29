package Function;
import ADTMatrix.*;
import Function.*;

public class Main{
    public static void main (String[] args){
        double[][]m = InputMatrix.readRegresiKeyboard();
        Matrix m1 = new Matrix(m, m.length, m[0].length);
        double hasil = Matrix.sumMultiplyCol(m1, 1, 2);
        System.out.print(hasil);
    }
}