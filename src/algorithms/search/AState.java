package algorithms.search;


import java.util.Objects;

public abstract class AState {
    protected double cost;
    protected AState cameFrom;

    abstract public void setParent (AState parent);
    abstract public  AState getParent();
    abstract public  void setCost(double newCost);
    abstract public double getCost();
    abstract public boolean equals(Object obj);

    @Override
    public int hashCode() {
        return Objects.hash(cameFrom,cost);
    }
    public AState(){}


}
