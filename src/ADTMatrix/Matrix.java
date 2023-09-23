package ADTMatrix;

import java.util.*;
import java.util.Scanner;

public class Matrix {
    int rowIdx = 0;
    int colIdx = 0;
    int row = 0;
    int col = 0;
    double [][] matrix ;
    double MARK = Double.NaN;
    
    public Matrix (double [][] contents , int rows, int cols) {
        this.matrix = contents;
        this.row = rows;
        this.col = cols;

    }

    public int getRowLength(double [][] matrix){
        return this.matrix.length;
    }

    public int getColLength(double [][] matrix){
        return this.matrix[0].length;
    }

    public int getLastRowIdx(double [][] matrix){
        return this.matrix.length - 1;
    }

    public int getLastColIdx(double [][] matrix){
        return this.matrix[0].length - 1;
    }

    public boolean isSquare(double [][] matrix){
        return getRowLength(matrix) == getColLength(matrix);
    }

    public Matrix readMatrix(){
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

        m = new Matrix(matrix, rows, cols);
        return m;
    }

    
}

