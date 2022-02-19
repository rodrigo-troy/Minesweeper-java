package minesweeper;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Minesweeper
 * User: rodrigotroy
 * Date: 19-02-22
 * Time: 15:06
 */
public class Minefield {
    private final Cell[][] board;
    private int minesCount;

    public Minefield(int minesCount,
                     int columns,
                     int rows) {
        this.board = new Cell[rows][columns];
        this.minesCount = minesCount;
        this.prepareBoard(minesCount);
    }

    private void addNeighbor(Cell cell,
                             int neighborRowIndex,
                             int neighborColIndex) {
        if (neighborRowIndex < 0 ||
            neighborColIndex < 0 ||
            neighborRowIndex == board.length ||
            neighborColIndex == board[0].length) {
            return;
        }

        cell.addNeighbor(board[neighborRowIndex][neighborColIndex]);
    }

    private void prepareBoard(int minesCount) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = new Cell(r,
                                       c);
            }
        }

        if (this.minesCount > board.length * board[0].length) {
            this.minesCount = board.length * board[0].length;
        }

        while (minesCount != 0) {
            int rowIndex = (int) (Math.random() * board.length);
            int colIndex = (int) (Math.random() * board[0].length);

            if (board[rowIndex][colIndex].getStatus().equals(Status.UNEXPLORED)) {
                board[rowIndex][colIndex].setStatus(Status.MINE);
                minesCount--;
            }
        }
    }

    public void printRealMatrix() {
        System.out.print(" |");
        for (int c = 0; c < board[0].length; c++) {
            System.out.printf("%d",
                              c + 1);
        }
        System.out.println("|");

        System.out.print("-|");
        for (int c = 0; c < board[0].length; c++) {
            System.out.print("-");
        }
        System.out.println("|");

        for (int r = 0; r < board.length; r++) {
            System.out.printf("%d|",
                              r + 1);

            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c].getStatus().equals(Status.MINE)) {
                    System.out.print('X');
                } else if (board[r][c].getMinesAround() == 0) {
                    System.out.print('.');
                } else {
                    System.out.print(board[r][c].getMinesAround());
                }
            }

            System.out.println("|");
        }

        System.out.print("-|");
        for (int c = 0; c < board[0].length; c++) {
            System.out.print("-");
        }
        System.out.println("|");
    }
}
