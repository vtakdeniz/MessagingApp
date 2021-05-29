package messageapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SClient {

    int id;
    Socket socket;
    ObjectOutputStream socketOutput;
    ObjectInputStream socketInput;
    Listen listenThread;
    SClient adversary;
    public boolean paired = false;

    public SClient(Socket incoming_socket, int id) {
        this.socket = incoming_socket;
        this.id = id;
        try {
            this.socketOutput = new ObjectOutputStream(this.socket.getOutputStream());
            this.socketInput = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.listenThread = new Listen(this);

    }

    class Listen extends Thread {

        SClient currentClient;

        Listen(SClient currentClient) {
            this.currentClient = currentClient;
        }

        public void run() {

            while (currentClient.socket.isConnected()) {

                try {
                    Object received = currentClient.socketInput.readObject();
                    //Server.Send(currentClient.adversary,received);

                } catch (IOException e) {
                    Server.Clients.remove(currentClient);
                    e.printStackTrace();
                    break;
                } catch (ClassNotFoundException e) {
                    Server.Clients.remove(currentClient);
                    e.printStackTrace();
                    break;
                }

            }

        }

    }



}