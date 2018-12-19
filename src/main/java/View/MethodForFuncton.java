package View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MethodForFuncton {
    protected static int numberFillRow(int[][] field) {//количество заполнившихся рядов
        int numberFill=0;
        int count;
        for (int i=0; i<20; i++) {
            count=0;
            for (int j=0;j<10;j++) {
                if (field[i][j] == 1) {
                    count++;
                }
            }
            if (count==10) numberFill++;
        }
        return numberFill;
    }

    protected static int numberHole(int[][] field) {//количество клеток заполненных с верху
        int numberHole=0;
        for (int i=1; i<20; i++) {
            for (int j=0;j<10;j++) {
                if (field[i][j] == 0 && field[i-1][j]==1) {
                    numberHole++;
                }
            }
        }
        return numberHole;
    }

    protected static int  maxHeight(int[][] field) {//максимальная высота от пола после установки фигуры
        int max=0;
        boolean check = true;
        for (int i=0; i<20; i++) {
            for (int j=0;j<10;j++) {
                if (field[i][j] != 0 && check) {
                    max=20-i;check=false;
                }
            }
        }
        return max;
    }

    protected static int numberSlot(int[][] field) {//количество колодцев
        List<Integer> listHeight1 = heightList(field);
        int numberFill=0;

        if(listHeight1.get(0)<listHeight1.get(1)) numberFill++;
        if(listHeight1.get(8)>listHeight1.get(9)) numberFill++;

        for (int i=1; i<listHeight1.size()-1; i++) {
            if(listHeight1.get(i-1)>listHeight1.get(i) && listHeight1.get(i)<listHeight1.get(i+1)) numberFill++;
        }
        return numberFill;
    }

    protected static int numberNotFill(int[][] field) {//если ряд не заполнен больше чем 3 клетками
        int numberNotFill=0;
        int cout;
        for (int j=0;j<10;j++) {
            cout=0;
            for (int i=19; i>=0; i--) {
                if (field[i][j] == 0) {
                    cout++;
                } else {
                    if (cout>=3) numberNotFill++;
                    cout=0;
                }
            }
        }
        return numberNotFill;
    }

    protected static List<Integer> heightList(int[][] field) {
        List<Integer> listHeight1 = new ArrayList<Integer>(Collections.nCopies(10, 0));
        boolean cellFill;
        int i1 = 0;

        for (int j=0;j<10;j++){
            cellFill=false;

            for (int i=0;i<=19;i++){
                if (field[i][j]!=0) {
                    cellFill=true;
                    i1=i;
                    break;
                }
            }
            if (cellFill) {
                if (listHeight1.get(j) != i1) {
                    listHeight1.set(j, i1);
                }
            }
        }
        return listHeight1;
    }
}
