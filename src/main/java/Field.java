import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field {

    private static List<Integer> listHeight = new ArrayList<Integer>(Collections.nCopies(10, 0));//10 нулей сначало в массиве
    public static int maxHeight=0;

    public static int[][] field = {//[20][10]
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public void insertField(int[][] fieldRecorder){
        field=fieldRecorder;
    }

    private void fillDelete(int i) {

        for (int i1=i ;i1>=20-maxHeight;i1--){//возможно >=
            for (int j=0;j<10;j++) {
                if (i1-1 >= 0) {
                    field[i1][j] = field[i1-1][j];
                } else {
                    field[0][j] = 0;
                }
            }
        }
        heightReload();
    }

    public void heightReload () {
        int max = 0;
        boolean cellFill;
        int i1 = 0;

        for (int j = 0; j < 10; j++) {
            cellFill = false;

            for (int i = 0; i <= 19; i++) {
                if (field[i][j] != 0) {
                    cellFill = true;
                    i1 = i;
                    break;
                }
            }
            if (cellFill) {
                if (listHeight.get(j) != (20-i1)) {
                    listHeight.set(j, (20-i1));
                    if (listHeight.get(j) > max) max = listHeight.get(j);
                }
            }
        }
        maxHeight=max;
    }

    public int fillFind(int[][] fieldHelper) {
        int count;
        int score=0;

        for (int i=0; i<20; i++) {
            count = 0;
            for (int j=0;j<10;j++) {
                if (fieldHelper[i][j] == 1) {
                    count++;
                }
            }
            if (count==10) {
                fillDelete(i);
                score++;
            }
        }
        return score;
    }

    public Pair<int[][], Boolean> insert(int[][] figure, int length, int width, int max, int i, int[][] fieldHelper) {
        int[][] field11 = new int[20][10];
        Boolean overflow=false;
        for (int z=0;z<20;z++ ){
            System.arraycopy(fieldHelper[z], 0, field11[z], 0, 10);
        }

        for (int j = 0; j < length; j++) {
            for (int k = 0; k < width; k++) {
                if ((20-max-length+j)>0) {
                    if(figure[j][k]==1) {
                        field11[20 - max - length + j][i + k] = figure[j][k];
                    }
                } else overflow=true;
            }
        }
      //  return field11;
        return new Pair<int[][], Boolean>(field11,overflow);
    }

}
