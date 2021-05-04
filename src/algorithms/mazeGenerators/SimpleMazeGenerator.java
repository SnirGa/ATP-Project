package algorithms.mazeGenerators;
import java.util.*;
public class SimpleMazeGenerator extends AMazeGenerator {
    public SimpleMazeGenerator() {
    }

    /**
     *
     * @param rows- the number of rows in the created maze
     * @param columns- the number of columns in the created maze
     * @return simple Maze
     */
    @Override
    public Maze generate(int rows, int columns) {
        int[][] myMaze=new int[rows][columns];
        Random ran=new Random();
        Position start=new Position(0,0);
        Position end=new Position(rows-1,columns/2);

        for (int row = 0; row <rows ; row++) {
            for (int column = 0; column <columns ; column++) {
                myMaze[row][column]=ran.nextInt(2);
            }
        }

        for (int column = 0; column <columns/2 ; column++) {
            myMaze[0][column]=0;
            }
        for (int row=0;row<rows;row++){
            myMaze[row][columns/2]=0;
        }

        return new Maze(myMaze,start,end);
    }
}
