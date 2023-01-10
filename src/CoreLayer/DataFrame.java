package CoreLayer;

import java.io.Serializable;

public class DataFrame implements Serializable {
    private int src;
    private int dest;
    private String msg;
    private int timestamp;


    public DataFrame(int src, int dest, String msg, int timestamp) {
        this.src = src;
        this.dest = dest;
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public int getSrc() {
        return src;
    }

    public int getDest() {
        return dest;
    }

    public String getMsg() {
        return msg;
    }

    public int getTimestamp() {
        return timestamp;
    }
}
