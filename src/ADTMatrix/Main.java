package ADTMatrix;
import java.io.*;
import java.util.*;
import Function.*;
public class Main{
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        int pilihan, pil1, pil2;
        System.out.println("            Selamat Datang di Kalkulator Matriks!           ");
        System.out.println("------------------------------------------------------------");
        System.out.println("                           MENU                             ");
        System.out.println("------------------------------------------------------------");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Linier Berganda");
        System.out.println("7. Keluar");
        System.out.println("------------------------------------------------------------");
        pilihan = input.nextInt();
        while (pilihan < 1 || pilihan >7 ){
            pilihan = input.nextInt();
        }
        if (pilihan == 1){
            System.out.println("------------------------------------------------------------");
            System.out.println("                      MENU METODE                           ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Metode Eliminasi Gauss");
            System.out.println("2. Metode Eliminasi Gauss-Jordan");
            System.out.println("3. Metode Matriks Balikan");
            System.out.println("4. Kaidah Cramer");
            System.out.println("------------------------------------------------------------");
            pil1 = input.nextInt();
            while (pil1 < 1 || pil1 > 4){
                pil1 = input.nextInt();
            }
            System.out.println("------------------------------------------------------------");
            System.out.println("------------------------------------------------------------");
            System.out.println("                      MENU INPUTAN                          ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            System.out.println("------------------------------------------------------------");
            pil2 = input.nextInt();
            while (pil2 < 1 || pil2 > 2){
                pil2 = input.nextInt();
            }
            if (pil1 == 1){
                if (pil2 == 1){
                    double[][]m = InputMatrix.readMatrixKeyboard1();
                    Matrix m1 = new Matrix(m, m.length, m[0].length);
                    SPL.gaussSPL(m1);
                }
                else if (pil2 == 2){
                    Matrix m1 = InputMatrix.readMatrixFile();
                    SPL.gaussSPL(m1);
                }
            }
            else if (pil1 == 2){
                if (pil2 == 1){
                    double[][]m = InputMatrix.readMatrixKeyboard1();
                    Matrix m1 = new Matrix(m, m.length, m[0].length);
                    SPL.gaussJordanSPL(m1);
                }
                else if (pil2 == 2){
                    Matrix m1 = InputMatrix.readMatrixFile();
                    SPL.gaussJordanSPL(m1);
                }
            }
            else if (pil1 == 3){
                if (pil2 == 1){
                    double[][]m = InputMatrix.readMatrixKeyboard1();
                    Matrix m1 = new Matrix(m, m.length, m[0].length);
                    SPL.inversSPL(m1);
                }
                else if (pil2 == 2){
                    Matrix m1 = InputMatrix.readMatrixFile();
                    SPL.inversSPL(m1);
                }   
            }
            else if (pil1 == 4){
                if (pil2 == 1){
                    double[][]m = InputMatrix.readMatrixKeyboard1();
                    Matrix m1 = new Matrix(m, m.length, m[0].length);
                    SPL.cramerSPL(m1);
                }
                else if (pil2 == 2){
                    Matrix m1 = InputMatrix.readMatrixFile();
                    SPL.cramerSPL(m1);
                }   
            }
        }
        if (pilihan == 2){
            System.out.println("------------------------------------------------------------");
            System.out.println("                      MENU METODE                           ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Metode Kofaktor");
            System.out.println("2. Metode OBE");
            System.out.println("------------------------------------------------------------");
            pil1 = input.nextInt();
            System.out.println("------------------------------------------------------------");
            System.out.println("------------------------------------------------------------");
            System.out.println("                      MENU INPUTAN                          ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            System.out.println("------------------------------------------------------------");
            pil2 = input.nextInt();
            if (pil1 == 1){
                if (pil2 == 1){
                    double[][]m = InputMatrix.readMatrixKeyboard2();
                    Matrix m1 = new Matrix(m, m.length, m[0].length);
                    System.out.printf("%.4f\n", Determinan.detKofaktor(m1));
                    OutputMatrix.OutputFileDeterminan(Determinan.detKofaktor(m1));
                }
                else if (pil2 == 2){
                    Matrix m1 = InputMatrix.readMatrixFile();
                    System.out.printf("%.4f\n", Determinan.detKofaktor(m1));
                    OutputMatrix.OutputFileDeterminan(Determinan.detKofaktor(m1));
                }
            }
            else if (pil1 == 2){
                if (pil2 == 1){
                    double[][]m = InputMatrix.readMatrixKeyboard2();
                    Matrix m1 = new Matrix(m, m.length, m[0].length);
                    double hasil = (Determinan.detOBE(m1));
                    System.out.printf("%.4f\n", hasil);
                    OutputMatrix.OutputFileDeterminan(hasil);
                }
                else if (pil2 == 2){
                    Matrix m1 = InputMatrix.readMatrixFile();
                    double hasil = (Determinan.detOBE(m1));
                    System.out.printf("%.4f\n", hasil);
                    OutputMatrix.OutputFileDeterminan(hasil);
                }
            }
        }
        if (pilihan == 3){
            System.out.println("------------------------------------------------------------");
            System.out.println("                      MENU METODE                           ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Metode Adjoin");
            System.out.println("2. Metode Matriks Identitas");
            System.out.println("------------------------------------------------------------");
            pil1 = input.nextInt();
            System.out.println("------------------------------------------------------------");
            System.out.println("------------------------------------------------------------");
            System.out.println("                      MENU INPUTAN                          ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            System.out.println("------------------------------------------------------------");
            pil2 = input.nextInt();
            if (pil1 == 1){
                if (pil2 == 1){
                    double[][]m = InputMatrix.readMatrixKeyboard2();
                    Matrix m1 = new Matrix(m, m.length, m[0].length);
                    Matrix hasil = (Invers.inversAdjoin(m1));
                    OutputMatrix.printMatrix(hasil);
                    OutputMatrix.OutputFileInvers(hasil);
                }
                else if (pil2 == 2){
                    Matrix m1 = InputMatrix.readMatrixFile();
                    Matrix hasil = (Invers.inversAdjoin(m1));
                    OutputMatrix.printMatrix(hasil);
                    OutputMatrix.OutputFileInvers(hasil);
                }
            }
            else if (pil1 == 2){
                if (pil2 == 1){
                    double[][]m = InputMatrix.readMatrixKeyboard2();
                    Matrix m1 = new Matrix(m, m.length, m[0].length);
                    Matrix m2 = new Matrix (m, m.length, m[0].length);
                    int i, j;
                    for (i = 0; i < m1.row; i++){
                        for (j = 0; j < m1.col; j++){
                            m2.setElmt(i, j, m1.getElmt(i, j));
                        }
                    }
                    Matrix hasil = Invers.inversIdentitas(m1);
                    if (Determinan.detOBE(m2) != 0){ 
                        OutputMatrix.printMatrix(hasil);
                    }
                    else{
                        System.out.println("Invers tidak ada.");
                    }
                    OutputMatrix.OutputFileInvers(hasil);  
                }
                else if (pil2 == 2){
                    Matrix m1 = InputMatrix.readMatrixFile();
                    Matrix m2 = new Matrix (m1.row, m1.col);
                    int i, j;
                    for (i = 0; i < m1.row; i++){
                        for (j = 0; j < m1.col; j++){
                            m2.setElmt(i, j, m1.getElmt(i, j));
                        }
                    }
                    Matrix hasil = Invers.inversIdentitas(m1);
                    if (Determinan.detOBE(m2) != 0){
                        OutputMatrix.printMatrix(hasil);
                    }
                    else{
                        System.out.println("Invers tidak ada.");
                    }
                    OutputMatrix.OutputFileInvers(hasil);  
                }
            }
        }
        if (pilihan == 4){
            System.out.println("------------------------------------------------------------");
            System.out.println("                      MENU INPUTAN                          ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            System.out.println("------------------------------------------------------------");
            pil2 = input.nextInt();
            while (pil2 < 1 || pil2 > 2){
                pil2 = input.nextInt();
            }
            if (pil2 == 1){
                double[][]m = InputMatrix.readInterpolasiKeyboard();
                Matrix m1 = new Matrix(m, m.length, m[0].length);
                Interpolasi.interpolasiPolinomialKeyboard(m1);
            }
            else if (pil2 == 2){
                Matrix m = InputMatrix.readMatrixFile();
                Interpolasi.interpolasiPolinomialFile(m);
            }
        }
        if (pilihan == 5){
            Matrix m = InputMatrix.readMatrixFile();
            Bikubic.interpolasiBicubic(m);
        }
        if (pilihan == 6){
            System.out.println("------------------------------------------------------------");
            System.out.println("                      MENU INPUTAN                          ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            System.out.println("------------------------------------------------------------");
            pil2 = input.nextInt();
            while (pil2 < 1 || pil2 > 2){
                pil2 = input.nextInt();
            }
            if (pil2 == 1){
                double[][]m = InputMatrix.readRegresiKeyboard();
                Matrix m1 = new Matrix(m, m.length, m[0].length);
                Regresi.regresiLinearKeyboard(m1);
            }
            else if (pil2 == 2){
                Matrix m = InputMatrix.readMatrixFile();
                Regresi.regresiLinearFile(m);
            }
        }
        if (pilihan == 7){
            System.out.println("------------------------------------------------------------");
            System.out.println("                       Terima Kasih!                        ");
            System.out.println("                      OPET BROT BROT                        ");
            System.out.println("               13522063 | 13522070 | 13522113                ");
            System.out.println("------------------------------------------------------------");
        }
    }
}