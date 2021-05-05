package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream out){
        this.out=out;
    }

    private int[] ConvertByteArrToIntArr(byte[] b){
        //translates the bytes sizes:
        int rowsArrLength=byteToInt(b[0]);
        int colsArrLength=byteToInt(b[1]);
        int StartRowLength=byteToInt(b[2]);
        int StartColLength=byteToInt(b[3]);
        int GoalRowLength=byteToInt(b[4]);
        int GoalColLength=byteToInt(b[5]);

        try {
            int loc = 0;
            out.write(b, loc, 6); //the sizes of the arrays (0-5)
            loc += 6;
            out.write(b, loc, rowsArrLength);
            loc += rowsArrLength;
            out.write(b, loc, colsArrLength);
            loc += colsArrLength;
            out.write(b, loc, StartRowLength);
            loc += StartRowLength;
            out.write(b, loc, StartColLength);
            loc += StartColLength;
            out.write(b, loc, GoalRowLength);
            loc += GoalRowLength;
            out.write(b, loc, GoalColLength);
            loc += GoalColLength;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void LempelZivEncode(byte[] b,int location){
        int index=0;
        Dict dict=new Dict();
        dict.add(0,"0");
        dict.add(1,"1");
        String str="";
        for (int i = location; i <b.length ; i++) {

        }
    }

    private int IntFromByteArray(byte[] Barr,int location,int distance){
        int count=0;
        for(int i=location;i<location+distance;i++){
            count+=byteToInt(Barr[i]);
        }
        return count;

    }

    private int byteToInt(byte b){
        return b&0xFF;
    }
    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {


    }

    private class Dict{
        private Map<String,Integer> Sindex;
        private Map<Integer,String> Iindex;

        private Dict(){
            this.Sindex=new HashMap<>();
            this.Iindex=new HashMap<>();
        }

        private void add(int x,String y){
            Sindex.put(y,x);
            Iindex.put(x,y);
        }

        private String getValueByKey(int x){
            return Iindex.get(x);
        }

        private  int getValueByKey(String y){
            return Sindex.get(y);
        }

        private Map<String, Integer> getStringDict(){
            return Sindex;
        }

        private Map<Integer, String> getIntDict(){
            return Iindex;
        }

        private boolean contains(String y){
            return Sindex.containsKey(y);
        }

        private boolean contains(int x){
            return Sindex.containsKey(x);
        }

    }
}
