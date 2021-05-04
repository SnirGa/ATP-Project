package algorithms.mazeGenerators;

import java.util.ArrayList;

public class Maze {
    private int[][] array;
    private Position start;
    private Position end;

    public Maze(int[][] array,Position start,Position end) {
        this.array = array;
        this.start=start;
        this.end=end;
    }
    public Position getStartPosition(){
        return start;
    }
    public Position getGoalPosition(){
        return end;
    }

    public int[][] getArray() {
        return array;
    }


    /**
     * prints the maze
     */
    public void print(){
        String prt="";
        for (int row = 0; row < array.length ; row++) {
            prt+="{";
            for (int col = 0; col <array[0].length ; col++) {
                if(col==start.getColumnIndex() && row==start.getRowIndex()){
                    prt+=" S";
                }
                else if(col==end.getColumnIndex() && row==end.getRowIndex()) {
                    prt += " E";
                }
                else{
                    prt+=" ";
                    prt+=array[row][col];
                }
            }
            prt+=" }\n";
        }
        System.out.println(prt);
    }

    public byte[] toByteArray(){
        byte[] BrowsArr=intToByteArr(this.getArray().length);
        byte[] BcolsArr=intToByteArr(this.getArray()[0].length);
        byte[] BMazeArr=arr2DToByteArr(this.getArray());
        byte[] startRow=intToByteArr(this.getStartPosition().getRowIndex());
        byte[] startCol=intToByteArr(this.getStartPosition().getColumnIndex());
        byte[] goalRow=intToByteArr(this.getGoalPosition().getRowIndex());
        byte[] goalCol=intToByteArr(this.getGoalPosition().getColumnIndex());
        int sum=BrowsArr.length+BcolsArr.length+BMazeArr.length+startRow.length+startCol.length+goalRow.length+goalCol.length;
        byte[] unite=new byte[sum+6];

        //add the lengths of the maze info
        unite[0]=(byte) BrowsArr.length; //rows
        unite[1]=(byte)BcolsArr.length; //columns
        unite[2]=(byte) startRow.length; //startPos row
        unite[3]=(byte) startCol.length; //startPos col
        unite[4]=(byte) goalRow.length; //goalPos row
        unite[5]=(byte) goalCol.length; //goalPos col

        int loc=6;
        System.arraycopy(BrowsArr,0,unite,loc,unite[0]);
        loc+=unite[0];
        System.arraycopy(BcolsArr,0,unite,loc,unite[1]);
        loc+=unite[1];
        System.arraycopy(startRow,0,unite,loc,unite[2]);
        loc+=unite[2];
        System.arraycopy(startCol,0,unite,loc,unite[3]);
        loc+=unite[3];
        System.arraycopy(goalRow,0,unite,loc,unite[4]);
        loc+=unite[4];
        System.arraycopy(goalCol,0,unite,loc,unite[5]);
        loc+=unite[5];

        //added!!
        System.arraycopy(BMazeArr,0,unite,loc,BMazeArr.length);
        return unite;
    }

    public Maze(byte[] b){
        //constructor that gets byte array and builds the maze
        //need t        System.out.println(x[0]);o add
        int rowsArrLength=byteToInt(b[0]);
        int colsArrLength=byteToInt(b[1]);
        int StartRowLength=byteToInt(b[2]);
        int StartColLength=byteToInt(b[3]);
        int GoalRowLength=byteToInt(b[4]);
        int GoalColLength=byteToInt(b[5]);

        byte[] BrowsArr=new byte[rowsArrLength];
        int loc=6;
        System.arraycopy(b,loc,BrowsArr,0,rowsArrLength);
        loc+=rowsArrLength;

        byte[] BColsArr=new byte[colsArrLength];
        System.arraycopy(b,loc,BColsArr,0,colsArrLength);
        loc+=colsArrLength;

        byte[] BStartRowArr=new byte[StartRowLength];
        System.arraycopy(b,loc,BStartRowArr,0,StartRowLength);
        loc+=StartRowLength;

        byte[] BStartColArr=new byte[StartColLength];
        System.arraycopy(b,loc,BStartColArr,0,StartColLength);
        loc+=StartColLength;

        byte[] BGoalRowArr=new byte[GoalRowLength];
        System.arraycopy(b,loc,BGoalRowArr,0,GoalRowLength);
        loc+=GoalRowLength;

        byte[] BGoalColArr=new byte[GoalColLength];
        System.arraycopy(b,loc,BGoalColArr,0,GoalColLength);
        loc+=GoalColLength;


        int rows=byteArrToInt(BrowsArr);
        int cols=byteArrToInt(BColsArr);
        int startRow=byteArrToInt(BStartRowArr);
        int startColumn=byteArrToInt(BStartColArr);
        int goalRow=byteArrToInt(BGoalRowArr);
        int goalColumn=byteArrToInt(BGoalColArr);
        byte[] BMaze=new byte[rows*cols];
        System.arraycopy(b,loc,BMaze,0,rows*cols);
        int k=0;

        Position startPos=new Position(startRow,startColumn);
        Position goalPos=new Position(goalRow,goalColumn);
        this.start=startPos;
        this.end=goalPos;
        this.array=new int[rows][cols];
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[0].length;j++){
                this.array[i][j]=BMaze[k];
                k++;
            }
        }





    }

    public byte[] intToByteArr(int conv){
        ArrayList<Byte> temp=new ArrayList<>();
        while(conv>255){
            byte b=(byte)255;
            temp.add(b);
            conv=conv-255;
        }
        byte b=(byte) conv;
        temp.add(b);
        byte[] byteArr=new byte[temp.size()];
        for (int i=0;i<temp.size();i++){
            byteArr[i]=temp.get(i);
        }
        return byteArr;
    }

    private byte[] arr2DToByteArr(int[][] arr){
        ArrayList<Byte> temp=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                byte x=(byte)arr[i][j];
                temp.add(x);
            }
        }
        byte[] byteArr=new byte[temp.size()];
        for (int i=0;i<temp.size();i++){
            byteArr[i]=temp.get(i);
        }
        return byteArr;
    }

    private int byteToInt(byte b){
        return b&0xFF;
    }

    private int byteArrToInt(byte[] arr){
        int count=0;
        for(int i=0;i<arr.length;i++){
            count+=byteToInt(arr[i]);
        }
        return count;
    }
}
