package algorithms.search;

import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    @Test
    void getName() throws Exception {
        BestFirstSearch BeFS = new BestFirstSearch();
        assertEquals("BestFirstSearch", BeFS.getName());
    }

    @Test
    void checkNotNull_solve1() throws Exception {
        BestFirstSearch BeFS = new BestFirstSearch();
        IMazeGenerator mazeGen = new MyMazeGenerator();
        Maze maze = mazeGen.generate(1000, 1000);
        ISearchable domain = new SearchableMaze(maze);
        Solution sol = BeFS.solve(domain);
        assertNotNull(sol);
    }

    @Test
    void checkEvaluatedNodes_solve1() throws Exception {
        BestFirstSearch BeFS = new BestFirstSearch();
        IMazeGenerator mazeGen = new MyMazeGenerator();
        Maze maze = mazeGen.generate(1000, 1000);
        ISearchable domain = null;
        Solution sol = BeFS.solve(domain);
        assertEquals(0, BeFS.getNumberOfNodesEvaluated());
    }

    void checkEvaluatedNodes_solve2() throws Exception {
        BestFirstSearch BeFS = null;
        IMazeGenerator mazeGen = new MyMazeGenerator();
        Maze maze = mazeGen.generate(1000, 1000);
        ISearchable domain = new SearchableMaze(maze);
        Solution sol = BeFS.solve(domain);
        assertEquals(0, BeFS.getNumberOfNodesEvaluated());
    }

    void checkEvaluatedNodes_solve3() throws Exception {
        BestFirstSearch BeFS = null;
        IMazeGenerator mazeGen = new MyMazeGenerator();
        Maze maze = mazeGen.generate(1000, 1000);
        ISearchable domain = null;
        Solution sol = BeFS.solve(domain);
        assertEquals(0, BeFS.getNumberOfNodesEvaluated());
    }

    @Test
    void checkNotNull_solve2() throws Exception {
        BestFirstSearch BeFS = new BestFirstSearch();
        IMazeGenerator mazeGen = new MyMazeGenerator();
        Maze maze = mazeGen.generate(2, 2);
        ISearchable domain = new SearchableMaze(maze);
        Solution sol = BeFS.solve(domain);
        assertNotNull(sol);
    }

    @Test
    void checkNotNull_solve3() throws Exception {
        BestFirstSearch BeFS = new BestFirstSearch();
        IMazeGenerator mazeGen = new MyMazeGenerator();
        Maze maze = mazeGen.generate(3, 4);
        ISearchable domain = new SearchableMaze(maze);
        Solution sol = BeFS.solve(domain);
        assertNotNull(sol);
    }

    @Test
    void checkPathNotNull() throws Exception {
        BestFirstSearch BeFS = new BestFirstSearch();
        IMazeGenerator mazeGen = new MyMazeGenerator();
        Maze maze = mazeGen.generate(2, 2);
        ISearchable domain = new SearchableMaze(maze);
        Solution sol = BeFS.solve(domain);
        assertNotNull(sol.getSolutionPath());
    }

    @Test
    void solve5() throws Exception {
        BestFirstSearch BeFS = new BestFirstSearch();
        IMazeGenerator mazeGen = new EmptyMazeGenerator();
        Maze maze = mazeGen.generate(1000, 1000);
        ISearchable domain = new SearchableMaze(maze);
        Solution sol = BeFS.solve(domain);
        assertNotNull(sol.getSolutionPath());
    }

    @Test
    void solve6() throws Exception {
        BestFirstSearch BeFS = new BestFirstSearch();
        IMazeGenerator mazeGen = new SimpleMazeGenerator();
        Maze maze = mazeGen.generate(1, 1);
        ISearchable domain = new SearchableMaze(maze);
        Solution sol = BeFS.solve(domain);
        assertNotNull(sol.getSolutionPath());
    }

    @Test
    void checkTime() throws Exception{
        BestFirstSearch BeFS=new BestFirstSearch();
        IMazeGenerator mazeGen=new MyMazeGenerator();
        Maze maze=mazeGen.generate(1000,1000);
        ISearchable domain=new SearchableMaze(maze);
        long solTime=BeFS.measureAlgorithmTimeMillis(domain);
        assertTrue(solTime<60000);
    }



}