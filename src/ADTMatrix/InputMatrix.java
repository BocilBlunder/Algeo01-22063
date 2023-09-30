package ADTMatrix;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputMatrix{
    // Input dari Keyboard
    public static Scanner input = new Scanner(System.in);
    
    public static double[][] readMatrixKeyboard1(){
        int i,j;
        double [][] matrixA, matrixB, m;
        int rows, cols;

        System.out.print("Masukkan jumlah baris: ");
        rows = input.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        cols = input.nextInt();

        matrixA = new double[rows][cols];
        System.out.println("Masukkan elemen matriks A: ");
        for(i = 0; i < rows; i++){
            for(j = 0; j < cols; j++){
                matrixA [i][j] = input.nextDouble();
            }
        }

        matrixB = new double[rows][1];
        System.out.println("Masukkan elemen matriks B: ");
        for(i = 0; i < rows; i++){
            for(j = 0; j < 1; j++){
                matrixB [i][j] = input.nextDouble();
            }
        }
        
        m = new double [rows][cols + 1];
        for (i = 0; i < rows; i++){
            System.arraycopy(matrixA[i], 0, m[i], 0, cols);
            System.arraycopy(matrixB[i], 0, m[i], cols, 1);
        }
        return m;
    }

    public static double[][] readMatrixKeyboard2(){
        int i,j;
        double [][] matrix;
        int rows, cols;

        System.out.print("Masukkan jumlah baris: ");
        rows = input.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        cols = input.nextInt();

        matrix = new double[rows][cols];
        System.out.println("Masukkan elemen matriks: ");
        for(i = 0; i < rows; i++){
            for(j = 0; j < cols; j++){
                matrix[i][j] = input.nextDouble();
            }
        }
        return matrix;
    }

    public static double[][] readInterpolasiKeyboard(){
        int i, j;
        int n;
        double [][] matrix;

        System.out.print("Masukkan derajat polinom (n): ");
        n = input.nextInt();

        matrix = new double[n + 1][2];
        System.out.println("Masukkan titik x dan y: ");
        for(i = 0; i < n + 1; i++){
            for(j = 0; j < 2; j++){
                matrix[i][j] = input.nextDouble();
            }
        }
        return matrix;
    }

    public static double[][] readRegresiKeyboard(){
        int i, j;
        int n, m;
        double [][] matrix;

        System.out.print("Masukkan jumlah peubah (x): ");
        n = input.nextInt();
        System.out.print("Masukkan jumlah sampel (m): ");
        m = input.nextInt();

        matrix = new double[m][n + 1];
        System.out.println("Masukkan titik x dan y: ");
        for(i = 0; i < m; i++){
            for(j = 0; j < n + 1; j++){
                matrix[i][j] = input.nextDouble();
            }
        }
        return matrix;
    }


    // Input dari File
    public static double[][] readMatrixFile(){
        Scanner scan;
        File tes;
        double[][] matrix;
        int i, j;
        int row = 0;
        int col = 0;

        System.out.print("Masukkan nama file: ");
        String file = input.nextLine();
        String path = "Test/" + file;
        System.out.println(path);

        try{
            tes = new File(path);
            scan = new Scanner(tes);
            while(scan.hasNextLine()){
                col = (scan.nextLine()).split(" ").length;
                row++;
            }
            scan.close();

            matrix = new double[row][col];
            scan = new Scanner(tes);
  
            for (i = 0; i < row; i++){
                for (j = 0; j < col; j++){
                    matrix[i][j] = scan.nextDouble();
                }
            }
            // close the scanner
            scan.close();
            return matrix;
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            System.out.println("Returning a 1x1 matrix with value 0");
            matrix = new double[1][1];
            return matrix;
        }
    }
}
    


    

