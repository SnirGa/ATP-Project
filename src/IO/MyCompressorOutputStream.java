package IO;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {


    }

    @Override
    public void write(byte[] b) throws IOException {
        //translates the bytes sizes:
        int rowsArrLength=byteToInt(b[0]);
        int colsArrLength=byteToInt(b[1]);
        int StartRowLength=byteToInt(b[2]);
        int StartColLength=byteToInt(b[3]);
        int GoalRowLength=byteToInt(b[4]);
        int GoalColLength=byteToInt(b[5]);

        try{
            int loc=0;
            out.write(b,loc,6); //the sizes of the arrays (0-5)
            loc+=6;
            out.write(b,loc,rowsArrLength);
            loc+=rowsArrLength;
            out.write(b,loc,colsArrLength);
            loc+=colsArrLength;
            out.write(b,loc,StartRowLength);
            loc+=StartRowLength;
            out.write(b,loc,StartColLength);
            loc+=StartColLength;
            out.write(b,loc,GoalRowLength);
            loc+=GoalRowLength;
            out.write(b,loc,GoalColLength);
            loc+=GoalColLength;

            byte prev;
            byte curr;
            int count=0;
            if(b[loc]==1){ //starts from zero times zero
                out.write(0);
            }
            for(int i=loc;i<b.length;i++){
                if(i==loc){ //first iteration
                    count++;
                    continue;
                }

                else{
                    prev=b[i-1];
                    curr=b[i];
                    if(curr==prev){
                        if (count==255){
                            out.write(count);
                            out.write(0);
                            count=0;
                            count++;
                        }
                        else {
                            count++; //repeat byte
                        }

                    }
                    else{

                        out.write(count);
                        count=1;
                        }
                    if(i==loc+GoalColLength-1){//last iteration
                        out.write(count);
                    }


                }
            }
            out.write(count);
            }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static void main(String[] args){
        byte x=(byte) 1;
        int y=x&0xFF;
        String mazeFileName = "HelloWorld.txt";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(100, 100); //Generate new maze
        try {
// save maze to a file
            OutputStream out =new FileOutputStream(mazeFileName);
            out.write(120);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int byteToInt(byte b){
        return b&0xFF;
    }

    private int byteArrToInt(byte[] arr){
        int count=0;
        for(int i=0;i<arr.length;i++){
            count+=byteToInt(arr[0]);
        }
        return count;
    }


}
