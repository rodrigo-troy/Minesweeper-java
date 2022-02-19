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
    private int rowIndex;
    private int ColumnIndex;
    private Status status;
    private Boolean containBomb;
    private List<Cell> neighbords;

    public Cell(int rowIndex,
                int columnIndex) {
        this.rowIndex = rowIndex;
        ColumnIndex = columnIndex;
        this.status = Status.UNEXPLORED;
        this.containBomb = false;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return ColumnIndex;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getContainBomb() {
        return containBomb;
    }

    public void setContainBomb(Boolean containBomb) {
        this.containBomb = containBomb;
    }

    public List<Cell> getNeighbords() {
        if (neighbords == null) {
            neighbords = new ArrayList<>();
        }

        return neighbords;
    }

    public void setNeighbords(List<Cell> neighbords) {
        this.neighbords = neighbords;
    }
}
