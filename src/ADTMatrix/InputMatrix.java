package ADTMatrix;
import java.util.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputMatrix{
    public static Scanner input = new Scanner(System.in);

    // Input dari Keyboard
    public static double[][] readMatrixKeyboard1(){
        int i,j;
        double [][] matrixA, matrixB, m;
        int rows, cols;

        // Input Jumlah Baris dan Kolom
        System.out.print("Masukkan jumlah baris: ");
        rows = input.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        cols = input.nextInt();

        // Membuat Matriks A
        matrixA = new double[rows][cols];
        System.out.println("Masukkan elemen matriks A: ");
        for(i = 0; i < rows; i++){
            for(j = 0; j < cols; j++){
                matrixA [i][j] = input.nextDouble();
            }
        }

        // Membuat Matriks B
        matrixB = new double[rows][1];
        System.out.println("Masukkan elemen matriks B: ");
        for(i = 0; i < rows; i++){
            for(j = 0; j < 1; j++){
                matrixB [i][j] = input.nextDouble();
            }
        }
        
        // Menggabungkan Kedua Matriks
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

        // Input Jumlah Baris dan Kolom
        System.out.print("Masukkan jumlah baris: ");
        rows = input.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        cols = input.nextInt();
        
        // Membuat Matriks 
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
        double x;

        // Input Derajat Polinom
        System.out.print("Masukkan derajat polinom (n): ");
        n = input.nextInt();

        // Membuat Matriks dengan Titik X dan Y
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

        // Input Jumlah Peubah dan Sampel
        System.out.print("Masukkan jumlah peubah (x): ");
        n = input.nextInt();
        System.out.print("Masukkan jumlah sampel (m): ");
        m = input.nextInt();

        // Membuat Matriks dengan Titik X dan Y
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
        int i;
        Matrix matrix;
    
        // Input Nama File
        System.out.print("Masukkan nama file: ");
        String file = input.nextLine();
        String path = "test/Input/" + file;
        System.out.println(path);

        // Mencari File
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String s;
            String[] x;
            double[] y;
            double[][] mTemp;

            // Membaca Baris 1
            s = br.readLine();
            
            // Mengubah Baris 1 Menjadi Array of String
            x = s.split("\\s+");
            y = new double[x.length];

            // Mengubah Array menjadi Array of Double
            for(i = 0; i < y.length; i++){
                y[i] = Double.parseDouble(x[i]);
            }

            // Memindahkan Array of Double ke Matriks
            mTemp = new double[1][x.length];
            for(i = 0; i < y.length; i++){
                mTemp[0][i] = y[i];
            }
            // Membuat matriks 
            matrix = new Matrix(mTemp, 1, y.length);

            while((s = br.readLine()) != null){
                x = s.split("\\s+");
                y = new double [x.length];
                for(i = 0; i < x.length; i++){
                    y[i] = Double.parseDouble(x[i]);
                }
                if(x.length < matrix.col){
                    double[] z = new double[matrix.col];
                    for(i = 0; i < matrix.col;i++){
                        if(i >= y.length){
                            z[i] = 0;
                        }else{
                            z[i] = y[i];
                        }
                    }
                    matrix = Matrix.addRow(matrix, z);
                }else{
                    matrix = Matrix.addRow(matrix, y);
                }
            }
            br.close();
            return matrix;
            
        // Ketika File Tidak Ditemukan
        }catch(Exception ex){
            System.out.println("File not found");
            System.out.println("Returning a matrix with no value");
            matrix = new Matrix(1, 1);
            return matrix;
        }
    }
}