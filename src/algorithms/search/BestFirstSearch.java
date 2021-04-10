package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {
    public BestFirstSearch() {
        this.openList=new PriorityQueue<>(new CostComparator());
    }

}

/**
 * this class used as comparator factor for the priorityQueue
 */
class CostComparator implements Comparator<AState> {
    public int compare(AState as1,AState as2) {
        if (as1.getCost()>as2.getCost()) {
            return 1;
        }
        else if(as1.getCost()<as2.getCost()){
            return -1;
        }
        else {
            return 0;
        }

        }
}