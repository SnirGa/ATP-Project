package algorithms.mazeGenerators;

public class Position {
    int row;
    int column;
    public Position(int row,int column){
        if (row<0 || column<0){
            throw new IllegalArgumentException("row and column must be bigger or equal to 0");
        }
        this.row=row;
        this.column=column;
    }

    public int getRowIndex() {
        return row;
    }

    public int getColumnIndex() {
        return column;
    }

    @Override
    public String toString() {
        return "{"+row+","+column+"}";
    }

    public boolean equals(Object obj) {
        Position pos=(Position) obj;
        return this.row==pos.row && this.column==pos.column;
    }
}

