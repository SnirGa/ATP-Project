package algorithms.mazeGenerators;

public class Position {
    int row;
    int column;
    Position(int row,int column){
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
}

