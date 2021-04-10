package algorithms.search;
import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position pos;
    public MazeState(Position pos) {
        //super(state);
        this.cameFrom=null;
        this.cost=0;
        this.pos=pos;
    }

    public MazeState(Position pos,int cost) {
            //super(state);
            this.cameFrom=null;
            this.cost=cost;
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

    /**
     *
     * @param obj- obj need to be MazeState
     * @return true if equal,else-false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MazeState) {
            MazeState ms = (MazeState) obj;
            return this.getPosition().equals(ms.getPosition());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int row=this.getPosition().getRowIndex();
        int col=this.getPosition().getColumnIndex();
        return (int)Math.sqrt(Math.pow(row,2)+Math.pow(col,2));
    }

    @Override
    public String toString() {
        return "{"+this.getPosition().getRowIndex()+","+this.getPosition().getColumnIndex()+"}";
    }
}
