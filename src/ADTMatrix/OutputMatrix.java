package ADTMatrix;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import Function.Determinan;
public class OutputMatrix {
    // Menampilkan Matriks pada Layar
    public static void printMatrix(Matrix matrix) {
        int i, j;
        for (i = 0; i < matrix.row; i++) {
            for (j = 0; j < matrix.col; j++) {
                System.out.printf("%.4f ",matrix.getElmt(i, j));
            }
            System.out.println();
        }
    }

    // Menampilkan Menu Output
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
            System.out.println("Masukan tidak valid! Silahkan ulangi inpuran pilihan.");
            pil3 = input.nextInt();
        }
        return pil3;
    }

    // Memasukkan Hasil Det ke File
    public static void OutputFileDeterminan (double det){
        Scanner input = new Scanner(System.in);
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        int pil3 = printMenuOutput();
        if (pil3 == 1){
            String newfileName = "";
            System.out.print("Masukkan nama file: ");
            try{
                newfileName = inputFile.readLine();
                String path = "test/Output/" + newfileName;
            }
            catch(IOException err){
                err.printStackTrace();
            }
            try{
                FileWriter file = new FileWriter("test/Output/" + newfileName);
                file.write("Determinan: "+ String.format("%.4f", det));
                file.close();
            }
            catch(IOException err){
                err.printStackTrace();
            }
        }
    }

    // Memasukkan Hasil Invers ke File
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
                String path = "test/Output/" + newfileName;
            }
            catch(IOException err){
                err.printStackTrace();
            }
            try{
                FileWriter file = new FileWriter("test/Output/" + newfileName);
                int i, j;
                Matrix m2 = new Matrix (m1.row, m1.col);
                for (i = 0; i < m1.row; i++){
                    for (j = 0; j < m1.col; j++){
                        m2.setElmt(i, j, m1.getElmt(i, j));
                    }
                }
                if (m1.getElmt(0, 0) == Double.POSITIVE_INFINITY || m1.getElmt(0, 0) == Double.NEGATIVE_INFINITY){
                    file.write("Invers tidak ada.");
                    file.close();
                }
                else{
                    for (i = 0; i < m1.row; i++){
                        for (j = 0; j < m1.col; j++){
                            String tempString = String.format("%.4f", m1.getElmt(i, j));
                            file.write(tempString + " ");
                        }
                        file.write("\n");
                    }
                    file.close();
                }
            }
            catch(IOException err){
                err.printStackTrace();
            }
        }
    }
}