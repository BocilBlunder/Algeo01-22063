package ADTMatrix;
import java.util.*;
import java.io.*;

public class InputMatrix{
    // Input dari Keyboard
    public static Scanner input = new Scanner(System.in);
    public static double[][] readMatrixKeyboard(){
        int i,j;
        double [][] matrix;
        int rows, cols;

        System.out.print("Masukkan jumlah baris: ");
        rows = input.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        cols = input.nextInt();

        matrix = new double[rows][cols];
        
        System.out.println("Masukkan elemen A: ");
        for(i = 0; i < rows; i++){
            for(j = 0; j < cols; j++){
                matrix[i][j] = input.nextDouble();
            }
        }
        return matrix;

    }
    // Input dari File
    /*public Matrix readMatrixFile(){
        Scanner input = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        Matrix m;
        Boolean fileExist;
        int i, j;

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
            }
            catch(FileNotFoundException err){
                err.printStackTrace();
                fileExist = false;
            }

        }
        m = new Matrix(matrix, rows, cols);
        return m;
    }*/
}


