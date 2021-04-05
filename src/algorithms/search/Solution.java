package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class Solution {
    ArrayList<AState> solutionPath;
    public Solution(ArrayList<AState> solutionPath){
        this.solutionPath=solutionPath;
    }

    public ArrayList<AState>getSolutionPath(){
        return this.solutionPath;
    }
}
