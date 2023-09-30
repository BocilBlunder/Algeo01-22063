package Function;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import ADTMatrix.*;

public class Bicubic {
    public static void interpolasiBicubic (Matrix m){
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        int i, j;
        Matrix m1;
        Matrix m2;

        m1 = new Matrix(4, 4);
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                m1.setElmt(i, j, m.matrix[i][j]);
            }
        }

        m2 = new Matrix(1, 2);
        for(j = 0; j < 2; j++){
            m2.setElmt(0, j, m.matrix[4][j]);
        }

        Matrix tempX = new Matrix(16, 16);
        Matrix tempY = new Matrix(16, 1);
        Matrix X, A;

        int indeks = 0;
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                tempY.setElmt(indeks, 0, m1.getElmt(i,j));
                indeks++;
            }
        }
        int x, y;
        int row = 0;
        for (y = -1; y < 3; y++) {
            for (x = -1; x < 3; x++) {
                int col = 0;
                for (j = 0; j < 4; j++) {
                    for (i = 0; i < 4; i++) {
                        tempX.setElmt(row, col, ((float) Math.pow(x, i) * (float) Math.pow(y, j)));
                        col++; 
                    }
                }
                row++;
            }
        }    

        X = Invers.inversIdentitas(tempX);
        A = Matrix.multiplyMatrix(X, tempY);

        Double hasil = 0.0;
        indeks = 0;
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                hasil += A.getElmt(indeks, 0) * Math.pow(m2.matrix[0][0], i) * Math.pow(m2.matrix[0][1], j);
                indeks++;
            }
        }
        System.out.println("f(" + m2.matrix[0][0] + "," + m2.matrix[0][1]+ ") = " + hasil);
        int pil3 = OutputMatrix.printMenuOutput();
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
                file.write("f(" + Double.toString(m2.matrix[0][0]) + "," + Double.toString(m2.matrix[0][1])+ ") = " + Double.toString(hasil));
                file.close();
            }
            catch(IOException err) {
                err.printStackTrace();
            }
        }
    }
}