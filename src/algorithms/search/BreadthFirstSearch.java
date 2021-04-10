package algorithms.search;
import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> openList;

    public BreadthFirstSearch() {
        openList=new LinkedList<>();

    }

    /**
     * removes an element from the openList
     * @return AState from the openList
     */
    protected AState popOpenList(){
        visitedNodes++;
        return openList.poll();
    }

    /**
     *
     * @param s-searchable maze
     * @return solution path of the maze
     */
    @Override
    public Solution solve(ISearchable s) {
        if (s==null){
            return null;
        }
        AState goal=BFSSearch(s);
        AState start=s.getStartState();
        path = getPath(start,goal);
        Solution solution = new Solution(path);
        return solution;
    }

    /**
     * BFS-Algorithm
     * @param s-searchable maze
     * @return AState if it is equal to the goal
     */
        private AState BFSSearch(ISearchable s) {
        visited = new HashSet<>();
        this.visitedNodes=0;
        AState start = s.getStartState();
        AState goal = s.getGoalState();
        visited.add(start); //marks start as visited
        openList.add(start);
        AState nextInLine;
        ArrayList<AState> successors;
        while (!(openList.isEmpty())) {
            nextInLine = popOpenList();
            if (nextInLine.equals(goal)) {

                return nextInLine;
            }
            successors = s.getAllSuccessors(nextInLine);
            for(int i = 0; i < successors.size(); i++) {
                if (!(visited.contains(successors.get(i)))) {
                    this.setAstateParent(successors.get(i),nextInLine);
                    visited.add(successors.get(i));
                    openList.add(successors.get(i));
                }
            }
        }
        return null;
    }



}