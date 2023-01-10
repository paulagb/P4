package CoreLayer;
/* ARGS
0-> myId
1-> myPort
2-> port1
3-> port2
 */

public class Main {
    public static void main(String[] args) {

        final int NUM_CONNECTIONS = 3;
        int myId = Integer.parseInt(args[0]);

        Connection connection = new Connection(myId, NUM_CONNECTIONS);
        NodeA nodeA = new NodeA(connection, myId);
       nodeA.startConnections(args);



    }



}
