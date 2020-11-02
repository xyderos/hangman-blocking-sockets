package Game.Server.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class GameServer {

    private static final int PORT_NO = 8080;

    public static void main (String [] args){
        GameServer server = new GameServer();
        server.init();
    }

    private void init() {
        try{
            ServerSocket socket = new ServerSocket(PORT_NO);
            while (true){

                Socket link = socket.accept();
                startConnection(link);
            }
        }
        catch (IOException ioe)
        { System.out.println("No connection"); }
    }

    private void startConnection ( Socket link) throws SocketException{
        ClientHandler handler = new ClientHandler(link);
        Thread threadHandler = new Thread(handler);
        threadHandler.start();
    }

}
