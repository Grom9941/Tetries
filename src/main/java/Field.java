import java.util.ArrayList;
import java.util.List;

public class Field {

    public List<Integer> listHeight = new ArrayList<Integer>(10);
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

    public void fillDelete(int i) {

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
        int max=0;
        for (int j=0;j<10;j++){
            int i=0;
            while (field[i][j]==0 && i<20){
                i++;
            }
            if (listHeight.get(j)!=i) {
                listHeight.set(j,i);
                if (listHeight.get(j)>max) max=listHeight.get(j);
            }
        }
    }

    public void fillFind(int max, int[][] fieldHelper, int figureWidth) {//figureWidth=
        int count=0;

        for (int i=20-max-figureWidth; i<20-max; i++) {
            for (int j=0;j<10;j++) {
                while (fieldHelper[i][j] == 1) {
                    count++;
                }
            }
            if (count==10) fillDelete(i);
            count=0;
        }
    }

    public int[][] insert(int[][] figure, int length, int width, int max, int i, int[][] fieldHelper) {
        int i1 = i;
        for (int k = 0; k < width; k++) {
            for (int j = 0; j < length; j++) {
                fieldHelper[20 - max - width + k + 1][i1] = figure[j][k];
                i1++;
            }
            i1=i;
        }
        return fieldHelper;
    }

}
