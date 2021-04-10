package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    public AMazeGenerator() {
    }

    public abstract Maze generate(int rows, int columns);


    /**
     *
      * @param rows- the rows number of the maze
     * @param columns- the columns number of the maze
     * @return time in milliseconds for generating maze
     */
    public long measureAlgorithmTimeMillis(int rows, int columns) {

        long timeMillisStart=System.currentTimeMillis();
        this.generate(rows,columns);
        long timeMillisEnd=System.currentTimeMillis();
        return  timeMillisEnd-timeMillisStart;
    }

}
