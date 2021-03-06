package algorithms.mazeGenerators;
//check
import java.util.ArrayList;
import java.util.List;

public class MyMazeGenerator extends AMazeGenerator {
    public MyMazeGenerator() {
    }

    /**
     * based on Prim algorithm
     * @param rows- number of rows in the maze
     * @param columns- number of columns in the maze
     * @return maze
     */
    @Override
    public Maze generate(int rows, int columns) {
        if (rows<2 || columns<2){
            return null;
        }

        int[][] myMaze = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                myMaze[row][column] = 1;
            }
        }
        Position start = new Position(0,0); //set the position of the start cell
        int endRow=rows-1;
        int endColumn=columns-1;
        if (endRow%2!=0){
            endRow=endRow-1;
        }
        if (endColumn%2!=0){
            endColumn=endColumn-1;
        }
        Position end = new Position(endRow,endColumn); //set the position if the end cell
        if (end.equals(start)){
            end=new Position(end.getRowIndex()+1,end.getColumnIndex()+1);
            myMaze[0][1]=0;
        }
        //Position end =new Position(-1,-1);
        List <Position> occupied=new ArrayList<>();
        updateMaze(occupied,start,myMaze);
        while(!(occupied.isEmpty())){
           double randomIndex=Math.random()*occupied.size();
           Position randomPos=occupied.get((int)randomIndex);
           occupied.remove(randomPos);
           updateMaze(occupied,randomPos,myMaze);
           if(randomPos.getRowIndex()==end.getRowIndex() && randomPos.getColumnIndex()== end.getColumnIndex()){
               break;
           }

           }

        return new Maze(myMaze,start,end);
    }

    /**
     *
     * @param occupied- neighbors of the cell that were chosen random before
     * @param pos- Position of a cell in the 2D array
     * @param arr- 2D array of the future maze
     */
    private void updateMaze (List < Position > occupied,Position pos,int[][] arr){
            arr[pos.getRowIndex()][pos.getColumnIndex()]=0;
            updateNeighbors(occupied,pos,arr);
    }

    /**
     * updates the neighbors of a specific cell
     * @param occupied- the list of all the neighbors of prev cells that contains 1
     * @param pos- the specific cell position
     * @param arr- the maze array
     */
    private void updateNeighbors(List < Position > occupied,Position pos,int[][] arr) {

        List<Position> vacant=new ArrayList<>();
        updateLeftNeighbor(occupied,vacant,pos,arr);
        updateRightNeighbor(occupied,vacant,pos,arr);
        updateTopNeighbor(occupied,vacant,pos,arr);
        updateBottomNeighbor(occupied,vacant,pos,arr);
        if (!(vacant.isEmpty())) {
            int num = (int) (Math.random() * vacant.size());
            Position randVacant = vacant.get(num);
            arr[randVacant.getRowIndex()][randVacant.getColumnIndex()] = 0;
        }
    }

    /**
     * update the left neighbor of a specific cell
     * @param occupied- the list of all the neighbors of prev cells that contains 1
     * @param vacant- the list of all the neighbors of prev cells that contains 0
     * @param pos- the specific cell position
     * @param arr- the maze array
     */
    private void updateLeftNeighbor(List < Position > occupied,List < Position > vacant,Position pos,int[][] arr) {
        if (pos.getColumnIndex() < 2) {
            return;
        }
        if (arr[pos.getRowIndex()][pos.getColumnIndex()-2] == 1) {
            Position leftNeighbor = new Position(pos.getRowIndex(), pos.getColumnIndex() - 2);
            occupied.add(leftNeighbor);
        }
        else {
            if (arr[pos.getRowIndex()][pos.getColumnIndex() - 1] == 1) {
                //arr[pos.row][pos.column - 1]=0;
                Position vac1=new Position(pos.getRowIndex(),pos.getColumnIndex()-1);
                vacant.add(vac1);

            }
        }
    }

    /**
     * update the right neighbor of a specific cell
     * @param occupied- the list of all the neighbors of prev cells that contains 1
     * @param vacant- the list of all the neighbors of prev cells that contains 0
     * @param pos- the specific cell position
     * @param arr- the maze array
     */
    private void updateRightNeighbor(List < Position > occupied,List < Position > vacant,Position pos,int[][] arr) {
        int columnsNum = arr[0].length;
        if (pos.getColumnIndex() >= columnsNum - 2) {
            return;
        }
        if (arr[pos.getRowIndex()][pos.getColumnIndex() + 2] == 1) {
            Position rightNeighbor = new Position(pos.getRowIndex(), pos.getColumnIndex() + 2);
            occupied.add(rightNeighbor);
        }
        else {
            if (arr[pos.getRowIndex()][pos.getColumnIndex() + 1] == 1 ) {
                //arr[pos.row][pos.column +1]=0;
                Position vac2=new Position(pos.getRowIndex(),pos.getColumnIndex()+1);
                vacant.add(vac2);


            }
        }
    }

    /**
     * update the top neighbor of a specific cell
     * @param occupied- the list of all the neighbors of prev cells that contains 1
     * @param vacant- the list of all the neighbors of prev cells that contains 0
     * @param pos- the specific cell position
     * @param arr- the maze array
     */
    private void updateTopNeighbor(List < Position > occupied,List < Position > vacant,Position pos,int[][] arr) {
        if (pos.getRowIndex() < 2) {
            return;
        }
        if (arr[pos.getRowIndex() - 2][pos.getColumnIndex()] == 1) {
            Position topNeighbor = new Position(pos.getRowIndex() - 2, pos.getColumnIndex());
            occupied.add(topNeighbor);
        } else {
            if (arr[pos.getRowIndex() - 1][pos.getColumnIndex()] == 1) {
                //arr[pos.row-1][pos.column]=0;
                Position vac3=new Position(pos.getRowIndex()-1,pos.getColumnIndex());
                vacant.add(vac3);
            }
        }
    }

    /**
     * update the bottom neighbor of a specific cell
     * @param occupied- the list of all the neighbors of prev cells that contains 1
     * @param vacant- the list of all the neighbors of prev cells that contains 0
     * @param pos- the specific cell position
     * @param arr- the maze array
     */
    private void updateBottomNeighbor(List < Position > occupied,List < Position > vacant,Position pos,int[][] arr) {
        int rowsNum = arr.length;
        if (pos.getRowIndex() >= rowsNum - 2) {
            return;
        }
        if (arr[pos.getRowIndex() + 2][pos.getColumnIndex()] == 1) {
            Position bottomNeighbor = new Position(pos.getRowIndex() + 2, pos.getColumnIndex());
            occupied.add(bottomNeighbor);
        }
        else {
            if (arr[pos.getRowIndex() + 1][pos.getColumnIndex()] == 1) {
                //arr[pos.row + 1][pos.column] = 0;
                Position vac4=new Position(pos.getRowIndex()+1,pos.getColumnIndex());
                vacant.add(vac4);
            }
        }
    }
}




