package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    Maze3D maze3d;
    public SearchableMaze3D(Maze3D maze3d) {
            this.maze3d = maze3d;
        }


        @Override
        public AState getStartState() {
            Position3D startPos = this.maze3d.getStartPosition();
            return new Maze3DState(startPos);
        }

        @Override
        public AState getGoalState() {
            Position3D goalPos = this.maze3d.getGoalPosition();
            return  new Maze3DState(goalPos);
        }

        /**
         * find the neighbors of a given state
         * @param ms- current State in the maze
         * @return ArrayList of all the neighbors of the State
         */
        @Override
        public ArrayList<AState> getAllSuccessors(AState ms) {
            Maze3DState mz = (Maze3DState) ms;
            int currDepth=mz.getPosition().getDepthIndex();
            int currRow = mz.getPosition().getRowIndex();
            int currColumn = mz.getPosition().getColumnIndex();
            ArrayList<AState> successors = new ArrayList<>();

            if (isValid(currRow, -1,1)) { //top
                Position3D TPos = new Position3D(currDepth,currRow - 1, currColumn);
                if (isPassage(TPos)) {
                    Maze3DState TState = new Maze3DState(TPos,10);
                    successors.add(TState);
                }
            }

            if (isValid(currColumn, 1,2)) { //right
                Position3D RPos = new Position3D(currDepth,currRow, currColumn + 1);
                if (isPassage(RPos)) {
                    Maze3DState RState = new Maze3DState(RPos,10);
                    successors.add(RState);
                }
            }

            if (isValid(currRow, 1,1)) {//bottom
                Position3D BPos = new Position3D(currDepth,currRow + 1, currColumn);
                if (isPassage(BPos)) {
                    Maze3DState BState = new Maze3DState(BPos,10);
                    successors.add(BState);
                }
            }
            if (isValid(currColumn, -1,2)) { //left
                Position3D LPos = new Position3D(currDepth,currRow, currColumn - 1);
                if (isPassage(LPos)) {
                    Maze3DState LState = new Maze3DState(LPos,10);
                    successors.add(LState);
                }
            }

            if (isValid(currDepth, -1,0)) { //upper floor
                Position3D abovePos = new Position3D(currDepth-1,currRow, currColumn);
                if (isPassage(abovePos)) {
                    Maze3DState aboveState = new Maze3DState(abovePos,10);
                    successors.add(aboveState);
                }
            }

            if (isValid(currDepth, 1,0)) { //bottom floor
                Position3D BottomPos = new Position3D(currDepth+1,currRow, currColumn);
                if (isPassage(BottomPos)) {
                    Maze3DState BottomState = new Maze3DState(BottomPos,10);
                    successors.add(BottomState);
                }
            }


            return successors;
        }

        /**
         *check if the route given is valid
         * @param DRC- row or column or depth index
         * @param NeighborDistance- the distance of the neighbor from the state
         * @param flag- if 0 checks the depth else if 1-checks the rows, if else 2 checks the columns
         * @return true if the position inside the array, else-false
         */
        private boolean isValid(int DRC, int NeighborDistance,int flag) {
            int depthNum = this.maze3d.getMap().length;
            int rowsNum = this.maze3d.getMap()[0].length;
            int ColumnsNum = this.maze3d.getMap()[0][0].length;
            if (flag == 1) { //if flag=1->checks the row
                if (DRC + NeighborDistance < 0 || DRC + NeighborDistance >= rowsNum) {
                    return false;
                }
            } else if (flag == 2) {//if flag=2->checks the column
                if (DRC + NeighborDistance < 0 || DRC + NeighborDistance >= ColumnsNum) {
                    return false;
                }
            } else if (flag == 0) {//if flag=0->checks the depth
                if (DRC + NeighborDistance < 0 || DRC + NeighborDistance >= depthNum) {
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
        private boolean isPassage(Position3D pos) {
            if (this.maze3d.getMap()[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()] == 0) {
                return true;
            }
            return false;
        }

    }




