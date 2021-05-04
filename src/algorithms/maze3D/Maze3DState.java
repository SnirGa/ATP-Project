package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

public class Maze3DState extends AState{
    private Position3D pos;
    public Maze3DState(Position3D pos) {
        this.cameFrom=null;
        this.cost=0;
        this.pos=pos;
    }

    public Maze3DState(Position3D pos,int cost) {
        this.cameFrom=null;
        this.cost=cost;
        this.pos=pos;
    }



    public void setParent (AState parent){
        this.cameFrom=parent;
    }

    public Maze3DState getParent(){
        return (Maze3DState) cameFrom;
    }

    public  void setCost(double newCost){
        this.cost=newCost;
    }

    public double getCost(){
        return this.cost;
    }

    public Position3D getPosition() {
        return pos;
    }

    /**
     *
     * @param obj- obj need to be 3D MazeState
     * @return true if equal,else-false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Maze3DState) {
            Maze3DState ms = (Maze3DState) obj;
            return this.getPosition().equals(ms.getPosition());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int row=this.getPosition().getRowIndex();
        int col=this.getPosition().getColumnIndex();
        int depth=this.getPosition().getDepthIndex();
        return  row+col+depth;
    }

    @Override
    public String toString() {
        return "{"+this.getPosition().getDepthIndex()+","+this.getPosition().getRowIndex()+","+this.getPosition().getColumnIndex()+"}";
    }
}
