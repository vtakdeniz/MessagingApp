package messageapp;


public class Message implements java.io.Serializable {

    public static enum Type {TEXT, CONN_REQ, FILE, DISCONNECT,LIST_REQUEST,LIST}
    public static enum Cast_Type {ROOM_MESSAGE, PVP_MESSAGE,ROOM_LIST,USER_LIST}
    
    public Type type;
    public Cast_Type cast_type;
    public Object content;
    public int receiver;

    public Message(Type t) {
        this.type = t;
    }

}