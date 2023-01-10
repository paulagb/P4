package CoreLayer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Connection {
    private ServerSocket server;
    private ArrayList<Socket> sockets;
    private int myId;
    private int numConnections;


    public Connection(int id, int numConnections) {
        sockets = new ArrayList<>();

        this.numConnections = numConnections;
    }
    public int getId(){
        return myId;
    }

    public void connectTo (int port){
        myId = port;
        boolean connection = false;
        while (!connection){
            try {
                Socket socket = new Socket("localhost", port);
                sockets.add(socket);
                connection = true;
                System.out.println("CoreLayer.Connection to " + port + "Succesfull");
            } catch (IOException ioException) {
                //System.out.println("waiting for server to accept");
                //ioException.printStackTrace();
            }
        }

    }

    public void openServerLeader(int port){
        try {
            server = new ServerSocket(port);
            System.out.println("Server " + port + " succesfull");
            acceptConnections();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public void openServer(int port){
        try {
            server = new ServerSocket(port);
          //  System.out.println("Server" + port + "succesfull");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void acceptConnections () {
        while (numConnections > 1){
            Socket socket = null;
            try {
                socket = server.accept();
                numConnections--;
               // System.out.println("connections" + socket.getPort());
                Thread s = new Thread(new SocketThread(this, socket, myId));
                s.start();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void sendMsg(String msg, int dest){
        DataFrame dataFrame = new DataFrame(myId, dest, msg, 1);
        try {
            System.out.println(sockets.indexOf(dest));
            ObjectOutputStream out = new ObjectOutputStream(sockets.get(sockets.indexOf(dest)).getOutputStream());
            out.writeObject(dataFrame);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public void sendUpdate(){
        for (Socket socket : sockets) {
            System.out.println("myid" + myId);
            DataFrame dataFrame = new DataFrame(myId, socket.getPort(), "update", 1);
            try {
                System.out.println(socket.getPort());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(dataFrame);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


}
