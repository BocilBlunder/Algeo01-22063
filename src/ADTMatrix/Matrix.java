package Matrix;

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

       /* for (i = 0; i < rows; i++)
        {
            for (j = 0; j < cols; j++)
            {
                contents[i][j] = MARK;
            }
        }
    }

    public Matrix (double [][] contents , int rows, int cols) {
        this.matrix = contents;
        this.row = rows;
        this.col = cols;
        for (i = 0; i < rows; i++)
        {
            for (j = 0; j < cols; j++)
            {
                contents[i][j] = MARK;
            }
        }

    }*/

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
        return matrix.length == matrix[0].length;
    }

}