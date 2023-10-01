package Function;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import ADTMatrix.Matrix;
import ADTMatrix.OutputMatrix;

public class SPL {
    public static void inversSPL (Matrix MatrixSPL){
        Matrix A;
        Matrix B;
        Matrix x;
        Matrix matrixInvers;
        int i, j;
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        // Konstruktor matriks A dan B
        A = new Matrix(MatrixSPL.row, MatrixSPL.row);
        B = new Matrix(MatrixSPL.row, 1);
        // Memisahkan A dan B yang terinput sebagai augmented matrix
        for (i = 0; i < A.getRowLength(); i++){
            for (j = 0; j < MatrixSPL.getColLength(); j++){
                if (j != MatrixSPL.getColLength()-1){
                        A.setElmt(i, j, MatrixSPL.getElmt(i, j));
                }
                else if (j == MatrixSPL.getColLength()-1){
                    B.setElmt(i, 0, MatrixSPL.getElmt(i, j));
                }
            }
        }

        // Jika determinan 0, maka SPL tidak dapat diselesaikan dengan metode matriks balikan
        if ((MatrixSPL.getRowLength() != MatrixSPL.getColLength()-1) || Determinan.detKofaktor(A) == 0){
            System.out.println("SPL tidak dapat diselesaikan dengan metode invers.");
            // output file
            int pil3 = OutputMatrix.printMenuOutput();
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
                    file.write("SPL tidak dapat diselesaikan dengan metode invers.");
                    file.close();
                }
                catch(IOException err){
                    err.printStackTrace();
                }
            }
        } else {
            // Konstruktor x
            x = new Matrix(MatrixSPL.row, 1);
            // x = (invers A) * B
            matrixInvers = Invers.inversIdentitas(A);
            x = Matrix.multiplyMatrix(matrixInvers, B);
            // Mengeluarkan output x
            for(i = 0; i < x.getRowLength(); i++){
                System.out.printf("x%d = %.4f\n", (i+1), (x.getElmt(i, 0)));
            }
            // output file
            int pil3 = OutputMatrix.printMenuOutput();
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
                    for(i = 0; i < x.getRowLength(); i++){
                        if (i != x.row - 1){
                            double temp = x.getElmt(i, 0);
                            file.write("x"+Integer.toString(i+1)+" = " + Double.toString(temp)+"\n");
                        }
                        else{
                            file.write("x"+Integer.toString(i+1)+" = "+Double.toString(x.getElmt(i, 0)));
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

    public static void cramerSPL (Matrix mCramer){
        Matrix A;
        Matrix B;
        Matrix x;
        int i, j;
        double det;
        Matrix mTemp;
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));

        // Input A dan B (Memisahkan augmented matrix)
        A = new Matrix(mCramer.getRowLength(), mCramer.getColLength()-1);
        B = new Matrix(mCramer.row, 1);
        for (i = 0; i < mCramer.getRowLength(); i++){
            for (j = 0; j < mCramer.getColLength(); j++){
                if (j != mCramer.getColLength()-1){
                    A.setElmt(i, j, mCramer.getElmt(i, j));
                }
                else{
                    B.setElmt(i, 0, mCramer.getElmt(i, j));
                }
                
            }
        }
        
        // Jika determinan 0, maka SPL tidak dapat diselesaikan dengan metode cramer.
        if ((mCramer.getRowLength() != mCramer.getColLength()-1) || Determinan.detKofaktor(A) == 0){
            System.out.print("SPL tidak dapat diselesaikan dengan metode cramer.");
            // output file
            int pil3 = OutputMatrix.printMenuOutput();
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
                    file.write("SPL tidak dapat diselesaikan dengan metode cramer.");
                    file.close();
                }
                catch(IOException err){
                    err.printStackTrace();
                }
            }
        } else {
            // Konstruktor x
            x = new Matrix(mCramer.getRowLength(), 1);
            // Determinan
            det = Determinan.detKofaktor(A);
            mTemp = A;
            // Mengloop sehingga setiap kolom diubah dengan B kemudian dicari nilai x[kolom]nya.
            for (j = 0; j < A.row; j++){
                for (i = 0; i < A.row; i++){
                    mTemp.setElmt(i, j, mCramer.getElmt(i, A.row));
                }
                double solution = Determinan.detKofaktor(mTemp)/det;
                System.out.printf("x%d = %.4f\n", (j+1), (solution));
                for (i = 0; i < A.row; i++){
                    mTemp.setElmt(i, j, mCramer.getElmt(i, j));
                }
            }
            // output file
            int pil3 = OutputMatrix.printMenuOutput();
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
                    for (j = 0; j < A.row; j++){
                        for (i = 0; i < A.row; i++){
                            mTemp.setElmt(i, j, mCramer.getElmt(i, A.row));
                        }
                        double solution = Determinan.detKofaktor(mTemp)/det;
                        file.write("x"+Integer.toString(j+1)+" = "+Double.toString(solution)+"\n");
                        for (i = 0; i < A.row; i++){
                            mTemp.setElmt(i, j, mCramer.getElmt(i, j));
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

    public static void gaussSPL (Matrix Mgauss) {
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        //Melakukan eliminasi gauss 
        Mgauss = Matrix.gaussElimination(Mgauss);
        double X[] = new double[Mgauss.getRowLength()];
        //Menganalisis matriks hasil eliminasi gauss apakah memiliki solusi tidak ada, unik atau banyak
        int solutionType = Matrix.SolutionType(Mgauss);
        int pil3;
        Matrix.backSubstitution(Mgauss, X);

        if (solutionType == 0) {
            System.out.println("Solusi tidak ada.");
            pil3 = OutputMatrix.printMenuOutput();
            //Mencetak SPL dalam bentuk file
            if(pil3 == 1) {
                String nameFile = "";
                System.out.println("Masukkan nama file: ");
                try {
                    nameFile = inputFile.readLine();
                    String path = "Test/" + nameFile;
                }
                catch (IOException err) {
                    err.printStackTrace();
                }
                try {
                    FileWriter file = new FileWriter("Test/" + nameFile);
                    file.write("Solusi tidak ada.");
                    file.close();
                }
                catch(IOException err) {
                    err.printStackTrace();
                }
            }
        } else if (solutionType == 1) {
            System.out.println("Solusi tunggal:");
            for (int i = 0; i < Mgauss.getRowLength(); i++) {
                System.out.printf("X[%d] = %.4f%n", i + 1, X[i]);
            }
            pil3 = OutputMatrix.printMenuOutput();
            //Mencetak SPL dalam bentuk file
            if(pil3 == 1) {
                String nameFile = "";
                System.out.println("Masukkan nama file: ");
                try {
                    nameFile = inputFile.readLine();
                    String path = "Test/" + nameFile;
                }
                catch (IOException err) {
                    err.printStackTrace();
                }
                try {
                    FileWriter file = new FileWriter("Test/" + nameFile);
                    file.write("Solusi tunggal:\n");
                    for (int i = 0; i < Mgauss.getRowLength(); i++) {
                        String tempString = Double.toString(X[i]);
                        String tempIndex = Integer.toString(i+1);
                        if (i == Mgauss.getRowLength() - 1){
                            file.write("X" + tempIndex + " = " + tempString);
                        } else {
                            file.write("X" + tempIndex + " = " + tempString+ "\n");
                        }
                    }
                    file.close();
                }
                catch(IOException err) {
                    err.printStackTrace();
                }
            
            } else {
                //Jika solusi berupa parametrik maka memanggil fungsi solusi parametrik
                System.out.println("Solusi banyak (parametrik):");
                Matrix.solveManySolution(Mgauss);
            }
        }
    }

    public static void gaussJordanSPL (Matrix Mgajo) {
        //Melakukan eliminasi gauss jordan
        Mgajo = Matrix.gaussJordanElimination(Mgajo);
        int pil3;
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        int solutionType = Matrix.SolutionType(Mgajo);
        //Menganalisis matriks hasil eliminasi gauss apakah memiliki solusi tidak ada, unik atau banyak
        if (solutionType == 0) {
            System.out.println("Solusi tidak ada.");
            pil3 = OutputMatrix.printMenuOutput();
            if(pil3 == 1) {
                //Mencetak out SPL ke dalam bentuk file
                String nameFile = "";
                System.out.println("Masukkan nama file: ");
                try {
                    nameFile = inputFile.readLine();
                    String path = "Test/" + nameFile;
                }
                catch (IOException err) {
                    err.printStackTrace();
                }
                try {
                    FileWriter file = new FileWriter("Test/" + nameFile);
                    file.write("Solusi tidak ada.");
                    file.close();
                }
                catch(IOException err) {
                    err.printStackTrace();
                }
            }
        } else if (solutionType == 1) {
            System.out.println("Solusi tunggal:");
            for (int i = 0; i < Mgajo.getRowLength(); i++) {
                System.out.printf("X[%d] = %.4f%n", i + 1, Mgajo.matrix[i][Mgajo.getColLength() - 1]);
            }
            pil3 = OutputMatrix.printMenuOutput();
            if(pil3 == 1) {
                 //Mencetak out SPL ke dalam bentuk file
                String nameFile = "";
                System.out.println("Masukkan nama file: ");
                try {
                    nameFile = inputFile.readLine();
                    String path = "Test/" + nameFile;
                }
                catch (IOException err) {
                    err.printStackTrace();
                }
                try {
                    FileWriter file = new FileWriter("Test/" + nameFile);
                    file.write("Solusi tunggal:\n");
                    for (int i = 0; i < Mgajo.getRowLength(); i++) {
                        String tempString = Double.toString(Mgajo.matrix[i][Mgajo.getColLength() - 1]);
                        String tempIndex = Integer.toString(i + 1);
                        if (i == Mgajo.getRowLength() - 1){
                            file.write("X" + tempIndex + " = " + tempString);
                        } else {
                            file.write("X" + tempIndex + " = " + tempString+ "\n");
                        }
                    }
                    file.close();
                }
                catch(IOException err) {
                    err.printStackTrace();
                }
            } else {
                System.out.println("Solusi banyak (parametrik):");
                //Jika solusi berupa parametrik maka memanggil fungsi solusi parametrik
                Matrix.solveManySolution(Mgajo);
            }
        }
    }
}
