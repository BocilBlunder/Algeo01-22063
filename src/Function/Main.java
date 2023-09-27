package Function;
import ADTMatrix.*;
import Function.*;

public class Main{
    public static void main (String[] args){
        double[][]m = InputMatrix.readMatrixKeyboard();
        Matrix m1 = new Matrix(m, m.length, m[0].length);
        double hasil = Determinan.detKofaktor((m1));
        double hasil2 = Determinan.detOBE(m1);
        System.out.printf("%.2f", hasil);
        System.out.printf("%.2f",hasil2);
    }
}