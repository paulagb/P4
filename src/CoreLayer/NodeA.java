package CoreLayer;

public class NodeA {
    private int num_updates;
    private Connection connection;
    private int myId;


    public NodeA(Connection connection, int id) {
        this.connection = connection;
        num_updates = 0;
    }

    public void startConnections(String[] args){
        if (myId == 1) {
            connection.openServerLeader(Integer.parseInt(args[1]));
            connection.connectTo(Integer.parseInt(args[2]));
            connection.connectTo(Integer.parseInt(args[3]));
        } else{
            connection.openServer(Integer.parseInt(args[1]));
            connection.connectTo(Integer.parseInt(args[2]));
            connection.connectTo(Integer.parseInt(args[3]));
            connection.acceptConnections();
        }
        System.out.println("connections completed");
        connection.sendUpdate();
    }
}
