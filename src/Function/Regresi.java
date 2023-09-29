package Function;
import ADTMatrix.*;

public class Regresi {
    public static void regresiLinear (Matrix m){
        Matrix mTemp;
        mTemp = new Matrix(m.row, m.col);
        Matrix.sumMultiplyCol(mTemp, 1, 2);
    }
        
}
