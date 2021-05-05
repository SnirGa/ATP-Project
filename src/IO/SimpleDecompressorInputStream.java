package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int j;
        int k=0;
        ArrayList<Byte> byteArry=new ArrayList<>();

        b[0]=(byte)in.read(); //rowsArrLength
        b[1]=(byte)in.read(); //colsArrLength
        b[2]=(byte)in.read(); //StartRowLength
        b[3]=(byte)in.read(); //StartColLength
        b[4]=(byte)in.read(); //GoalRowLength
        b[5]=(byte)in.read(); //GoalColLength
        int loc=6;
        int sum=byteToInt(b[0])+byteToInt(b[1])+byteToInt(b[2])+byteToInt(b[3])+byteToInt(b[4])+byteToInt(b[5]);
        while (loc<sum+6){
            b[loc]=(byte) in.read();
            loc++;
        }

        boolean flag=true; //if true->then represents zero,else represent ones
        int i;
        while((loc<b.length-1)) {
            i = in.read();
            for (int p = 0; p < i; p++) {
                if(loc>=b.length){
                    break;
                }
                if (flag) {
                    b[loc] = (byte) 0;
                }
                else {
                    b[loc] =(byte) 1;
                }
                loc++;
            }
            flag = !flag;
        }
        return 0;
        }

        private int byteToInt(byte b){
            return b&0xFF;
        }
    }
