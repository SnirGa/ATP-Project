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
        AState startState = new MazeState(startPos);
        return startState;
    }

    @Override
    public AState getGoalState() {
        Position goalPos = this.maze.getGoalPosition();
        AState goalState = new MazeState(goalPos);
        return goalState;
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState ms) {
        MazeState mz = (MazeState) ms;
        int currRow = mz.getPosition().getRowIndex();
        int currColumn = mz.getPosition().getColumnIndex();
        MazeState RState = null;
        ArrayList<AState> successors = new ArrayList<>();
        if (isValid(currRow, -1)) {
            Position TPos = new Position(currRow - 1, currColumn);
            if (isPassage(TPos)) {
                MazeState TState = new MazeState(TPos);
                successors.add(TState);
            }
        }
        if (isValid(currRow, 1)) {
            Position BPos = new Position(currRow + 1, currColumn);
            if (isPassage(BPos)) {
                MazeState BState = new MazeState(BPos);
                successors.add(BState);
            }
        }
        if (isValid(currColumn, -1)) {
            Position LPos = new Position(currRow, currColumn - 1);
            if (isPassage(LPos)) {
                MazeState LState = new MazeState(LPos);
                successors.add(LState);
            }
        }
        if (isValid(currColumn, 1)) {
            Position RPos = new Position(currRow, currColumn + 1);
            if (isPassage(RPos)) {
                RState = new MazeState(RPos);
                successors.add(RState);
            }
            updateDiag(successors,mz.getPosition(),1,1);
            updateDiag(successors,mz.getPosition(),1,-1);
            updateDiag(successors,mz.getPosition(),-1,1);
            updateDiag(successors,mz.getPosition(),-1,-1);
        }
        //if (successors.contains(RState)) {
          //  if (this.maze.getArray()[RState.getPosition().getRowIndex() - 1][RState.getPosition().getColumnIndex()] == 0) {
            //    Position TRPosition = new Position(RState.getPosition().getRowIndex() - 1, RState.getPosition().getColumnIndex());
              //  MazeState TRState = new MazeState(TRPosition);
            //}
            //if (this.maze.getArray()[RState.getPosition().getRowIndex() + 1][RState.getPosition().getColumnIndex()] == 0) {
              //  Position TRPosition = new Position(RState.getPosition().getRowIndex() - 1, RState.getPosition().getColumnIndex());
                //MazeState TRState = new MazeState(TRPosition);
            //}
       // }
        return successors;
    }

    private boolean isValid(int RowOrColumn, int NeighborDistance) {
        int rowsNum = this.maze.getArray().length;
        int ColumnsNum = this.maze.getArray()[0].length;
        if (RowOrColumn + NeighborDistance < 0 || RowOrColumn + NeighborDistance >= rowsNum || RowOrColumn + NeighborDistance >= ColumnsNum) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isPassage(Position pos) {
        if (this.maze.getArray()[pos.getRowIndex()][pos.getColumnIndex()] == 0) {
            return true;
        }
        return false;
    }

    private boolean isPassage(int row,int column) {
        if (this.maze.getArray()[row][column] == 0) {
            return true;
        }
        return false;
    }
    private void updateDiag(ArrayList<AState> successors,Position pos,int rowDistance,int colDistance){
        int posRow=pos.getRowIndex();
        int posColumn=pos.getColumnIndex();
        if(isValid(posRow,rowDistance) && isValid(posColumn,colDistance)){
            if(isPassage(posRow+rowDistance,posColumn) || isPassage(posRow,posColumn+colDistance)){
                if(isPassage(posRow+rowDistance,posColumn+colDistance)){
                    Position diagPos=new Position(posRow+rowDistance,posColumn+colDistance);
                    MazeState diagState=new MazeState(diagPos);
                    successors.add(diagState);
                }
            }
        }
    }
}


