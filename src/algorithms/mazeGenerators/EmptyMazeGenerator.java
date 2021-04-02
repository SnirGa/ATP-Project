package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {
    public EmptyMazeGenerator() {
    }

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
        Maze maze=new Maze(myMaze,start,end);
        return maze;
    }
}
