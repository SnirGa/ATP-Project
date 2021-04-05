package algorithms.search;

import java.util.PriorityQueue;
import java.util.*;
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected PriorityQueue<AState> openList;
    private int visitedNodes;
    public ASearchingAlgorithm() {
        openList=new PriorityQueue<>();
        this.visitedNodes=0;
    }
    protected AState popOpenList(){
        visitedNodes++;
        return openList.poll();
    }
     public Solution solve(ISearchable s){
        return null;
     }
     public int getNumberOfNodesEvaluated(){ return this.visitedNodes;}
     public String getName(){return this.getClass().getSimpleName();}

    @Override
    public void setAstateParent(AState curr,AState parent) {
        curr.setParent(parent);
    }

    public long measureAlgorithmTimeMillis(ISearchable s) {

        long timeMillisStart=System.currentTimeMillis();
        this.solve(s);
        long timeMillisEnd=System.currentTimeMillis();
        return  timeMillisEnd-timeMillisStart;
    }
}
