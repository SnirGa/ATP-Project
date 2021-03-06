package algorithms.mazeGenerators;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        if (row < 0 || column < 0) {
            return;
        }
        this.row = row;
        this.column = column;
    }

    public int getRowIndex() {
        return row;
    }

    public int getColumnIndex() {
        return column;
    }

    @Override
    public String toString() {
        return "{" + row + "," + column + "}";
    }

    /**
     * check if the Position equals to obj
     *
     * @param obj-
     * @return true if equal, else- false
     */
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position pos = (Position) obj;
            return this.row == pos.row && this.column == pos.column;
        }
        return false;
    }
}

