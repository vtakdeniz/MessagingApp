package messageapp;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


class ServerThread extends Thread {

    public void run() {
        while (!Server.serverSocket.isClosed()) {
            try {
                Server.print("Waiting for client");
                Socket clientSocket = Server.serverSocket.accept();
                Server.print("One client accepted");
                SClient Sclient = new SClient(clientSocket, Server.client_id);
                
                SRoom s1 = new SRoom();
                s1.room_id=1;
                s1.room_name="test";
                SRoom s2 = new SRoom();
                s2.room_id=1;
                s2.room_name="test";
                Server.Rooms.add(s1);
                Server.Rooms.add(s2);
                Message rooms_message = new Message(Message.Type.LIST);
                rooms_message.cast_type = Message.Cast_Type.ROOM_LIST;
                rooms_message.content=Server.Rooms;
                Server.Send(Sclient, rooms_message);
                
                Server.client_id++;
                Server.Clients.add(Sclient);
                Sclient.listenThread.start();

            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

public class Server {

    public static ServerSocket serverSocket;
    public static int client_id = 0;

    public static int port = 0;

    public static ServerThread main_thread;

    public static ArrayList<SClient> Clients = new ArrayList<>();
    public static ArrayList<SRoom> Rooms = new ArrayList<>();
    
    public static void Start(int openport) {
        try {
            Server.port = openport;
            Server.serverSocket = new ServerSocket(Server.port);

            Server.main_thread = new ServerThread();
            Server.main_thread.start();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void Send(SClient cl, Object msg) {

        try {
            cl.socketOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public static void BroadCast(SClient cl, Object msg) {

        try {
            cl.socketOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public static void print(String msg) {

        System.out.println(msg);

    }



}