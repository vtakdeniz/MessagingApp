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
public class Chatbox {
    int chatbox_id;
    public Message.Chat_Type chat_type;
    public int sender_id;
    public SRoom room;
    public CClient cclient;
    public DefaultListModel list_model;

    public Chatbox(CClient cClient) {
        this.chat_type=Message.Chat_Type.PVP_MESSAGE;
        this.chatbox_id=Screen.chat_box_id;
        Screen.chat_box_id++;
        this.sender_id=cClient.client_id;
        this.cclient=cClient;
        
    }
    public Chatbox(SRoom sRoom) {
        this.chat_type=Message.Chat_Type.ROOM_MESSAGE;
        this.chatbox_id=Screen.chat_box_id;
        Screen.chat_box_id++;
        this.sender_id=sRoom.room_id;
        this.room=sRoom;
    }
}
