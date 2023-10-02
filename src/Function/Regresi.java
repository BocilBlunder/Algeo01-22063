package Function;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import ADTMatrix.*;

public class Regresi {
    public static void regresiLinearKeyboard(Matrix m){
        int i, j;
        double temp = 0;
        double result = 0;
        double sum = 0;
        Matrix mTemp;
        double [] x;
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));

        x = new double [m.col - 1];
        //Input nilai-nilai xk yang ingin ditaksir
        System.out.println("Masukkan nilai x yang ingin ditaksir: ");
        for(i = 0; i < x.length; i++){
            x[i] = InputMatrix.input.nextDouble();
        }
        //Membuat matriks baru semacam SPL
        mTemp = new Matrix(m.col, m.col + 1);

        for(i = 0 ; i < mTemp.row ; i++){
            for(j = 0 ; j < mTemp.col ; j++){
                if(i == 0 && j == 0){
                    temp = m.row;
                    
                }
                else if (i == 0 && j > 0){
                    temp = Matrix.sumCol(m, j - 1);
                }
                else if (j == 0 && i > 0){
                    temp = Matrix.sumCol(m, i - 1);
                }
                else if (i > 0 && j > 0) {
                    temp = Matrix.sumMultiplyCol(m, i-1, j-1);
                }
                mTemp.setElmt(i, j, temp);
            }
        }
        
        mTemp = Matrix.gaussElimination(mTemp);
        double[] m1 = new double [mTemp.getRowLength()];
        Matrix.backSubstitution(mTemp, m1); 

        System.out.print("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            if (i == 0){
                result = m1[i];
                if (m1[i] > 0){
                    System.out.printf("%.4f ", m1[i]);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4f ", m1[i]);
                }
            } else if ( i > 0 && i < mTemp.row - 1){
                result = m1[i] * x[i - 1];
                if (m1[i] > 0){
                    System.out.printf("+ %.4fx%d ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx%d ", m1[i], i);
                }
            } else if (i == mTemp.row - 1){
                result = m1[i] * x[i - 1];
                if (m1[i] > 0){
                    System.out.printf("+ %.4fx%d, ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx%d, ", m1[i], i);
                }
            }
            sum += result;
        }
        System.out.printf("f(xk) = %.4f\n", sum);

        int pil3 = OutputMatrix.printMenuOutput();
        if (pil3 == 1){
            //Mencetak ouput ke dalam bentuk file
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
                System.out.print("f(x) = ");
                for (i = 0; i < mTemp.row; i++) {
                    if (i == 0){
                        result = m1[i];
                        if (m1[i] > 0){
                            file.write(Double.toString(m1[i]));
                        } else {
                            m1[i] *= -1;
                            file.write("- "+Double.toString(m1[i]));
                        }
                    } else if (i > 0 && i < mTemp.row - 1){
                        result = m1[i] * x[i - 1];
                        if (m1[i] > 0){
                            file.write("+ "+ Double.toString(m1[i])+"x"+Integer.toString(i));
                        } else {
                            m1[i] *= -1;
                            file.write("- "+ Double.toString(m1[i])+"x"+Integer.toString(i));
                        }
                    } else if (i == mTemp.row - 1){
                        result = m1[i] * x[i - 1];
                        if (m1[i] > 0){
                            file.write("+ "+Double.toString(m1[i])+"x^"+Integer.toString(i));
                        } else {
                            m1[i] *= -1;
                            file.write("- "+Double.toString(m1[i])+"x^"+Integer.toString(i));
                        }
                    }
                    sum += result;
                }
                file.write("f(xk) = "+Double.toString(sum));
                file.close();
            }
            catch(IOException err) {
                err.printStackTrace();
            }
        }
    }

    public static void regresiLinearFile (Matrix m){
        int i, j;
        double temp = 0;
        double result = 0;
        double sum = 0;
        Matrix mTemp;
        Matrix m1;
        double[] m2;
        double[] x;

        // Membuat Matriks
        m1 = new Matrix(m.row - 1, m.col);
        for(i = 0; i < m.row - 1; i++){
            for(j = 0; j < m.col; j++){
                m1.setElmt(i, j, m.matrix[i][j]);
            }
        }

        m2 = new double[m.col - 1];
        for(i = 0; i < m2.length; i++){ 
            m2[i] = m.getElmt(m.row - 1, i);
        }

        //Membuat matriks baru semacam SPL
        mTemp = new Matrix(m.col, m.col + 1);

        for(i = 0 ; i < mTemp.row  ; i++){
            for(j = 0 ; j < mTemp.col ; j++){
                if(i == 0 && j == 0){
                    temp = m1.row;
                }
                else if (i == 0 && j > 0){
                    temp = Matrix.sumCol(m1, j - 1);
                }
                else if (j == 0 && i > 0){
                    temp = Matrix.sumCol(m1, i - 1);
                }
                else if (i > 0 && j > 0) {
                    temp = Matrix.sumMultiplyCol(m1, i-1, j-1);
                }
                mTemp.setElmt(i, j, temp);
            }
        }
        
        mTemp = Matrix.gaussElimination(mTemp);
        x = new double [mTemp.getRowLength()];
        Matrix.backSubstitution(mTemp, x); 

        System.out.print("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            if (i == 0){
                result = x[i];
                if (x[i] > 0){
                    System.out.printf("%.4f ", x[i]);
                } else {
                    x[i] *= -1;
                    System.out.printf("- %.4f ", x[i]);
                }
            } else if ( i > 0 && i < mTemp.row - 1){
                result = x[i] * m2[i - 1];
                if (x[i] > 0){
                    System.out.printf("+ %.4fx%d ", x[i], i);
                } else {
                    x[i] *= -1;
                    System.out.printf("- %.4fx%d ", x[i], i);
                }
            } else if (i == mTemp.row - 1){
                result = x[i] * m2[i - 1];
                if (x[i] > 0){
                    System.out.printf("+ %.4fx%d, ", x[i], i);
                } else {
                    x[i] *= -1;
                    System.out.printf("- %.4fx%d, ", x[i], i);
                }
            }
            sum += result;
        }
        System.out.printf("f(xk) = %.4f", sum);
    }
 }

