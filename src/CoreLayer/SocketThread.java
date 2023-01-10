package CoreLayer;

import java.io.*;
import java.net.Socket;

public class SocketThread implements Runnable{
    private final Connection connection;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private int ack;
    private int myPort;

    public SocketThread(Connection connection, Socket socket, int port)  {
        this.connection = connection;
        this.socket = socket;
        myPort = port;
    }



    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                Object o = in.readObject();
                manageMsg(o);
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }

    public void manageMsg(Object o){
        // creae dataframe and send it to the source of this message.
        //TODO es necessary llegirho com a object si sempre enviare DataFrames?

        DataFrame dataFrame = (DataFrame) o;
        System.out.println("Received message: " + "src: " + dataFrame.getSrc()
                + "dest: " + dataFrame.getDest() + " msg: " + dataFrame.getMsg());

        if(dataFrame.getMsg().equals("ack")){
            // acknowledge message
            ack++;
            System.out.println("ack" +ack);

        }else if(dataFrame.getMsg().equals("update")){
            connection.sendMsg("ack", dataFrame.getSrc());
            //out.writeObject(new CoreLayer.DataFrame(connection.getId(), dataFrame.getSrc(), "ack", 1));
        }
        // update num_updates
        //connection.increaseNumUpdates();
    }
}
