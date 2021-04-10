package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    Stack<AState> openList;

    public DepthFirstSearch() {
        this.openList = new Stack<>();
    }

    /**
     *
     * @param s-searchable maze
     * @return solution path of the maze
     */
    public Solution solve(ISearchable s) {
        AState start = s.getStartState();
        AState goal = DFSSearch(s);
        //if (goal==null || start==null){

        //}
        this.path = getPath(start, goal);
        Solution solution = new Solution(path);
        return solution;
    }

    /**
     * DFS-Algorithm
     * @param s-searchable maze
     * @return AState if it is equal to the goal
     */
    private AState DFSSearch(ISearchable s) {
        if (s == null) {
            throw new IllegalArgumentException("domain must have a value");
        }
        this.visited=new HashSet<>();
        this.visitedNodes=0;
        AState goal = s.getGoalState();
        AState NextInLine = s.getStartState();
        openList.push(NextInLine);
        visited.add(NextInLine);
        this.visitedNodes++;
        while (!(openList.isEmpty())) {
            NextInLine = openList.pop();
            if (NextInLine.equals(goal)) {
                return NextInLine;
            }

                ArrayList<AState> successors = s.getAllSuccessors(NextInLine);
                for (int i = 0; i < successors.size(); i++) {
                    successors.get(i).setParent(NextInLine);
                    if (!visited.contains(successors.get(i))) {
                        visited.add(successors.get(i));
                        this.visitedNodes++;
                        openList.push(successors.get(i));
                    }

                }


        }
        return null;
    }
}
