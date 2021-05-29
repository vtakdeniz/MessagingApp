package messageapp;

import messageapp.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


class Listen extends Thread {

    Screen local_screen;
    public Listen(Screen localscreen){
        this.local_screen=localscreen;
    }
    
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
        
        if (message.cast_type==Message.Cast_Type.ROOM_LIST) {
            ArrayList <SRoom> rooms = (ArrayList <SRoom>)message.content;
            for (SRoom room : rooms) {
                local_screen.room_table_model.addRow(new Object[]{room});
            }
        }
    }
}

public class Client {

    public static ObjectInputStream socketInput;
    public static ObjectOutputStream socketOutput;
    public static Socket socket;
    public static Listen listenMe;
    public Screen screen;
    
    public void Start(String ip, int port) {
        try {
            screen = new Screen();
            screen.setVisible(true);
            Client.socket = new Socket(ip, port);
            Client.print("Connected");
            Client.socketInput = new ObjectInputStream(Client.socket.getInputStream());
            Client.socketOutput = new ObjectOutputStream(Client.socket.getOutputStream());
            Client.listenMe = new Listen(screen);
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