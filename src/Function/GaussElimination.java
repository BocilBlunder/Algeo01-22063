package Function;

import ADTMatrix.Matrix;
import java.util.*;
import java.io.*;

public class GaussElimination {
    public static Matrix gaussElimination(Matrix matrix) {
        int n = matrix.getRowLength();
        int m = matrix.getColLength();
        double X[] = new double[n];

        for (int i = 0; i < n; i++) {
            int pivotRow = i;
            while (pivotRow < n && matrix.getElmt(pivotRow, i) == 0) {
                pivotRow++;
            }

            if (pivotRow == n) {
                continue;
            }

            if (matrix.getElmt(pivotRow, i) != 1) {
                double pivotValue = matrix.getElmt(pivotRow, i);
                for (int j = i; j < m; j++) {
                    matrix.setElmt(pivotRow, j, matrix.getElmt(pivotRow, j) / pivotValue);
                }
            }

            for (int j = i; j < m; j++) {
                double temp = matrix.getElmt(i, j);
                matrix.setElmt(i, j, matrix.getElmt(pivotRow, j));
                matrix.setElmt(pivotRow, j, temp);
            }

            for (int j = i + 1; j < n; j++) {
                double factor = matrix.getElmt(j, i);
                for (int k = i; k < m; k++) {
                    matrix.setElmt(j, k, matrix.getElmt(j, k) - factor * matrix.getElmt(i, k));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int pivotColl = 0;
            while (pivotColl < m - 1 && matrix.getElmt(i, pivotColl) == 0) {
                pivotColl++;
            }

            if (pivotColl == m - 1) {
                continue;
            }

            if (matrix.getElmt(i, pivotColl) != 1) {
                double pivotValue2 = matrix.getElmt(i, pivotColl);
                for (int k = pivotColl; k < m; k++) {
                    matrix.setElmt(i, k, matrix.getElmt(i, k) / pivotValue2);
                }
            }
        }
        backSubstitution(matrix, X);
        return matrix;
    }

    public static void backSubstitution(Matrix matrix, double[] X) {
        int n = matrix.getRowLength();
        int m = matrix.getColLength();

        for (int i = n - 1; i >= 0; i--) {
            X[i] = matrix.getElmt(i, m - 1);
            for (int j = i + 1; j < n; j++) {
                X[i] -= matrix.getElmt(i, j) * X[j];
            }
            X[i] /= matrix.getElmt(i, i);
        }
    }

    public static int SolutionType(Matrix matrix) {
        int n = matrix.getRowLength();
        int m = matrix.getColLength();
        int rank = 0;
        int augmentedColumns = m - 1;
        boolean hasNonZeroRow = false;

        for (int i = 0; i < n; i++) {
            boolean isZeroRow = true;
            for (int j = 0; j < augmentedColumns; j++) {
                if (matrix.getElmt(i, j) != 0) {
                    isZeroRow = false;
                    break;
                }
            }
            if (!isZeroRow) {
                rank++;
                hasNonZeroRow = true;
            } else if (matrix.getElmt(i, augmentedColumns) != 0) {
                return 0;
            }
        }

        if (!hasNonZeroRow || rank < augmentedColumns) {
            return 2;
        } else {
            return 1;
        }
    }

    public static void solveManySolution(Matrix matrix) {
        int nEff = matrix.getColLength() - 1;
        boolean[] visited = new boolean[nEff];
        char[] parametric = new char[nEff];
        int cur = 17;

        for (int i = 0; i < nEff; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < matrix.getRowLength(); i++) {
            for (int j = i; j < nEff; j++) {
                if (matrix.getElmt(i, j) == 1) {
                    visited[j] = true;
                    StringBuilder temp = new StringBuilder();

                    if (Math.abs(matrix.getElmt(i, matrix.getColLength() - 1)) > 1e-8) {
                        temp.append(String.format("%.2f", Math.abs(matrix.getElmt(i, matrix.getColLength() - 1))));
                    }

                    for (int k = j + 1; k < nEff; k++) {
                        if (Math.abs(matrix.getElmt(i, k)) > 1e-8) {
                            if (!visited[k]) {
                                visited[k] = true;
                                parametric[k] = (char) ('a' + cur % 26);
                                System.out.printf("X%d = %c%n", k + 1, parametric[k]);
                                cur = (cur + 1) % 26;
                            }

                            if (matrix.getElmt(i, k) > 0) {
                                if (temp.length() == 0) {
                                    temp.append(String.format("%.2f", Math.abs(matrix.getElmt(i, k))));
                                } else {
                                    temp.append(String.format(" - %.2f", Math.abs(matrix.getElmt(i, k))));
                                }
                            } else {
                                if (temp.length() == 0) {
                                    temp.append(String.format("%.2f", Math.abs(matrix.getElmt(i, k))));
                                } else {
                                    temp.append(String.format(" + %.2f", Math.abs(matrix.getElmt(i, k))));
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
