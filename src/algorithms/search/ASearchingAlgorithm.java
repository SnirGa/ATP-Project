package algorithms.search;

import java.util.PriorityQueue;
import java.util.*;
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int visitedNodes;
    public ASearchingAlgorithm() {
        this.visitedNodes=0;
    }

     abstract public Solution solve(ISearchable s);
     public int getNumberOfNodesEvaluated(){ return this.visitedNodes;}
     public String getName(){return this.getClass().getSimpleName();}

    @Override
    public void setAstateParent(AState curr,AState parent) {
        curr.setParent(parent);
    }

    public void setAStateCost(AState as) {
        if (as.getParent()==null){
            as.setCost(0);
        }
        else {
            as.setCost(as.getParent().getCost() + as.getCost());
        }
    }

     protected ArrayList<AState> getPath(AState start,AState goal) {
        //AState start = s.getStartState();
        //AState goal = s.getGoalState();
         ArrayList<AState> path = new ArrayList<>();
         if (start==null || goal==null){
             return path;
         }
        if (goal.cameFrom == null) {
            return path;
        }
        Stack<AState> stack = new Stack<>();
        AState curr = goal;
        while (!(curr.equals(start))) {
            stack.add(curr);
            curr = curr.cameFrom;
        }
        stack.add(curr);
        while (!stack.isEmpty()){
            AState temp=stack.pop();
            path.add(temp);
        }
        System.out.println(stack.toString());
        return path;
    }

    public long measureAlgorithmTimeMillis(ISearchable s) {

        long timeMillisStart=System.currentTimeMillis();
        this.solve(s);
        long timeMillisEnd=System.currentTimeMillis();
        return  timeMillisEnd-timeMillisStart;
    }


}
