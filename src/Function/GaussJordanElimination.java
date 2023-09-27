package Function;

import ADTMatrix.Matrix;
import java.util.*;
import java.util.function.Function;
import java.io.*;


public class GaussJordanElimination {
    public static Matrix gaussJordanElimination(Matrix A) {
        int n = A.getRowLength();
        int m = A.getColLength();
        
        for (int i = 0; i < n; i++) {
            // Cari pivot non-nol pertama dalam kolom
            int pivotRow = i;
            while (pivotRow < n && A.getElmt(pivotRow, i) == 0) {
                pivotRow++;
            }

            // Pivot ketemu
            if (pivotRow == n) {
                continue;
            }

            // Tukar baris pivot dengan baris saat ini
            A.rowSwap(A, i, pivotRow);

            // Buat pivot jadi 1
            double pivot = A.getElmt(i, i);
            for (int j = i; j < m; j++) {
                A.setElmt(i, j, A.getElmt(i, j) / pivot);
            }

            // Eliminasi baris lainnya
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = A.getElmt(j, i);
                    for (int k = i; k < m; k++) {
                        A.setElmt(j, k, A.getElmt(j, k) - factor * A.getElmt(i, k));
                    }
                }
            }
        }
        
        // Jika ada baris dengan semua elemen nol, swap ke paling bawah
        for (int i = 0; i < n; i++) {
            int allZero = 1;
            for (int j = 0; j < m - 1; j++) {
                if (A.getElmt(i, j) != 0) {
                    allZero = 0;
                    break;
                }
            }
            if (allZero == 1) {
                for (int k = i; k < n - 1; k++) {
                    A.rowSwap(A, k, k + 1);
                }
            }
        }

        // Jika ada baris dengan semua elemen nol, swap ke paling bawah
        for (int i = 0; i < n; i++) {
            // Cari pivot non-nol pertama dalam kolom
            int pivotCol = 0;
            while (pivotCol < m - 1 && A.getElmt(i, pivotCol) == 0) {
                pivotCol++;
            }

            // Pivot ketemu
            if (pivotCol == m - 1) {
                continue;
            }

            // Jika elemen pivot bukan 1, bagi seluruh baris oleh elemen pivot
            if (A.getElmt(i, pivotCol) != 1) {
                double pivotValue = A.getElmt(i, pivotCol);
                for (int k = pivotCol; k < m; k++) {
                    A.setElmt(i, k, A.getElmt(i, k) / pivotValue);
                }
            }
        }
        return A;
    }

    static int SolutionType(Matrix A) {
        int n = A.getRowLength();
        int m = A.getColLength();
        int rank = 0;
        int augmentedColumns = m - 1;
        boolean hasNonZeroRow = false;

        for (int i = 0; i < n; i++) {
            boolean isZeroRow = true;
            for (int j = 0; j < augmentedColumns; j++) {
                if (A.getElmt(i, j) != 0) {
                    isZeroRow = false;
                    break;
                }
            }
            if (!isZeroRow) {
                rank++;
                hasNonZeroRow = true;
            } else if (A.getElmt(i, augmentedColumns) != 0) {
                return 0; // Tidak ada solusi
            }
        }

        if (!hasNonZeroRow || rank < augmentedColumns) {
            return 2; // Solusi banyak (parametrik)
        } else {
            return 1; // Solusi tunggal
        }
    }

    static void solveManySolution(Matrix A) {
        int nEff = A.getColLength() - 1;
        boolean[] visited = new boolean[nEff];
        char[] parametric = new char[nEff];
        int cur = 17;

        for (int i = 0; i < nEff; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < A.getRowLength(); i++) {
            for (int j = i; j < nEff; j++) {
                if (A.getElmt(i, j) == 1) {
                    visited[j] = true;
                    StringBuilder temp = new StringBuilder();

                    if (Math.abs(A.getElmt(i, A.getColLength() - 1)) > 1e-8) {
                        temp.append(String.format("%.2f", Math.abs(A.getElmt(i, A.getColLength() - 1))));
                    }

                    for (int k = j + 1; k < nEff; k++) {
                        if (Math.abs(A.getElmt(i, k)) > 1e-8) {
                            if (!visited[k]) {
                                visited[k] = true;
                                parametric[k] = (char) ('a' + cur % 26);
                                System.out.printf("X%d = %c%n", k + 1, parametric[k]);
                                cur = (cur + 1) % 26;
                            }

                            if (A.getElmt(i, k) > 0) {
                                if (temp.length() == 0) {
                                    temp.append(String.format("%.2f", Math.abs(A.getElmt(i, k))));
                                } else {
                                    temp.append(String.format(" - %.2f", Math.abs(A.getElmt(i, k))));
                                }
                            } else {
                                if (temp.length() == 0) {
                                    temp.append(String.format("%.2f", Math.abs(A.getElmt(i, k))));
                                } else {
                                    temp.append(String.format(" + %.2f", Math.abs(A.getElmt(i, k))));
                                }
                            }
                            temp.append(parametric[k]);
                        }
                    }
                    System.out.printf("X%d = %s%n", j + 1, temp.toString());
                    break;
                } else {
                    if (!visited[j]) {
                        visited[j] = true;
                        parametric[j] = (char) ('a' + cur % 26);
                        System.out.printf("X%d = %c%n", j + 1, parametric[j]);
                        cur = (cur + 1) % 26;
                    }
                }
            }
        }
    }
}
