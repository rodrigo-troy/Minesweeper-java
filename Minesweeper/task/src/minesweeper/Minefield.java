package minesweeper;

import static minesweeper.Status.*;

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

    public void markCell(int row,
                         int column) {
        if (areInvalidValues(row,
                             column)) {
            return;
        }

        Cell cell = board[row][column];
        if (cell.getStatus().equals(UNEXPLORED)) {
            cell.setStatus(UNEXPLORED_MARKED);
        } else if (cell.getStatus().equals(UNEXPLORED_MINE)) {
            cell.setStatus(MINE_MARKED);
        } else if (cell.getStatus().equals(MINE_MARKED)) {
            cell.setStatus(UNEXPLORED_MINE);
        } else if (cell.getStatus().equals(UNEXPLORED_MARKED)) {
            cell.setStatus(UNEXPLORED);
        }
    }

    private void exploreCell(Cell cell) {
        exploreCell(cell.getRowIndex(),
                    cell.getColumnIndex());
    }

    public boolean checkWinner() {
        int unexploredCount = 0;
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (cell.getStatus().equals(UNEXPLORED_MINE) ||
                    cell.getStatus().equals(UNEXPLORED)) {
                    unexploredCount++;
                }
            }
        }

        if (unexploredCount == minesCount) {
            return true;
        }

        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (cell.getStatus().equals(UNEXPLORED_MINE)) {
                    return false;
                }
            }
        }


        return true;
    }

    public void exploreCell(int row,
                            int column) {
        if (areInvalidValues(row,
                             column)) {
            return;
        }

        Cell cell = board[row][column];
        if (cell.getStatus().equals(UNEXPLORED)) {
            cell.setStatus(EXPLORED);

            if (cell.getMinesAround() == 0) {
                for (Cell neighbor : cell.getNeighbors()) {
                    exploreCell(neighbor);
                }
            }
        }
    }

    public boolean isBomb(int row,
                          int column) {
        if (areInvalidValues(row,
                             column)) {
            return false;
        }

        return board[row][column].isBomb();
    }

    private boolean areInvalidValues(int row,
                                     int column) {
        return row < 0 ||
               column < 0 ||
               row == board.length ||
               column == board[0].length;
    }

    private void addNeighbor(Cell cell,
                             int neighborRowIndex,
                             int neighborColIndex) {
        if (areInvalidValues(neighborRowIndex,
                             neighborColIndex)) {
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

            if (board[rowIndex][colIndex].getStatus().equals(UNEXPLORED)) {
                board[rowIndex][colIndex].setStatus(UNEXPLORED_MINE);
                minesCount--;
            }
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                this.addNeighbor(board[r][c],
                                 r - 1,
                                 c - 1);
                this.addNeighbor(board[r][c],
                                 r - 1,
                                 c);
                this.addNeighbor(board[r][c],
                                 r - 1,
                                 c + 1);
                this.addNeighbor(board[r][c],
                                 r,
                                 c - 1);
                this.addNeighbor(board[r][c],
                                 r,
                                 c + 1);
                this.addNeighbor(board[r][c],
                                 r + 1,
                                 c - 1);
                this.addNeighbor(board[r][c],
                                 r + 1,
                                 c);
                this.addNeighbor(board[r][c],
                                 r + 1,
                                 c + 1);
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
                if (board[r][c].getStatus().equals(UNEXPLORED_MINE)) {
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

    public void printMatrix() {
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
                Cell cell = board[r][c];
                Status status = cell.getStatus();

                if (status.equals(UNEXPLORED) || status.equals(UNEXPLORED_MINE)) {
                    System.out.print('.');
                } else if (status.equals(UNEXPLORED_MARKED) || status.equals(MINE_MARKED)) {
                    System.out.print('*');
                } else if (status.equals(EXPLORED) && cell.getMinesAround() > 0) {
                    System.out.print(cell.getMinesAround());
                } else if (status.equals(EXPLORED) && cell.getMinesAround() == 0) {
                    System.out.print('/');
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
