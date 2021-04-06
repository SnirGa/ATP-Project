package algorithms.search;
import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected PriorityQueue<AState> openList;
    ArrayList<AState> path;
    HashSet<AState> visited;


    public BreadthFirstSearch() {
        this.path=new ArrayList<>();
        this.visited=new HashSet<>();
        openList=new PriorityQueue<>();

    }

    protected AState popOpenList(){
        visitedNodes++;
        return openList.poll();
    }

    @Override
    public Solution solve(ISearchable s) {
        if (s==null){
            throw new IllegalArgumentException("s must have a value");
        }
        AState goal=BFSSearch(s);
        AState start=s.getStartState();
        path = getPath(start,goal);
        Solution solution = new Solution(path);
        return solution;
    }

    private AState BFSSearch(ISearchable s) {
        visited = new HashSet<>();
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



}