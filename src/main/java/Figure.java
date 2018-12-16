import java.util.ArrayList;

public class Figure {

    public ArrayList<Integer> figureLength(int numberFigure,int position) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        switch (numberFigure) {
            case 1:
                list = lengthI(position);
                break;
            case 2:
                list = lengthJ(position);
                break;
            case 3:
                list = lengthL(position);
                break;
            case 4:
                list = lengthO(position);
                break;
            case 5:
                list = lengthS(position);
                break;
            case 6:
                list = lengthT(position);
                break;
            case 7:
                list = lengthZ(position);
                break;
        }
        return list;
    }

    private ArrayList<Integer> lengthI(int position) {
        ArrayList<Integer> size = new ArrayList<Integer>();
        switch (position) {
            case 1:
            case 3:
                size.add(0);size.add(0);size.add(0);size.add(0);
                break;
            case 2:
            case 4:
                size.add(0);
                break;
        }
        return size;
    }

    private ArrayList<Integer> lengthJ(int position) {
        ArrayList<Integer> size = new ArrayList<Integer>();
        switch (position) {
            case 1:
                size.add(0);size.add(0);size.add(0);
                break;
            case 2:
                size.add(0);size.add(2);
                break;
            case 3:
                size.add(1);size.add(1);size.add(0);
                break;
            case 4:
                size.add(0);size.add(0);
                break;
        }
        return size;
    }

    private ArrayList<Integer> lengthL(int position) {
        ArrayList<Integer> size = new ArrayList<Integer>();
        switch (position) {
            case 1:
                size.add(0);size.add(0);size.add(0);
                break;
            case 2:
                size.add(0);size.add(0);
                break;
            case 3:
                size.add(0);size.add(1);size.add(1);
                break;
            case 4:
                size.add(2);size.add(0);
                break;
        }
        return size;
    }

    private ArrayList<Integer> lengthO(int position) {
        ArrayList<Integer> size = new ArrayList<Integer>();
        switch (position) {
            case 1:
            case 2:
            case 3:
            case 4:
                size.add(0);size.add(0);
                break;
        }
        return size;
    }

    private ArrayList<Integer> lengthS(int position) {
        ArrayList<Integer> size = new ArrayList<Integer>();
        switch (position) {
            case 1:
            case 3:
                size.add(0);size.add(0);size.add(1);
                break;
            case 2:
            case 4:
                size.add(1);size.add(0);
                break;
        }
        return size;
    }

    private ArrayList<Integer> lengthT(int position) {
        ArrayList<Integer> size = new ArrayList<Integer>();
        switch (position) {
            case 1:
                size.add(0);size.add(0);size.add(0);
                break;
            case 2:
                size.add(0);size.add(1);
                break;
            case 3:
                size.add(1);size.add(0);size.add(1);
                break;
            case 4:
                size.add(1);size.add(0);
                break;
        }
        return size;
    }

    private ArrayList<Integer> lengthZ(int position) {
        ArrayList<Integer> size = new ArrayList<Integer>();
        switch (position) {
            case 1:
            case 3:
                size.add(1);size.add(0);size.add(0);
                break;
            case 2:
            case 4:
                size.add(0);size.add(1);
                break;
        }
        return size;
    }

    public int[][] figureRandom(int numberFigure,int position) {
        int[][] matrix = new int[4][4];
        switch (numberFigure) {
            case 1:
                matrix = I(position);
                break;
            case 2:
                matrix = J(position);
                break;
            case 3:
                matrix = L(position);
                break;
            case 4:
                matrix = O(position);
                break;
            case 5:
                matrix = S(position);
                break;
            case 6:
                matrix = T(position);
                break;
            case 7:
                matrix = Z(position);
                break;
        }
        return matrix;
    }

    private int[][] I(int position) {
        int[][] matrix = new int[4][4];
        switch (position) {
            case 1:
            case 3:
                matrix = new int[][]{
                        {1, 1, 1, 1}
                };
                break;
            case 2:
            case 4:
                matrix = new int[][]{
                        {1},
                        {1},
                        {1},
                        {1}
                };
                break;
        }
        return matrix;
    }

    private int[][] J(int position) {
        int[][] matrix = new int[3][3];
        switch (position) {
            case 1:
                matrix = new int[][]{
                        {1, 0, 0},
                        {1, 1, 1}
                };
                break;
            case 2:
                matrix = new int[][]{
                        {1, 1},
                        {1, 0},
                        {1, 0}
                };
                break;
            case 3:
                matrix = new int[][]{
                        {1, 1, 1},
                        {0, 0, 1}
                };
                break;
            case 4:
                matrix = new int[][]{
                        {0, 1},
                        {0, 1},
                        {1, 1}
                };
                break;
        }
        return matrix;
    }

    private int[][] L(int position) {
        int[][] matrix = new int[3][3];
        switch (position) {
            case 1:
                matrix = new int[][]{
                        {0, 0, 1},
                        {1, 1, 1}
                };
                break;
            case 2:
                matrix = new int[][]{
                        {1, 0},
                        {1, 0},
                        {1, 1}
                };
                break;
            case 3:
                matrix = new int[][]{
                        {1, 1, 1},
                        {1, 0, 0}
                };
                break;
            case 4:
                matrix = new int[][]{
                        {1, 1},
                        {0, 1},
                        {0, 1}
                };
                break;
        }
        return matrix;
    }

    private int[][] O(int position) {
        int[][] matrix = new int[2][2];
        switch (position) {
            case 1:
            case 2:
            case 3:
            case 4:
                matrix = new int[][]{
                        {1, 1},
                        {1, 1}
                };
        }
        return matrix;
    }

    private int[][] S(int position) {
        int[][] matrix = new int[4][4];
        switch (position) {
            case 1:
            case 3:
                matrix = new int[][]{
                        {0, 1, 1},
                        {1, 1, 0}
                };
                break;
            case 2:
            case 4:
                matrix = new int[][]{
                        {1, 0},
                        {1, 1},
                        {0, 1}
                };
                break;
        }
        return matrix;
    }

    private int[][] T(int position) {
        int[][] matrix = new int[3][3];
        switch (position) {
            case 1:
                matrix = new int[][]{
                        {0, 1, 0},
                        {1, 1, 1}
                };
                break;
            case 2:
                matrix = new int[][]{
                        {1, 0},
                        {1, 1},
                        {1, 0}
                };
                break;
            case 3:
                matrix = new int[][]{
                        {1, 1, 1},
                        {0, 1, 0}
                };
                break;
            case 4:
                matrix = new int[][]{
                        {0, 1},
                        {1, 1},
                        {0, 1}
                };
                break;
        }
        return matrix;
    }

    private int[][] Z(int position) {
        int[][] matrix = new int[4][4];
        switch (position) {
            case 1:
            case 3:
                matrix = new int[][]{
                        {1, 1, 0},
                        {0, 1, 1}
                };
                break;
            case 2:
            case 4:
                matrix = new int[][]{
                        {0, 1},
                        {1, 1},
                        {1, 0}
                };
                break;
        }
        return matrix;
    }
}
