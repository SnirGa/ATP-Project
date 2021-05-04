package algorithms.maze3D;

import java.util.ArrayList;
import java.util.List;

public class MyMaze3DGenerator extends AMaze3DGenerator {
    /**
     * Creates 3DMaze
     * @param depth-the depth of the 3D maze
     * @param row-the row of the 3D maze
     * @param column-the column of the 3D maze
     * @return 3D Maze
     */
    @Override
    public Maze3D generate(int depth, int row, int column) {
        if (row < 2 || column < 2 || depth < 2) {
            return null;
        }
        int[][][] myMaze = new int[depth][row][column];
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < column; k++) {
                    myMaze[i][j][k] = 1;
                }
            }
        }
        Position3D start = new Position3D(0, 0, 0); //set the position of the start cell
        int endDepth = depth - 1;
        int endRow = row - 1;
        int endColumn = column - 1;
        //make sure that the end Position will be in even indexes
        if (endDepth % 2 != 0) {
            endDepth--;
        }
        if (endRow % 2 != 0) {
            endRow--;
        }
        if (endColumn % 2 != 0) {
            endColumn--;
        }
        Position3D end = new Position3D(endDepth, endRow, endColumn);
        List<Position3D> occupied = new ArrayList<>();
        updateMaze(occupied, start, myMaze);
        while (!(occupied.isEmpty())) { //updates the occupied neighbors
            double randomIndex = Math.random() * occupied.size();
            Position3D randomPos = occupied.get((int) randomIndex);
            occupied.remove(randomPos);
            updateMaze(occupied, randomPos, myMaze);
            if (randomPos.getRowIndex() == end.getRowIndex() && randomPos.getColumnIndex() == end.getColumnIndex() && randomPos.getDepthIndex() == end.getDepthIndex()) {
                break;
            }

        }
        return new Maze3D(myMaze, start, end);
    }

    /**
     *update the 3D maze
     * @param occupied- occupied neighbors list
     * @param pos- specific position in the maze
     * @param arr - map that represent the maze
     */
    private void updateMaze(List<Position3D> occupied, Position3D pos, int[][][] arr) {
        arr[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()] = 0;
        updateNeighbors(occupied, pos, arr);
    }

    /**
     *update all the neighbors of a specific 3D position
     * @param occupied- occupied neighbors list
     * @param pos- specific 3D position in the maze
     * @param arr - map that represent the maze
     */
    private void updateNeighbors(List < Position3D > occupied,Position3D pos,int[][][] arr) {

        List<Position3D> vacant=new ArrayList<>();
        updateLeftNeighbor(occupied,vacant,pos,arr);
        updateRightNeighbor(occupied,vacant,pos,arr);
        updateTopNeighbor(occupied,vacant,pos,arr);
        updateBottomNeighbor(occupied,vacant,pos,arr);
        updateBottom3DNeighbor(occupied,vacant,pos,arr);
        updateTop3DNeighbor(occupied,vacant,pos,arr);

        if (!(vacant.isEmpty())) {
            int num = (int) (Math.random() * vacant.size());
            Position3D randVacant = vacant.get(num);
            arr[randVacant.getDepthIndex()][randVacant.getRowIndex()][randVacant.getColumnIndex()] = 0;
        }
    }

    /**
     *update the left neighbor
     * @param occupied- occupied neighbors list
     * @param pos- specific position in the maze
     * @param arr - map that represent the maze
     * @param vacant-list of the neighbors with value 0
     */
    private void updateLeftNeighbor(List < Position3D > occupied,List < Position3D > vacant,Position3D pos,int[][][] arr) {
        if (pos.getColumnIndex() < 2) {
            return;
        }
        if (arr[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()-2] == 1) {
            Position3D leftNeighbor = new Position3D(pos.getDepthIndex(),pos.getRowIndex(), pos.getColumnIndex() - 2);
            occupied.add(leftNeighbor);
        }
        else {
            if (arr[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex() - 1] == 1) {
                //arr[pos.row][pos.column - 1]=0;
                Position3D vac1=new Position3D(pos.getDepthIndex(),pos.getRowIndex(),pos.getColumnIndex()-1);
                vacant.add(vac1);
            }
        }
    }
    /**
     *update the right neighbor
     * @param occupied- occupied neighbors list
     * @param pos- specific position in the maze
     * @param arr - map that represent the maze
     * @param vacant-list of the neighbors with value 0
     */
    private void updateRightNeighbor(List < Position3D > occupied,List < Position3D > vacant,Position3D pos,int[][][] arr) {
        int columnsNum = arr[0][0].length;
        if (pos.getColumnIndex() >= columnsNum - 2) {
            return;
        }
        if (arr[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex() + 2] == 1) {
            Position3D rightNeighbor = new Position3D(pos.getDepthIndex(),pos.getRowIndex(), pos.getColumnIndex() + 2);
            occupied.add(rightNeighbor);
        }
        else {
            if (arr[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex() + 1] == 1 ) {
                //arr[pos.row][pos.column +1]=0;
                Position3D vac2=new Position3D(pos.getDepthIndex(),pos.getRowIndex(),pos.getColumnIndex()+1);
                vacant.add(vac2);
            }
        }
    }
    /**
     *update the top neighbor
     * @param occupied- occupied neighbors list
     * @param pos- specific position in the maze
     * @param arr - map that represent the maze
     * @param vacant-list of the neighbors with value 0
     */
    private void updateTopNeighbor(List < Position3D > occupied,List < Position3D > vacant,Position3D pos,int[][][] arr) {
        if (pos.getRowIndex() < 2) {
            return;
        }
        if (arr[pos.getDepthIndex()][pos.getRowIndex() - 2][pos.getColumnIndex()] == 1) {
            Position3D topNeighbor = new Position3D(pos.getDepthIndex(),pos.getRowIndex() - 2, pos.getColumnIndex());
            occupied.add(topNeighbor);
        } else {
            if (arr[pos.getDepthIndex()][pos.getRowIndex() - 1][pos.getColumnIndex()] == 1) {
                //arr[pos.row-1][pos.column]=0;
                Position3D vac3=new Position3D(pos.getDepthIndex(), pos.getRowIndex()-1,pos.getColumnIndex());
                vacant.add(vac3);
            }
        }
    }
    /**
     *update the bottom neighbor
     * @param occupied- occupied neighbors list
     * @param pos- specific position in the maze
     * @param arr - map that represent the maze
     * @param vacant-list of the neighbors with value 0
     */
    private void updateBottomNeighbor(List < Position3D > occupied,List < Position3D > vacant,Position3D pos,int[][][] arr) {
        int rowsNum = arr[0].length;
        if (pos.getRowIndex() >= rowsNum - 2) {
            return;
        }
        if (arr[pos.getDepthIndex()][pos.getRowIndex() + 2][pos.getColumnIndex()] == 1) {
            Position3D bottomNeighbor = new Position3D(pos.getDepthIndex(),pos.getRowIndex() + 2, pos.getColumnIndex());
            occupied.add(bottomNeighbor);
        }
        else {
            if (arr[pos.getDepthIndex()][pos.getRowIndex() + 1][pos.getColumnIndex()] == 1) {
                Position3D vac4=new Position3D(pos.getDepthIndex(),pos.getRowIndex()+1,pos.getColumnIndex());
                vacant.add(vac4);
            }
        }
    }
    /**
     *update the lower floor neighbor
     * @param occupied- occupied neighbors list
     * @param pos- specific position in the maze
     * @param arr - map that represent the maze
     * @param vacant-list of the neighbors with value 0
     */
    private void updateBottom3DNeighbor(List < Position3D > occupied,List < Position3D > vacant,Position3D pos,int[][][] arr){
        int depthNum = arr.length;
        if (pos.getDepthIndex()>= depthNum - 2) {
            return;
        }
        if (arr[pos.getDepthIndex()+2][pos.getRowIndex()][pos.getColumnIndex()] == 1) {
            Position3D bottomNeighbor = new Position3D(pos.getDepthIndex()+2,pos.getRowIndex(), pos.getColumnIndex());
            occupied.add(bottomNeighbor);
        }
        else {
            if (arr[pos.getDepthIndex()+1][pos.getRowIndex()][pos.getColumnIndex()] == 1) {
                Position3D vac4=new Position3D(pos.getDepthIndex()+1,pos.getRowIndex(),pos.getColumnIndex());
                vacant.add(vac4);
            }
        }
    }
    /**
     *update the upper floor neighbor
     * @param occupied- occupied neighbors list
     * @param pos- specific position in the maze
     * @param arr - map that represent the maze
     * @param vacant-list of the neighbors with value 0
     */
    private void updateTop3DNeighbor(List < Position3D > occupied,List < Position3D > vacant,Position3D pos,int[][][] arr){
        if (pos.getDepthIndex()<2){
            return;
        }
        if (arr[pos.getDepthIndex()-2][pos.getRowIndex()][pos.getColumnIndex()] == 1) {
            Position3D Top3DNeighbor = new Position3D(pos.getDepthIndex()-2,pos.getRowIndex(), pos.getColumnIndex());
            occupied.add(Top3DNeighbor);
        }
        else {
            if (arr[pos.getDepthIndex()-1][pos.getRowIndex()][pos.getColumnIndex()] == 1) {
                Position3D vac4=new Position3D(pos.getDepthIndex()-1,pos.getRowIndex(),pos.getColumnIndex());
                vacant.add(vac4);
            }
        }
    }
}
