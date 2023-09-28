package Function;
import ADTMatrix.*;
import Function.*;

public class Main{
    public static void main (String[] args){
        double[][]m = InputMatrix.readMatrixKeyboard2();
        Matrix m1 = new Matrix(m, m.length, m[0].length);
        OutputMatrix.printMatrix(m1);
        SPL.gaussSPL((m1));
        System.out.println();
        OutputMatrix.printMatrix(m1);
        System.out.println();
        SPL.gaussJordanSPL((m1));
        System.out.println();
        OutputMatrix.printMatrix(m1);
        System.out.println();
        SPL.inversSPL(m1);
        System.out.println();
        SPL.cramerSPL(m1);
        System.out.println();
    }
}