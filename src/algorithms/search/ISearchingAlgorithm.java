package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable s);
    int getNumberOfNodesEvaluated();
    String getName();
    void setAstateParent(AState curr,AState parent);

    long measureAlgorithmTimeMillis(ISearchable domain);
}

