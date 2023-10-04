package ADTMatrix;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import Function.Determinan;

public class OutputMatrix {
    public static void printMatrix(Matrix matrix) {
        int i, j;
        for (i = 0; i < matrix.row; i++) {
            for (j = 0; j < matrix.col; j++) {
                System.out.printf("%.4f ",matrix.getElmt(i, j));
            }
            System.out.println();
        }
    }
    public static int printMenuOutput(){
        Scanner input = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("------------------------------------------------------------");
        System.out.println("                       MENU OUTPUT                          ");
        System.out.println("------------------------------------------------------------");
        System.out.println("1. Buat Output File");
        System.out.println("2. Tidak Menghasilkan Output File");
        System.out.println("------------------------------------------------------------");
        int pil3 = input.nextInt();
        while (pil3 < 1 || pil3 > 2){
            pil3 = input.nextInt();
        }
        return pil3;
    }
     public static void OutputFileDeterminan (double det){
        Scanner input = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        int pil3 = printMenuOutput();
        if (pil3 == 1){
            String newfileName = "";
            System.out.print("Masukkan nama file: ");
            try{
                newfileName = inputFile.readLine();
                String path = "Test/" + newfileName;
            }
            catch(IOException err){
                err.printStackTrace();
            }
            try{
                FileWriter file = new FileWriter("Test/" + newfileName);
                file.write("Determinan: "+ det);
                file.close();
            }
            catch(IOException err){
                err.printStackTrace();
            }
        }
    }
    public static void OutputFileInvers(Matrix m1){
        Scanner input = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        int pil3 = printMenuOutput();
            while (pil3 < 1 || pil3 > 2){
                pil3 = input.nextInt();
            }
        if (pil3 == 1){
            String newfileName = "";
            System.out.print("Masukkan nama file: ");
            try{
                newfileName = inputFile.readLine();
                String path = "Test/" + newfileName;
            }
            catch(IOException err){
                err.printStackTrace();
            }
            try{
                FileWriter file = new FileWriter("Test/" + newfileName);
                int i, j;
                Matrix m2 = new Matrix (m1.row, m1.col);
                for (i = 0; i < m1.row; i++){
                    for (j = 0; j < m1.col; j++){
                        m2.setElmt(i, j, m1.getElmt(i, j));
                    }
                }
                if (Determinan.detOBE(m2) == 0){
                    file.write("Invers tidak ada.");
                }
                else{
                    for (i = 0; i < m1.row; i++){
                        for (j = 0; j < m1.col; j++){
                            String tempString = Double.toString(m1.getElmt(i, j));
                            file.write(tempString + " ");
                        }
                        file.write("\n");
                    }
                }
                file.close();
            }
            catch(IOException err){
                err.printStackTrace();
            }
        }
    }
}