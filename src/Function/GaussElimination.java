package Function;

import ADTMatrix.Matrix;
import java.util.*;
import java.io.*;

public class GaussElimination {
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

    public static void gaussElimination(Matrix matrix) {
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

            // Eliminasi baris lainnya
            for (int j = i + 1; j < n; j++) {
                double factor = matrix.getElmt(j, i) / matrix.getElmt(i, i);
                for (int k = i; k < m; k++) {
                    matrix.setElmt(j, k, matrix.getElmt(j, k) - factor * matrix.getElmt(i, k));
                }
            }
        }
    }

    public static void backSubstitution(Matrix matrix, double[] X) {
        int n = matrix.getRowLength();
        int m = matrix.getColLength();

        for (int i = n - 1; i >= 0; i--) {
            X[i] = matrix.getElmt(i, m - 1);  // Inisialisasi dengan elemen kolom augmented
            for (int j = i + 1; j < n; j++) {
                X[i] -= matrix.getElmt(i, j) * X[j];
            }
            X[i] /= matrix.getElmt(i, i);
        }
    }
}