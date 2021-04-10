package algorithms.search;

import java.util.*;
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int visitedNodes;
    HashSet<AState> visited;
    ArrayList<AState> path;

    public ASearchingAlgorithm() {
        this.visitedNodes=0;
        visited=new HashSet<>();
        this.path=new ArrayList<>();

    }

     abstract public Solution solve(ISearchable s);
     public int getNumberOfNodesEvaluated(){ return this.visitedNodes;}
     public String getName(){return this.getClass().getSimpleName();}

    @Override
    public void setAstateParent(AState curr,AState parent) {
        curr.setParent(parent);
    }

    /**
     *
     * @param start- the start state of the maze
     * @param goal- the goal state of the maze
     * @return the path
     */
     protected ArrayList<AState> getPath(AState start,AState goal) {
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
        return path;
    }

    /**
     *
     * @param s- searchable object
     * @return the time it takes to run the solve method
     */
    public long measureAlgorithmTimeMillis(ISearchable s) {

        long timeMillisStart=System.currentTimeMillis();
        this.solve(s);
        long timeMillisEnd=System.currentTimeMillis();
        return  timeMillisEnd-timeMillisStart;
    }


}
