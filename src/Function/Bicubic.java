package Function;
import java.util.*;
import ADTMatrix.*;

public class Bicubic {
    public static void interpolasiBicubic (){
        int i, j;
        Matrix m;
        Scanner input = new Scanner(System.in);

        m = new Matrix(4, 4);
        System.out.print("Input matriks\n");
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                m.setElmt(i, j, input.nextDouble());
            }
        }
        System.out.print("Input a:");
        double a = input.nextDouble();
        System.out.print("Input b: ");
        double b = input.nextDouble();

        Matrix tempX = new Matrix(16, 16);
        Matrix tempY = new Matrix(16, 1);
        Matrix X, A;

        int indeks = 0;
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                tempY.setElmt(indeks, 0, m.getElmt(i,j));
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
        OutputMatrix.printMatrix(tempX);

        X = Invers.inversIdentitas(tempX);
        A = Matrix.multiplyMatrix(X, tempY);

        Double hasil = 0.0;
        indeks = 0;
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                hasil += A.getElmt(indeks, 0) * Math.pow(a, i) * Math.pow(b, j);
                indeks++;
            }
        }
        System.out.println("f(" + a + "," + b+ ") = " + hasil);
    }
}