package messageapp;

public class ServerStart {

    public static void main(String[] args) {
        Server.Start(2005);
        Client.Start("127.0.0.1", 2005);
    }

}