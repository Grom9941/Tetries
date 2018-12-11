import java.util.List;

public class BestSolver {

    public int[][] choseBest(List<Integer> shapeQueue){
        Field field = new Field();
        Figure figure = new Figure();
        int[][] matr = field.returnField();

        int[][] figure1 = figure.figureRandom(shapeQueue.get(0),0);

        int figure1Length=figure1.length;
        int figure1Width=figure1[0].length;

        List<Integer> onFloor = figure.figureLength(shapeQueue.get(0),0);
        // row=figure1.length;/10
        //col=figure1[0].length;/20


        //роняем фигуру в одном положении
        for (int i=0;i<10-figure1Length;i++){
            List<Integer> list = field.listHeight.subList(i,i+figure1Length-1);

            int max=0;

            //место куда встанет фигура(является самым большим difference(difference-верхняя строка фигуры)
            for(int j=0;j<figure1Length;j++) {
                int difference = list.get(j)-onFloor.get(j);
                if (difference > max) {
                    max=difference;
                }
            }

            int[][] matrWithFigure = field.insert(figure1, figure1Length, figure1Width, max, i, matr);//вставили фигуру
            int numberFillRow = numberFillRow(matrWithFigure);




        }

        //  field.fillFind();
        return matr;
    }



    public int numberFillRow(int[][] field) {
        int numberFill=0;
        int count;
        for (int i=0; i<20; i++) {
            count=0;
            for (int j=0;j<10;j++) {
                while (field[i][j] == 1) {
                    count++;
                }
            }
            if (count==10) numberFill++;
        }
        return numberFill;
    }
}


