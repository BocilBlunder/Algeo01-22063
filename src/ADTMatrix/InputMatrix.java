package Matrix;
import java.util.*;
import java.io.*;


public class InputMatrix{

    // Input dari Keyboard
    public Matrix readMatrixKeyboard(){
        Scanner input = new Scanner(System.in);
        int i,j;
        int rows, cols;
        double [][] matrix;
        Matrix m;

        System.out.print("Masukkan jumlah baris: ");
        rows = input.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        cols = input.nextInt();

        matrix = new double[rows][cols];

        for(i = 0; i < row; i++){
            for(j = 0; j < col; j++){
                matrix[i][j] = input.nextDouble();
            }
        }
        return m;
    }

    // Input dari File
    public Matrix readMatrixFile(){
        Scanner input = new Scanner(System.in);
    }

}