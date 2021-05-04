package algorithms.maze3D;

public class Maze3D {
    private int[][][] Map;
    private Position3D StartPosition;
    private Position3D GoalPosition;

    public Maze3D(int[][][] map, Position3D startPosition, Position3D goalPosition) {
        Map = map;
        StartPosition = startPosition;
        GoalPosition = goalPosition;
    }

    public int[][][] getMap() {
        return Map;
    }

    /**
     * print the 3D maze
     */
    public void print(){
        System.out.println("{");
        for(int depth = 0; depth < Map.length; depth++){
            for(int row = 0; row < Map[0].length; row++) {
                System.out.print("{ ");
                for (int col = 0; col < Map[0][0].length; col++) {
                    if (depth == StartPosition.getDepthIndex() && row == StartPosition.getRowIndex() && col == StartPosition.getColumnIndex()) // if the position is the start - mark with S
                        System.out.print("S ");
                    else {
                        if (depth == GoalPosition.getDepthIndex() && row == GoalPosition.getRowIndex() && col == GoalPosition.getColumnIndex()) // if the position is the goal - mark with E
                            System.out.print("E ");
                        else
                            System.out.print(Map[depth][row][col] + " ");
                    }
                }
                System.out.println("}");
            }
            if(depth < Map.length - 1) {
                System.out.print("---");
                for (int i = 0; i < Map[0][0].length; i++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }

    public Position3D getStartPosition() {
        return StartPosition;
    }

    public Position3D getGoalPosition() {
        return GoalPosition;
    }
}
