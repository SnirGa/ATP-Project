package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable s);
    int getNumberOfNodesEvaluated();
    String getName();
    void setAStateCost(AState as);
    void setAstateParent(AState curr,AState parent);
    long measureAlgorithmTimeMillis(ISearchable domain);
}
