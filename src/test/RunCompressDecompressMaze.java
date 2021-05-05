package test;
import IO.SimpleCompressorOutputStream;
import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.*;
import java.util.Arrays;


public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(1000, 1000); //Generate new maze




        try {
// save maze to a file
            OutputStream out = new SimpleCompressorOutputStream(new FileOutputStream(mazeFileName));
            byte[] temp =maze.toByteArray();
            out.write(temp);

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
       }
        byte savedMazeBytes[] = new byte[0];
       try {
//read maze from file
            InputStream in = new SimpleDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
       }
         Maze loadedMaze = new Maze(savedMazeBytes);
        maze.print();
        loadedMaze.print();

        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s",areMazesEquals));
    //maze should be equal to loadedMaze
   }
}
