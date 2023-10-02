package Function;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import ADTMatrix.*;

public class Bicubic {
    public static Matrix XforF(){
        int i, j;
        Matrix F;
        F = new Matrix(4, 16);
        int row = 0;
        int col;
        int x, y;
        // mengisi nilai matriks X berdasarkan rumus
        for (row = 0; row < 4; row++){
            if (row == 0){
                x = 0;
                y = 0;
            }
            else if (row == 2){
                x = 1;
                y = 0;
            }
            else if (row == 1){
                x = 0;
                y = 1;
            }
            else{
                x = 1;
                y = 1;
            }
            i = 0;
            j = 0;
            for (col = 0; col < 16; col++){
                F.setElmt(row, col, Math.pow(x, i) * Math.pow(y, j));
                j++;
                if (j == 4){
                    j = 0;
                    i++;
                }
            }
        }
        return F;
    }

    public static Matrix XforFx(){
        int x, y;
        int i, j;
        Matrix Fx;
        Fx = new Matrix(4, 16);
        int row = 0;
        int col;
        // mengisi nilai matriks X berdasarkan rumus
        for (row = 0; row < 4; row++){
            if (row == 0){
                x = 0;
                y = 0;
            }
            else if (row == 1){
                x = 1;
                y = 0;
            }
            else if (row == 2){
                x = 0;
                y = 1;
            }
            else{
                x = 1;
                y = 1;
            }
            i = 0;
            j = 0;

            for (col = 0; col < 16; col++){
                if (i == 0){
                    Fx.setElmt(row, col, 0.0000);
                }
                else{
                    Fx.setElmt(row, col, Math.pow(x, i-1) * Math.pow(y, j) * i);
                }
                i++;
                if (i > 3){
                    i = 0;
                    j++;
                }
            }
        }
        return Fx;
    }

    public static Matrix XforFy(){
        int x, y;
        int i, j;
        Matrix Fy;
        Fy = new Matrix(4, 16);
        int row = 0;
        int col;
        // mengisi nilai matriks X berdasarkan rumus
        for (row = 0; row < 4; row++){
            if (row == 0){
                x = 0;
                y = 0;
            }
            else if (row == 1){
                x = 1;
                y = 0;
            }
            else if (row == 2){
                x = 0;
                y = 1;
            }
            else{
                x = 1;
                y = 1;
            }
            i = 0;
            j = 0;

            for (col = 0; col < 16; col++){
                if (j == 0){
                    Fy.setElmt(row, col, 0.0000);
                }
                else{
                    Fy.setElmt(row, col, Math.pow(x, i) * Math.pow(y, j-1) * j);
                }
                i++;
                if (i > 3){
                    i = 0;
                    j++;
                }
            }
        }
        return Fy;
    }
    public static Matrix XforFxy(){
        int x, y;
        int i, j;
        Matrix Fxy;
        Fxy = new Matrix(4, 16);
        int row = 0;
        int col;
        // mengisi nilai matriks X berdasarkan rumus
        for (row = 0; row < 4; row++){
            if (row == 0){
                x = 0;
                y = 0;
            }
            else if (row == 1){
                x = 1;
                y = 0;
            }
            else if (row == 2){
                x = 0;
                y = 1;
            }
            else{
                x = 1;
                y = 1;
            }
            i = 0;
            j = 0;

            for (col = 0; col < 16; col++){
                if (i == 0 || j == 0){
                    Fxy.setElmt(row, col, 0.0000);
                }
                else{
                    Fxy.setElmt(row, col, Math.pow(x, i-1) * Math.pow(y, j-1) * j * i);
                }
                i++;
                if (i >= 4){
                    i = 0;
                    j++;
                }
            }
        }
        return Fxy;
    }
    public static Matrix matrixX(){
        Matrix X = new Matrix(16, 16);
        int i, j;
        // mengisi nilai matriks X
        for (i = 0; i < 16; i++){
            for (j = 0; j < 16; j++){
                if (i >= 0 && i <= 3){
                    X.setElmt(i, j, XforF().getElmt(i, j));
                }
                else if (i >= 4 && i <= 7){
                    X.setElmt(i, j, XforFx().getElmt(i%4, j));
                }
                else if (i >= 8 && i <= 11){
                    X.setElmt(i, j, XforFy().getElmt(i%4, j));
                }
                else if (i >= 12 && i <= 15){
                    X.setElmt(i, j, XforFxy().getElmt(i%4, j));
                }
            }
        }
        return X;
    }
    public static void interpolasiBicubic (Matrix m){
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        int i, j;
        Matrix m1;
        Matrix m2;
        // menginput matriks 4x4
        m1 = new Matrix(4, 4);
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                m1.setElmt(i, j, m.matrix[i][j]);
            }
        }

        // menginput nilai a dan b
        m2 = new Matrix(1, 2);
        for(j = 0; j < 2; j++){
            m2.setElmt(0, j, m.matrix[4][j]);
        }

        Matrix tempY = new Matrix(16, 1);
        Matrix X, A;
        // mengubah bentuk 4x4 menjadi 16x1
        int indeks = 0;
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                tempY.setElmt(indeks, 0, m1.getElmt(i,j));
                indeks++;
            }
        }

        // A = (invers X) * Y
        X = matrixX();
        OutputMatrix.printMatrix(X);
        System.out.println("------------");
        Matrix inversX = Invers.inversIdentitas(X);
        OutputMatrix.printMatrix(inversX);
        A = Matrix.multiplyMatrix(inversX, tempY);

        //jumlahkan hasil perkalian A dengan a pangkat i dan b pangkat j
        Double hasil = 0.0;
        indeks = 0;
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                hasil += A.getElmt(indeks, 0) * Math.pow(m2.matrix[0][0], i) * Math.pow(m2.matrix[0][1], j);
                indeks++;
            }
        }
        // output file
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