package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }


    @Override
    public AState getStartState() {
        Position startPos = this.maze.getStartPosition();
        return new MazeState(startPos);
    }

    @Override
    public AState getGoalState() {
        Position goalPos = this.maze.getGoalPosition();
        return new MazeState(goalPos);
    }

    /**
     * find the neighbors of a given state
     * @param ms- current State in the maze
     * @return ArrayList of all the neighbors of the State
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState ms) {
        MazeState mz = (MazeState) ms;
        int currRow = mz.getPosition().getRowIndex();
        int currColumn = mz.getPosition().getColumnIndex();
        ArrayList<AState> successors = new ArrayList<>();

        if (isValid(currRow, -1,true)) { //top
            Position TPos = new Position(currRow - 1, currColumn);
            if (isPassage(TPos)) {
                MazeState TState = new MazeState(TPos,10);
                successors.add(TState);
            }
        }

        updateDiag(successors,mz.getPosition(),-1,1); //top-right

        if (isValid(currColumn, 1,false)) { //right
            Position RPos = new Position(currRow, currColumn + 1);
            if (isPassage(RPos)) {
                MazeState RState = new MazeState(RPos,10);
                successors.add(RState);
            }
        }

        updateDiag(successors,mz.getPosition(),1,1); //bottom-right

        if (isValid(currRow, 1,true)) {//bottom
            Position BPos = new Position(currRow + 1, currColumn);
            if (isPassage(BPos)) {
                MazeState BState = new MazeState(BPos,10);
                successors.add(BState);
            }
        }

        updateDiag(successors,mz.getPosition(),1,-1); //bottom-left

        if (isValid(currColumn, -1,false)) { //left
            Position LPos = new Position(currRow, currColumn - 1);
            if (isPassage(LPos)) {
                MazeState LState = new MazeState(LPos,10);
                successors.add(LState);
            }
        }

        updateDiag(successors,mz.getPosition(),-1,-1); //top-left

        return successors;
    }

    /**
     *
     * @param RowOrColumn- row or column index
     * @param NeighborDistance- the distance of the neighbor from the state
     * @param flag- if true-checks the rows, else checks the columns
     * @return true if the position inside the array, else-false
     */

    private boolean isValid(int RowOrColumn, int NeighborDistance,boolean flag) {
        int rowsNum = this.maze.getArray().length;
        int ColumnsNum = this.maze.getArray()[0].length;
        if (flag) { //if flag=true->checks the row
            if (RowOrColumn + NeighborDistance < 0 || RowOrColumn + NeighborDistance >= rowsNum) {
                return false;
            }
        }
        else {//if flag=false->checks the column
            if (RowOrColumn + NeighborDistance < 0 || RowOrColumn + NeighborDistance >= ColumnsNum) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks if there is a passage (the array in the index equals to 0)
     * @param pos- position on the maze
     * @return true if array in the position equal true, false otherwise
     */
        private boolean isPassage(Position pos) {
        if (this.maze.getArray()[pos.getRowIndex()][pos.getColumnIndex()] == 0) {
            return true;
        }
        return false;
    }

    /**
     * checks if there is a passage (the array in the index equals to 0)
     * @param row- row on the maze
     * @param column- column on the maze
     * @return true if array in the position equal true, false otherwise
     */
    private boolean isPassage(int row,int column) {
        if (this.maze.getArray()[row][column] == 0) {
            return true;
        }
        return false;
    }

    /**
     * calculate the diagonal neighbors of the state
     * @param successors- the arrayList of the neighbors of the state
     * @param pos- position of the state
     * @param rowDistance- distance of the neighbors by row
     * @param colDistance- distance of the neighbors by column
     */
    private void updateDiag(ArrayList<AState> successors,Position pos,int rowDistance,int colDistance){
        int posRow=pos.getRowIndex();
        int posColumn=pos.getColumnIndex();
        if(isValid(posRow,rowDistance,true) && isValid(posColumn,colDistance,false)){
            if(isPassage(posRow+rowDistance,posColumn) || isPassage(posRow,posColumn+colDistance)){
                if(isPassage(posRow+rowDistance,posColumn+colDistance)){
                    Position diagPos=new Position(posRow+rowDistance,posColumn+colDistance);
                    MazeState diagState=new MazeState(diagPos,15);
                    successors.add(diagState);
                }
            }
        }
    }
}


