package algorithms.mazeGenerators;

public class Position {
    int row;
    int column;

    public Position(int row, int column) {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("row and column must be bigger or equal to 0");
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

