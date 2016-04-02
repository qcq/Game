package game.gobang;

import java.util.Scanner;

/**
 * Question 19、利用二维数组实现五子棋下棋功能
 */
public class Gobang {

    private GobangColor[][] data;
    private int row = 10;
    private int column = 10;

    public Gobang() {
        /*
         * The default checkerboard is 10 * 10 boxes;
         */
        this(10, 10);
        initial();
    }

    /*
     * Initial the Game
     */
    public void initial() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                data[i][j] = GobangColor.NULL;
            }
        }
    }

    /*
     * The piece move
     */
    public void setChess(int row, int column, GobangColor color) {
        data[row][column] = color;
    }

    public Gobang(int row, int column) {
        /*
         * set the Chess squre to row * line
         */
        this.row = row;
        this.column = column;
        data = new GobangColor[row][];
        for (int i = 0; i < row; i++) {
            data[i] = new GobangColor[column];
        }
        initial();
    }

    public GobangColor[][] getData() {
        return data;
    }

    public void setData(GobangColor[][] data) {
        this.data = data;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    /*
     * print out the current checkerboard
     */
    public void printCheckerBoard() {
        /*
         * print out the chessboard layout
         */
        for (GobangColor[] i : data) {
            for (GobangColor j : i) {
                System.out.print(j.toString() + " ");
            }
            System.out.println();
        }
    }

    /*
     * verify the input data to insure the piece is in the range and not put
     * this piece in the history place 验证棋子在合适的位置上
     */
    public boolean check(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= column) {
            System.out.println("The input is error");
            System.out.println("The input should from(0, 0) to (" + (x - 1) + "," + (y - 1) + ")");
            return false;
        }
        if (GobangColor.NULL != data[x][y]) {
            System.out.println("This place already has one piece!");
            return false;
        }
        return true;
    }

    /*
     * judge if successed, The parameter x and y is the range which need to be
     * scanning. The Scanning range can zoom in a range which is a circle(a
     * square) center in current move, radius is 5.
     * 扫描棋盘看是不是有棋子获胜，可以优化扫描方案，将扫描的范围局限于一个以落子处为中心 5为半径的棋盘(isSucess的重载方法实现这一个思想)。
     */
    public boolean isSuccess(Place x, Place y, GobangColor color) {
        boolean flag = false;
        int counter = 0;
        label: for (int i = (int) x.getX(); i <= (int) y.getX(); i++) {
            for (int j = (int) x.getY(); j <= (int) y.getY(); j++) {
                /*
                 * scanning the [x,y] range in 4 directions.
                 */

                // check the horizontal
                counter = 0;
                if (j + 4 <= (int) y.getY()) {
                    for (int index = j; index <= j + 4; index++) {
                        if (color == data[i][index]) {
                            counter++;
                        }
                    }
                }
                if (5 == counter) {
                    flag = true;
                    break label;
                }
                // check the vertical
                counter = 0;
                if (i + 4 <= (int) y.getX()) {
                    for (int index = i; index <= i + 4; index++) {
                        if (color == data[index][j]) {
                            counter++;
                        }
                    }
                }
                if (5 == counter) {
                    flag = true;
                    break label;

                }
                // check the dig line
                counter = 0;
                if ((i + 4 <= (int) y.getX()) && (j + 4 <= (int) y.getY())) {
                    for (int indexX = i, indexY = j; indexX <= i + 4; indexX++, indexY++) {
                        if (color == data[indexX][indexY]) {
                            counter++;
                        }
                    }
                }
                if (5 == counter) {
                    flag = true;
                    break label;
                }
                // check the reverse dig line
                counter = 0;
                if ((i + 4 <= (int) y.getX()) && (j - 4 >= (int) x.getY())) {
                    for (int indexX = i, indexY = j; indexX <= i + 4; indexX++, indexY--) {
                        if (color == data[indexX][indexY]) {
                            counter++;
                        }
                    }
                }
                if (5 == counter) {
                    flag = true;
                    break label;
                }
            }
        }

        return flag;

    }

    /*
     * 这是一个重载的方法，与上一个方法不同的是没有使用扫描来实现判定 而是基于落子的地方，来基于四个方向进行计数判断。
     */
    public boolean isSuccess(Place x, GobangColor color) {
        boolean flag = false;
        /*
         * horizontal direction
         */
        int counter = 0;
        for (int i = x.getY(); i >= Math.min(x.getY() - 4, row); i--) {
            if (color == data[x.getX()][i]) {
                counter++;
            } else {
                break;
            }
        }
        for (int i = x.getY() + 1; i <= Math.min(x.getY() + 4, column - 1); i++) {
            if (color == data[x.getX()][i]) {
                counter++;
            } else {
                break;
            }
        }
        if (5 == counter) {
            flag = true;
        }
        /*
         * vertical direction
         */
        counter = 0;
        for (int i = x.getX(); i >= Math.min(x.getX() - 4, row); i--) {
            if (color == data[i][x.getY()]) {
                counter++;
            } else {
                break;
            }
        }
        for (int i = x.getY() + 1; i <= Math.min(x.getY() + 4, column - 1); i++) {
            if (color == data[i][x.getY()]) {
                counter++;
            } else {
                break;
            }
        }
        if (5 == counter) {
            flag = true;
        }
        /*
         * diagonal direction
         */
        counter = 0;
        for (int i = x.getX(), j = x.getY(); i >= Math.min(x.getX() - 4, row)
                && j >= Math.min(x.getY() - 4, row); i--, j--) {
            if (color == data[i][j]) {
                counter++;
            } else {
                break;
            }
        }
        for (int i = x.getX() + 1, j = x.getY() + 1; i <= Math.min(x.getX() + 4, column - 1)
                && j <= Math.min(x.getY() + 4, column - 1); i++, j++) {
            if (color == data[i][j]) {
                counter++;
            } else {
                break;
            }
        }
        if (5 == counter) {
            flag = true;
        }
        /*
         * reverse diagonal direction
         */
        counter = 0;
        for (int i = x.getX(), j = x.getY(); i <= Math.min(x.getX() + 4, column - 1)
                && j >= Math.min(x.getY() - 4, row); i++, j--) {
            if (color == data[i][j]) {
                counter++;
            } else {
                break;
            }
        }
        for (int i = x.getX() + 1, j = x.getY() + 1; i >= Math.min(x.getX() - 4, row)
                && j <= Math.min(x.getY() + 4, column - 1); i--, j++) {
            if (color == data[i][j]) {
                counter++;
            } else {
                break;
            }
        }
        if (5 == counter) {
            flag = true;
        }
        return flag;
    }

    /*
     * The actual game start here, where black first, white second, we can
     * optimize the game which do not call the method isSuccess in the first
     * segment. such as 8 moves. and we can narrow the scanning the range to a
     * square just talk above.
     */
    public void playChess() {
        Scanner in = new Scanner(System.in);
        Place x = new Place(0, 0);
        Place y = new Place(row - 1, column - 1);
        label: while (true) {
            // The input should like 1,2
            while (in.hasNext()) {

                String[] str = in.next().split(",");
                int indexX = Integer.valueOf(str[0]);
                int indexY = Integer.valueOf(str[1]);
                if (!check(indexX, indexY)) {
                    System.out.println("please input again");
                    continue;
                } else {
                    setChess(indexX, indexY, GobangColor.BLACK);
                    printCheckerBoard();
                    // if (isSuccess(x, y, GobangColor.BLACK)){
                    if (isSuccess(new Place(indexX, indexY), GobangColor.BLACK)) {
                        System.out.println("The Black is win");
                        break label;
                    }
                    break;
                }
            }
            while (in.hasNext()) {
                String[] str = in.next().split(",");
                int indexX = Integer.valueOf(str[0]);
                int indexY = Integer.valueOf(str[1]);
                if (!check(indexX, indexY)) {
                    System.out.println("please input again");
                    continue;
                } else {
                    setChess(indexX, indexY, GobangColor.WHITE);
                    printCheckerBoard();
                    // if (isSuccess(x, y, GobangColor.WHITE)){
                    if (isSuccess(new Place(indexX, indexY), GobangColor.WHITE)) {
                        System.out.println("The White is win");

                        break label;
                    }
                    break;
                }
            }
        }
        in.close();
    }

    public static void main(String[] args) {
        Gobang chess = new Gobang();
        chess.playChess();
    }

}
