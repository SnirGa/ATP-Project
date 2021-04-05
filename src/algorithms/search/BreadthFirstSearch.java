package algorithms.search;
import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    public BreadthFirstSearch() {
    }

    @Override
    public Solution solve(ISearchable s) {
        AState goal=BFSSearch(s);
        AState start=s.getStartState();
        ArrayList<AState> path = getPath(start,goal);
        Solution solution = new Solution(path);
        return solution;
    }

    private AState BFSSearch(ISearchable s) {
        HashSet<AState> visited = new HashSet<>();
        AState start = s.getStartState();
        AState goal = s.getGoalState();
        visited.add(start); //marks start as visited
        openList.add(start);
        //ArrayList<AState> successors = s.getAllSuccessors(start);
        //openList.addAll(successors);
        //successors.clear();
        //AState current = popOpenList();
        AState nextInLine;
        ArrayList<AState> successors;
        while (!(openList.isEmpty())) {
            nextInLine = popOpenList();
            if (nextInLine.equals(goal)) {

                return nextInLine;
            }
            successors = s.getAllSuccessors(nextInLine);
            for (int i = 0; i < successors.size(); i++) {
                if (!(visited.contains(successors.get(i)))) {
                    this.setAstateParent(successors.get(i),nextInLine);
                    this.setAStateCost(successors.get(i));
                    //successors.get(i).cost += 1;
                    //successors.get(i).cameFrom = nextInLine;
                    visited.add(successors.get(i));
                    openList.add(successors.get(i));
                }
            }
        }
        return null;
    }


    private ArrayList<AState> getPath(AState start,AState goal) {
        //AState start = s.getStartState();
        //AState goal = s.getGoalState();
        ArrayList<AState> path = new ArrayList<>();
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

    @Override
    public void setAStateCost(AState as) {
        if (as.getParent()==null){
            as.setCost(0);
        }
        else {
            as.setCost(as.getParent().getCost() + 1);
        }
    }


}