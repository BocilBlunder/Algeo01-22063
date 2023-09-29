package ADTMatrix;
import java.util.*;
import java.io.*;

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
    public static Matrix readMatrixFile(){
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        double[][] matrix;
        Boolean fileExist;
        int i, j;
        int rows, cols;

        fileExist = false;
        while (fileExist == true){
            fileExist = true;
            String fileName;
            fileName = "";
            System.out.print("Masukkan nama file: ");
            try{
                fileName = inputFile.readLine();
            }
            catch(IOException err){
                err.printStackTrace();
            }
            try{
                rows = 0;
                cols = 0;
                Scanner file = new Scanner(new File("../test/"+fileName));
                matrix = new double[rows][cols];
                while(file.hasNextLine()){
                    rows++;
                    cols = file.nextLine().split(" ").length;
                }
            
                file.close();
                file = new Scanner(new File("../test/"+fileName));
                for (i = 0; i < rows; i++){
                    for (j = 0; j < cols; j++){
                        matrix[i][j] = input.nextDouble();
                    }
                }
                file.close();
                Matrix m = new Matrix(matrix, matrix.length, matrix[0].length);
                return m;
            }
            catch(FileNotFoundException err){
                err.printStackTrace();
                fileExist = false;
            }

        }
        return null;
        
    }
    
}