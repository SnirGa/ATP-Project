package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D {
    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long timeMillisStart=System.currentTimeMillis();
        this.generate(depth,row,column);
        long timeMillisEnd=System.currentTimeMillis();
        return  timeMillisEnd-timeMillisStart;
    }
}
