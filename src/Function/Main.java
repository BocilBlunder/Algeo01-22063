package Function;
import ADTMatrix.*;
import Function.*;

public class Main{
    public static void main (String[] args){
        double[][]m = InputMatrix.readMatrixKeyboard();
        Matrix m1 = new Matrix(m, m.length, m[0].length);
    }
}