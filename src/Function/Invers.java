import ADTMatrix.Matrix;
import java.util.*;
import java.io.*;

public class Invers {
	public double matrix [][];
    public boolean inversExist = true;

    public Matrix inversIdentitas (Matrix matrix){
        int i, j, k;
        Matrix invers;
		invers = new Matrix(matrix.getRowLength(), matrix.getColLength()*2);
		
        for (i = 0; i < matrix.getRowLength(); i++){
            for (j = 0; j < matrix.getColLength(); j++){
                if (i == j){
					invers.setElmt(i, j+matrix.getColLength(), 1);
                }
				else{
					invers.setElmt(i, j+matrix.getColLength(), 0);
				}
				invers.setElmt(i, j, matrix.getElmt(i,j));
            }
        }
        for (i = 0; i < invers.getRowLength(); i++){
			for (j = 0; j < invers.getRowLength(); j++){
				if (i != j){
					k = (i + 1);
				    if (invers.getElmt(i,i) == 0){
					    inversExist = false;
					    while (k < invers.getRowLength()){
                            if (invers.getElmt(k,i) != 0){
                                invers.rowSwap(invers, i, k);
                                inversExist = true;
						    }
						    k++;
					    }
				    }

				    if (inversExist == false){
					    return matrix;
					}
				    double sub = -1 * invers.getElmt(j, i) / invers.getElmt(i, i);
				    for (k = i; k < invers.getColLength(); k++){
					    invers.setElmt(j, k, (invers.getElmt(j,k)+sub*invers.getElmt(i,k)));
                    }
			    }
		    }
        }

		for (i = 0; i < invers.getRowLength(); i++){
			double divisor = invers.getElmt(i,i);
			for (j = 0; j < invers.getColLength(); j++){
				if (invers.getElmt(i,j) != 0){
                    invers.setElmt(i, j, invers.getElmt(i,j) / divisor);
                }
			}
		}

		for (i = 0; i < matrix.getRowLength(); i++){
			for (j = 0; j < matrix.getColLength(); j++){
				matrix.setElmt(i, j, (invers.getElmt(i, j+matrix.getColLength())));
			}
		}
		return matrix;
	}
}