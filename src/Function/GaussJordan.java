package Function;

import ADTMatrix.Matrix;
import java.util.*;
import java.io.*;


public class GaussJordanElimination {
    public static void printMatrix(Matrix matrix) {
        int n = matrix.getRowLength();
        int m = matrix.getColLength();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%0.2f ", matrix.getElmt(i, j));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void gaussJordanElimination(Matrix matrix) {
        int n = matrix.getRowLength();
        int m = matrix.getColLength();

        for (int i = 0; i < n; i++) {
            // Cari pivot non-nol pertama dalam kolom
            int pivotRow = i;
            while (pivotRow < n && matrix.getElmt(pivotRow, i) == 0) {
                pivotRow++;
            }

            // Pivot ketemu
            if (pivotRow == n) {
                continue;
            }

            // Tukar baris pivot dengan baris saat ini
            matrix.rowSwap(matrix, i, pivotRow);

            // Buat pivot jadi 1
            double pivot = matrix.getElmt(i, i);
            for (int j = i; j < m; j++) {
                matrix.setElmt(i, j, matrix.getElmt(i, j) / pivot);
            }

            // Eliminasi baris lainnya
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = matrix.getElmt(j, i);
                    for (int k = i; k < m; k++) {
                        matrix.setElmt(j, k, matrix.getElmt(j, k) - factor * matrix.getElmt(i, k));
                    }
                }
            }
        }
    }
}