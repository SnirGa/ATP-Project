package algorithms.mazeGenerators;

public class Maze {
    private int[][] array;
    private Position start;
    private Position end;

    public Maze(int[][] array,Position start,Position end) {
        this.array = array;
        this.start=start;
        this.end=end;
    }
    public Position getStartPosition(){
        return start;
    }
    public Position getGoalPosition(){
        return end;
    }

    public int[][] getArray() {
        return array;
    }


    /**
     * prints the maze
     */
    public void print(){
        String prt="";
        for (int row = 0; row < array.length ; row++) {
            prt+="{";
            for (int col = 0; col <array[0].length ; col++) {
                if(col==start.getColumnIndex() && row==start.getRowIndex()){
                    prt+=" S";
                }
                else if(col==end.getColumnIndex() && row==end.getRowIndex()) {
                    prt += " E";
                }
                else{
                    prt+=" ";
                    prt+=array[row][col];
                }
            }
            prt+=" }\n";
        }
        System.out.println(prt);
    }
}
