import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field {

    public List<Integer> listHeight = new ArrayList<Integer>(Collections.nCopies(10, 0));//10 нулей сначало в массиве
    public int maxHeight=0;

    private int[][] field = {//[20][10]
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

        for (int i1=0 ;i1<maxHeight;i1++){
            for (int j=0;j<10;j++) {
                if (i-maxHeight-1 >= 0) {
                    field[i-maxHeight][j] = field[i-maxHeight-1][j];
                } else {
                    field[0][j] = 0;
                }
            }
        }
        heightReload();
    }

    public int[][] returnField(){
        return field;
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
                if (listHeight.get(j) != i1) {
                    listHeight.set(j, i1);
                    if (listHeight.get(j) > max) max = listHeight.get(j);
                }
            }
        }
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

    public int[][] insert(int[][] figure, int length, int width, int max, int i, int[][] fieldHelper) {
        int[][] field11 = new int[20][10];
        for (int z=0;z<20;z++ ){
            System.arraycopy(fieldHelper[z], 0, field11[z], 0, 10);
        }

        for (int j = 0; j < length; j++) {
            System.arraycopy(figure[j], 0, field11[20 - max - length + j], i, width);
        }
        return field11;
    }

}
