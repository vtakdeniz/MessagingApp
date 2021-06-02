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
                Server.intToClientMap.put(Sclient.id, Sclient);
                Message rooms_message = new Message(Message.Type.LIST);
                rooms_message.cast_type = Message.Cast_Type.ROOM_LIST;
                rooms_message.content = Server.castRooms(Server.Rooms);

                Message users_message = new Message(Message.Type.LIST);
                users_message.cast_type = Message.Cast_Type.USER_LIST;
                users_message.content = Server.castClients(Server.Clients);
                

                Server.Send(Sclient, users_message);
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
    public static int room_id = 0;

    public static int port = 0;
    public static Map<Integer, SRoom> intToRoomMap = new HashMap<Integer, SRoom>();
    public static Map<Integer, SClient> intToClientMap = new HashMap<Integer, SClient>();
    public static ServerThread main_thread;
    public static ArrayList<SClient> Clients = new ArrayList<>();
    public static ArrayList<SRoom> Rooms = new ArrayList<>();

    public static void Start(int openingport) {
        try {
            initBaseRooms();
            Server.port = openingport;
            Server.serverSocket = new ServerSocket(Server.port);

            Server.main_thread = new ServerThread();
            Server.main_thread.start();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void broadcastToRoom(CRoom cRoom, SClient sClient, Object message) {
        SRoom sRoom = intToRoomMap.get(cRoom.room_id);
        ArrayList<SClient> room_clients = sRoom.clients;
        if (!room_clients.contains(sClient)) {
            //Message m= new Message(Message.Type.CONN_REQ);
            return;
        }

        for (SClient room_client : room_clients) {
            if (sClient == room_client) {
                continue;
            }
            Send(room_client, message);
        }
    }

    public static void initBaseRooms() {
        SRoom s1 = new SRoom();
        s1.room_id = room_id;
        s1.room_name = "Chat Room 1";
        room_id++;
        SRoom s2 = new SRoom();
        s2.room_id = room_id;
        s2.room_name = "Chat Room 2";
        room_id++;
        mapRoom(s1);
        mapRoom(s2);

        SRoom s3 = new SRoom();
        s3.room_id = room_id;
        s3.room_name = "Chat Room 3";
        room_id++;
        SRoom s4 = new SRoom();
        s4.room_id = room_id;
        s4.room_name = "Chat Room 4";
        room_id++;
        mapRoom(s3);
        mapRoom(s4);

        Server.Rooms.add(s1);
        Server.Rooms.add(s2);
        Server.Rooms.add(s3);
        Server.Rooms.add(s4);
    }

    public static void broadcastToNotfRoom(SRoom sRoom, SClient sClient, Object message) {

        ArrayList<SClient> room_clients = sRoom.clients;

        for (SClient room_client : room_clients) {
            Send(room_client, message);
        }
    }
    
    public static void injectUser(SClient sClient){
        CClient cClient = castClient(sClient);
        Message client_injection = new Message(Message.Type.INJECTION);
        client_injection.cast_type= Message.Cast_Type.CLIENT;
        client_injection.content=cClient;
        for (SClient Client : Clients) {
            if (Client==sClient) {
                continue;
            }
            Send(Client, client_injection);
        }

    }
    
    public static void SendToUser(SClient sender_client,Message message){
     SClient sClient = intToClientMap.get(((CClient)message.receiver).client_id);
     message.sender=castClient(sender_client);
     Server.Send(sClient, message);
    }

    public static void handleRequest(SClient sClient, Message message) {
        SRoom sRoom = intToRoomMap.get(((CRoom) message.content).room_id);
        sRoom.clients.add(sClient);
        Message m = new Message(Message.Type.NOTIFICATION);
        m.notf_type = Message.Notf_Type.SUCCES;
        m.nickname = message.nickname;
        m.content = message.content;
        Send(sClient, m);
        Message join_message = new Message(Message.Type.TEXT);
        join_message.nickname = "Server";
        join_message.chat_type = Message.Chat_Type.ROOM_MESSAGE;
        join_message.content = m.nickname + " has joined the room";
        join_message.receiver = castRoom(sRoom);
        broadcastToNotfRoom(sRoom, sClient, join_message);
    }

    public static void CreateRoom(SClient sClient, Message message) {
        SRoom sRoom = initializeRoom(((String) message.content), sClient);
        Rooms.add(sRoom);
        intToRoomMap.put(sRoom.room_id, sRoom);
        CRoom cRoom = castRoom(sRoom, message.nickname);

        Message injection_message = new Message(Message.Type.INJECTION);
        injection_message.cast_type = Message.Cast_Type.ROOM;
        injection_message.content = cRoom;
        BroadCast(injection_message);

        Message room_create_message = new Message(Message.Type.ROOM_CREATE_NOTF);
        room_create_message.notf_type = Message.Notf_Type.SUCCES;
        room_create_message.content = cRoom;
        Send(sClient, room_create_message);
    }

    public static SRoom initializeRoom(String room_name, SClient sClient) {
        SRoom sRoom = new SRoom();
        sRoom.room_id = room_id;
        room_id++;
        sRoom.room_name = room_name;
        sRoom.room_creater = sClient;
        return sRoom;
    }

    public static void Send(SClient cl, Object msg) {

        try {
            cl.socketOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static ArrayList<CRoom> castRooms(ArrayList<SRoom> sRooms) {
        ArrayList<CRoom> cRooms = new ArrayList<>();

        for (SRoom sroom : sRooms) {
            cRooms.add(castRoom(sroom));
        }
        return cRooms;
    }

    static CRoom castRoom(SRoom sRoom) {
        CRoom cRoom = new CRoom();
        //cRoom.room_creator=sRoom.room_creator;
        cRoom.room_id = sRoom.room_id;
        cRoom.room_name = sRoom.room_name;
        return cRoom;
    }

    static ArrayList<CClient> castClients(ArrayList<SClient> sClients) {
        ArrayList<CClient> cClients = new ArrayList<>();

        for (SClient sclient : sClients) {
            cClients.add(castClient(sclient));
        }
        return cClients;
    }

    static CClient castClient(SClient sClient) {
        CClient cClient = new CClient(sClient.id,sClient.nickname);
        return cClient;
    }

    static CRoom castRoom(SRoom sRoom, String creator) {
        CRoom cRoom = new CRoom();
        cRoom.room_creator = creator;
        cRoom.room_id = sRoom.room_id;
        cRoom.room_name = sRoom.room_name;
        return cRoom;
    }

    private static void mapRoom(SRoom sRoom) {
        int room_id = sRoom.room_id;
        Server.intToRoomMap.put(room_id, sRoom);
    }

    private static void mapClient(SClient sClient) {
        int client_id = sClient.id;
        Server.intToClientMap.put(client_id, sClient);
    }

    public static void BroadCast(Message message) {

        for (SClient Client : Clients) {
            Send(Client, message);
        }

    }

    /*public static void BroadCast(SClient sClient, Object msg) {

        try {
            sClient.socketOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/
    public static void print(String msg) {

        System.out.println(msg);

    }

}
