package messageapp;

import messageapp.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;


class Listen extends Thread {
    public void run() {
        while (Client.socket.isConnected()) {
            try {
                Message received = (Message)(Client.socketInput.readObject());
                switch (received.type) {
                    case LIST:
                        addListToTable(received);
                        break;
                    case TEXT:
                        String text = received.nickname+" : "+(String)received.content;
                        Chatbox chatbox = Screen.objToChatMap.get(received.receiver);
                        chatbox.list_model.addElement(text);
                        break;
                }

            } catch (IOException ex) {

                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                Client.terminate();
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                Client.terminate();
                break;
            }
        }

    }
    
     void addListToTable(Message message){
        
        if (message.cast_type==Message.Cast_Type.ROOM_LIST) {
            ArrayList <CRoom> rooms = (ArrayList <CRoom>)message.content;
            for (CRoom room : rooms) {
              //  Client.screen.room_table_model.addRow(new Object[]{room});
                setChatBox(room);
            }
        }
        else{
            ArrayList <CClient> cclients = (ArrayList <CClient>)message.content;
            for (CClient cClient : cclients) {
               // Client.screen.client_table_model.addRow(new Object[]{cClient});
                setChatBox(cClient);
            }
        }
    }
        
     void setChatBox(CRoom cRoom){
        Chatbox chatbox = new Chatbox(cRoom);
        Client.screen.room_table_model.addRow(new Object[]{chatbox});
     }
     void setChatBox(CClient cClient){
        Chatbox chatbox = new Chatbox(cClient);
        Client.screen.client_table_model.addRow(new Object[]{chatbox});
     }

}

public class Client {

    public static ObjectInputStream socketInput;
    public static ObjectOutputStream socketOutput;
    public static Socket socket;
    public static Listen listenMe;
    public static Screen screen;
    public static String nick_name  ;
    public static CClient cclient;
    
    public static void Start(String ip, int port,String username) {
        try {
            Client.nick_name = username;
            screen = new Screen();
            screen.setVisible(true);
            Client.socket = new Socket(ip, port);
            Client.print("Connected");
            Client.socketInput = new ObjectInputStream(Client.socket.getInputStream());
            Client.socketOutput = new ObjectOutputStream(Client.socket.getOutputStream());
            Client.listenMe = new Listen();
            Client.listenMe.start();
            Client.cclient= new CClient();
            cclient.client_id=-1;
            cclient.client_nickname=username;
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void terminate() {
        try {
            if (Client.socket != null) {
                Client.listenMe.stop();
                Client.socket.close();
                Client.socketOutput.flush();
                Client.socketOutput.close();

                Client.socketInput.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void Send(Object msg) {
        try {
            Client.socketOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void print(String msg) {

        System.out.println(msg);

    }



}