package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D {
    private int DepthIndex;
    private int RowIndex;
    private int ColumnIndex;

    public Position3D(int depthIndex, int rowIndex, int columnIndex) {
        DepthIndex = depthIndex;
        RowIndex = rowIndex;
        ColumnIndex = columnIndex;
    }

    public int getDepthIndex() {
        return DepthIndex;
    }

    public int getRowIndex() {
        return RowIndex;
    }



    public int getColumnIndex() {
        return ColumnIndex;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Position3D) {
            Position3D pos = (Position3D) obj;
            return this.RowIndex==pos.getRowIndex() && this.ColumnIndex == pos.getColumnIndex() && this.DepthIndex==pos.getDepthIndex();
        }
        return false;
    }

    @Override
    public String toString() {
        return "{" + DepthIndex + "," + RowIndex + ","+ ColumnIndex+ "}";
    }

}


