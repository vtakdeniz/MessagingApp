package messageapp;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
                
                
                Message rooms_message = new Message(Message.Type.LIST);
                rooms_message.cast_type = Message.Cast_Type.ROOM_LIST;
                rooms_message.content=Server.castRooms(Server.Rooms);
                
                Server.Send(Sclient, rooms_message);
                
                /*for (SRoom rooms : Server.Rooms) {
                rooms.clients.add(Sclient);
                }*/
                
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
    public static int room_id = 0;
    
    public static int port = 0;
    public static Map<Integer, SRoom> intToRoomMap = new HashMap<Integer, SRoom>();
    public static Map<Integer, SClient> intToClientMap = new HashMap<Integer, SClient>();
    public static ServerThread main_thread;
    public static ArrayList<SClient> Clients = new ArrayList<>();
    public static ArrayList<SRoom> Rooms = new ArrayList<>();
    public static void Start(int openingport) {
        try {
                SRoom s1 = new SRoom();
                s1.room_id=room_id;
                s1.room_name="test1";
                room_id++;
                SRoom s2 = new SRoom();
                s2.room_id=room_id;
                s2.room_name="test2";
                room_id++;
                mapRoom(s1);
                mapRoom(s2);
                Server.Rooms.add(s1);
                Server.Rooms.add(s2);
            Server.port = openingport;
            Server.serverSocket = new ServerSocket(Server.port);

            Server.main_thread = new ServerThread();
            Server.main_thread.start();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void broadcastToRoom(CRoom cRoom,SClient sClient,Object message){
         SRoom sRoom = intToRoomMap.get(cRoom.room_id);
         ArrayList<SClient> room_clients = sRoom.clients;
         if (!room_clients.contains(sClient)) {
             Message m= new Message(Message.Type.CONN_REQ);
             
             return;
         }
         
         for (SClient room_client : room_clients) {
             if(sClient==room_client) continue;
             Send(room_client, message);
         }
    }
    
    public static void handleRequest(SClient sClient,Message message){
        SRoom sRoom = intToRoomMap.get(((CRoom)message.content).room_id);
        sRoom.clients.add(sClient);
        Message m = new Message(Message.Type.NOTIFICATION);
        m.notf_type= Message.Notf_Type.SUCCES;
        m.nickname=message.nickname;
        m.content=message.content;
        Send(sClient, m);
    }
    
    
    public static void Send(SClient cl, Object msg) {

        try {
            cl.socketOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    static ArrayList<CRoom> castRooms (ArrayList<SRoom> sRooms) {
        ArrayList<CRoom> cRooms = new ArrayList<>();
        
        for (SRoom sroom : sRooms) {
            cRooms.add(castRoom(sroom));
        }
        return cRooms;
    }
    
    static CRoom castRoom(SRoom sRoom){
        CRoom cRoom = new CRoom();
        //cRoom.room_creater=sRoom.room_creater;
        cRoom.room_id=sRoom.room_id;
        cRoom.room_name=sRoom.room_name;
        return cRoom;
    }
    
    private static void mapRoom(SRoom sRoom){
    int room_id=sRoom.room_id;
    Server.intToRoomMap.put(room_id, sRoom);
    }
    
    private static void mapClient(SClient sClient){
    int client_id=sClient.id;
    Server.intToClientMap.put(client_id, sClient);
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