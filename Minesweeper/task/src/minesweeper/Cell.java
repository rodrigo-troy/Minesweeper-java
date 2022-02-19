package minesweeper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Minesweeper
 * User: rodrigotroy
 * Date: 19-02-22
 * Time: 14:57
 */
public class Cell {
    private final int rowIndex;
    private final int columnIndex;
    private int minesAround;
    private Status status;
    private List<Cell> neighbors;

    public Cell(int rowIndex,
                int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.minesAround = 0;
        this.status = Status.UNEXPLORED;
    }

    public void addNeighbor(Cell cell) {
        this.getNeighbors().add(cell);

        if (cell.status.equals(Status.MINE)) {
            minesAround++;
        }
    }

    public int getMinesAround() {
        return minesAround;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Cell> getNeighbors() {
        if (neighbors == null) {
            neighbors = new ArrayList<>();
        }

        return neighbors;
    }

    public void setNeighbors(List<Cell> neighbors) {
        this.neighbors = neighbors;
    }
}
