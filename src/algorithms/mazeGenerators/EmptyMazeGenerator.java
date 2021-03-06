package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {
    public EmptyMazeGenerator() {
    }

    /**
     *
     * @param rows- the rows number in the generated maze
     * @param columns- the rows number in the generated maze
     * @return empty Maze
     */
    @Override
    public Maze generate(int rows, int columns) {
        int[][] myMaze=new int[rows][columns];
        Position start=new Position(0,0);
        Position end=new Position(rows-1,columns-1);

        for (int row = 0; row <rows ; row++) {
            for (int column = 0; column <columns ; column++) {
                myMaze[row][column]=0;
            }
        }
        return new Maze(myMaze,start,end);
    }
}
