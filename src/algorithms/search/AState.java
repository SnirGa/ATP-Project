package algorithms.search;


import algorithms.mazeGenerators.Position;

import java.util.Objects;

public abstract class AState implements Comparable<AState>{
    //protected String state;
    protected double cost;
    protected AState cameFrom;

    abstract public void setParent (AState parent);
    abstract public  MazeState getParent();
    abstract public  void setCost(double newCost);
    abstract public double getCost();
    abstract public int compareTo(AState as);

    abstract public boolean equals(Object obj);

    @Override
    public int hashCode() {
        return Objects.hash(cameFrom,cost);
    }


    //public AState(String state) {this.state = state;}
    public AState(){};
}
