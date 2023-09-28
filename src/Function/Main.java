package Function;
import ADTMatrix.*;
import Function.*;

public class Main{
    public static void main (String[] args){
        double[][]m = InputMatrix.readMatrixKeyboard1();
        Matrix m1 = new Matrix(m, m.length, m[0].length);
        OutputMatrix.printMatrix(m1);
        SPL.gaussSPL((m1));
        double hasil = Determinan.detOBE(m1);
        System.out.print(hasil);
    }
}