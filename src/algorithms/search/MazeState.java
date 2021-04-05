package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.*;
import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    private Position pos;
    public MazeState(Position pos) {
        //super(state);
        this.cameFrom=null;
        this.cost=0;
        this.pos=pos;
    }


    public void setParent (AState parent){
        this.cameFrom=parent;
    }

    public MazeState getParent(){
        return (MazeState) cameFrom;
    }

    public  void setCost(double newCost){
        this.cost=newCost;
    }

    public double getCost(){
        return this.cost;
    }

    public Position getPosition() {
        return pos;
    }

    @Override
    public boolean equals(Object obj) {
        MazeState ms=(MazeState)obj;
        return this.getPosition().equals(ms.getPosition());
    }

    @Override
    public int hashCode() {
        int row=this.getPosition().getRowIndex();
        int col=this.getPosition().getColumnIndex();
        return (int)Math.sqrt(Math.pow(row,2)+Math.pow(col,2));
    }

    @Override
    public int compareTo(AState as) {
        if (this.getCost()>as.getCost()) {
            return 1;
        }
        else if(this.getCost()<as.getCost()){
            return -1;
        }
        else {
            return 0;
        }

    }

    @Override
    public String toString() {
        return "{"+this.getPosition().getRowIndex()+","+this.getPosition().getColumnIndex()+"}";
    }
}
