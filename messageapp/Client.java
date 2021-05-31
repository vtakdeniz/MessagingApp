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
        
        if (message.chat_type==Message.Chat_Type.ROOM_MESSAGE) {
            ArrayList <SRoom> rooms = (ArrayList <SRoom>)message.content;
            for (SRoom room : rooms) {
                Client.screen.room_table_model.addRow(new Object[]{room});
                setChatBox(room);
            }
        }
        else{
            ArrayList <CClient> cclients = (ArrayList <CClient>)message.content;
            for (CClient cClient : cclients) {
                Client.screen.client_table_model.addRow(new Object[]{cClient});
                setChatBox(cClient);
            }
        }
    }
        
     void setChatBox(SRoom sRoom){
        Chatbox chatbox = new Chatbox(sRoom);
     }
     void setChatBox(CClient cClient){
        Chatbox chatbox = new Chatbox(cClient);
     }

}

public class Client {

    public static ObjectInputStream socketInput;
    public static ObjectOutputStream socketOutput;
    public static Socket socket;
    public static Listen listenMe;
    public static Screen screen;
    
    public static void Start(String ip, int port) {
        try {
            Client s= new Client();
            screen = new Screen();
            screen.setVisible(true);
            Client.socket = new Socket(ip, port);
            Client.print("Connected");
            Client.socketInput = new ObjectInputStream(Client.socket.getInputStream());
            Client.socketOutput = new ObjectOutputStream(Client.socket.getOutputStream());
            Client.listenMe = new Listen();
            Client.listenMe.start();

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