/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageapp;

import javax.swing.DefaultListModel;

/**
 *
 * @author medit
 */
//This class holds list model and associated room or client within for JList in screen class
public class Chatbox {

    int chatbox_id;
    String chatbox_name;
    String chat_box_nickname;
    public Message.Chat_Type chat_type;
    public int sender_id;
    public CRoom croom;
    public CClient cclient;
    public DefaultListModel list_model;

    public Chatbox(CClient cClient) {
        this.chat_type = Message.Chat_Type.PVP_MESSAGE;
        this.chatbox_id = Screen.chat_box_id;
        Screen.chat_box_id++;
        this.sender_id = cClient.client_id;
        this.cclient = cClient;
        DefaultListModel dlm = new DefaultListModel();
        this.list_model = dlm;
        this.chatbox_name = cClient.client_nickname;
        
    }

    public Chatbox(CRoom cRoom) {
        this.chat_type = Message.Chat_Type.ROOM_MESSAGE;
        this.chatbox_id = Screen.chat_box_id;
        Screen.chat_box_id++;
        this.sender_id = cRoom.room_id;
        this.croom = cRoom;
        DefaultListModel dlm = new DefaultListModel();
        this.list_model = dlm;
        this.chatbox_name = cRoom.room_name;
        
    }
    
    public Chatbox(SRoom sRoom) {
        CRoom cRoom = castRoom(sRoom);
        this.chat_type = Message.Chat_Type.ROOM_MESSAGE;
        this.chatbox_id = Screen.chat_box_id;
        Screen.chat_box_id++;
        this.sender_id = cRoom.room_id;
        this.croom = cRoom;
        DefaultListModel dlm = new DefaultListModel();
        this.list_model = dlm;
        this.chatbox_name = cRoom.room_name; 
    }

    CRoom castRoom(SRoom sRoom){
        CRoom cRoom = new CRoom();
        //cRoom.room_creator=sRoom.room_creator;
        cRoom.room_id=sRoom.room_id;
        cRoom.room_name=sRoom.room_name;
        return cRoom;
    }
    
    public CRoom getReceiverRoom() {
        return this.croom;
    }

    public CClient getReceiverCClient() {
        return this.cclient;
    }

    public Object getReceiver() {
        if (chat_type == Message.Chat_Type.ROOM_MESSAGE) {
            return this.croom;
        }

        return this.cclient;

    }

    public String getNickName() {
        if (chat_type == Message.Chat_Type.ROOM_MESSAGE) {
            return this.chat_box_nickname;
        }

        return Client.nick_name;
    }

    @Override
    public String toString() {
        return this.chatbox_name;
    }
}
