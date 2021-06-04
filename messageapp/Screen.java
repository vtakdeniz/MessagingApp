/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageapp;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author medit
 */
public class Screen extends javax.swing.JFrame {

    /**
     * Creates new form Screen
     */
    public static DefaultTableModel client_table_model = new DefaultTableModel();
    public static DefaultTableModel room_table_model = new DefaultTableModel();
    public static int chat_box_id = 0;
    public static Map<Integer, Chatbox> IntToRoomChatMap = new HashMap<Integer, Chatbox>();
    public static Map<Integer, Chatbox> IntToClientChatMap = new HashMap<Integer, Chatbox>();
    public static ArrayList<Chatbox> chatboxes = new ArrayList();
    public static Chatbox chosen_chatbox;

    /**
     * deprecated public static ArrayList<DefaultListModel> dlms = new
     * ArrayList(); public static Map<Integer, DefaultListModel> intToRoomDlmMap
     * = new HashMap<Integer, DefaultListModel>(); public static
     * Map<Integer, DefaultListModel> intToClientDlmMap = new
     * HashMap<Integer, DefaultListModel>();
     */
    public Screen() {
        initComponents();

        client_table_model.setColumnIdentifiers(new Object[]{"User Name"});
        user_table.setModel(client_table_model);

        room_table_model.setColumnIdentifiers(new Object[]{"Room Name"});
        room_table.setModel(room_table_model);

        message_screen_panel.setVisible(false);
        create_screen_panel.setVisible(false);
        message_list.setModel(new DefaultListModel());
        create_screen_panel.setBackground(Color.decode("#a39a70"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator4 = new javax.swing.JSeparator();
        message_screen_panel = new javax.swing.JPanel();
        back_button = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        message_list = new javax.swing.JList<>();
        stat_current_room_label = new javax.swing.JLabel();
        current_room_label = new javax.swing.JLabel();
        text_field = new javax.swing.JTextField();
        send_button = new javax.swing.JButton();
        send_file_button = new javax.swing.JButton();
        start_panel = new javax.swing.JPanel();
        choose_button = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        stat_room_label = new javax.swing.JLabel();
        room_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        user_table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        room_table = new javax.swing.JTable();
        create_button = new javax.swing.JButton();
        create_screen_panel = new javax.swing.JPanel();
        room_create_text = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        create_screen_button = new javax.swing.JButton();
        cancel_button = new javax.swing.JToggleButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        nickname_field = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        back_button.setText("< Back");
        back_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_buttonActionPerformed(evt);
            }
        });

        message_list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(message_list);

        stat_current_room_label.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        stat_current_room_label.setText("Current Room :");

        current_room_label.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        send_button.setText("Send");
        send_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send_buttonActionPerformed(evt);
            }
        });

        send_file_button.setText("Send a file");
        send_file_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send_file_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout message_screen_panelLayout = new javax.swing.GroupLayout(message_screen_panel);
        message_screen_panel.setLayout(message_screen_panelLayout);
        message_screen_panelLayout.setHorizontalGroup(
            message_screen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(message_screen_panelLayout.createSequentialGroup()
                .addGroup(message_screen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, message_screen_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(text_field))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, message_screen_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, message_screen_panelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(stat_current_room_label, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(current_room_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, message_screen_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(send_file_button, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(send_button, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(message_screen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(message_screen_panelLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(611, Short.MAX_VALUE)))
        );
        message_screen_panelLayout.setVerticalGroup(
            message_screen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(message_screen_panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(message_screen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stat_current_room_label, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(current_room_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(text_field, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(message_screen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(send_button, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(send_file_button))
                .addContainerGap())
            .addGroup(message_screen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, message_screen_panelLayout.createSequentialGroup()
                    .addContainerGap(556, Short.MAX_VALUE)
                    .addComponent(back_button)
                    .addContainerGap()))
        );

        choose_button.setText("Choose ");
        choose_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choose_buttonActionPerformed(evt);
            }
        });

        stat_room_label.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        stat_room_label.setText("   Rooms : ");

        room_label.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        room_label.setText("  Users : ");

        user_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        user_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(user_table);

        room_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        room_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                room_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(room_table);

        create_button.setText("Create Room");
        create_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_buttonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Room Name : ");

        create_screen_button.setText("Create");
        create_screen_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_screen_buttonActionPerformed(evt);
            }
        });

        cancel_button.setText("Cancel");
        cancel_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_buttonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Nickname For Room :");

        javax.swing.GroupLayout create_screen_panelLayout = new javax.swing.GroupLayout(create_screen_panel);
        create_screen_panel.setLayout(create_screen_panelLayout);
        create_screen_panelLayout.setHorizontalGroup(
            create_screen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(create_screen_panelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(cancel_button, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(create_screen_button, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(create_screen_panelLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(create_screen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(create_screen_panelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(create_screen_panelLayout.createSequentialGroup()
                        .addGroup(create_screen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nickname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(room_create_text, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 74, Short.MAX_VALUE))))
            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        create_screen_panelLayout.setVerticalGroup(
            create_screen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(create_screen_panelLayout.createSequentialGroup()
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(room_create_text, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nickname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(create_screen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel_button)
                    .addComponent(create_screen_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout start_panelLayout = new javax.swing.GroupLayout(start_panel);
        start_panel.setLayout(start_panelLayout);
        start_panelLayout.setHorizontalGroup(
            start_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(start_panelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(create_button, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(choose_button, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(start_panelLayout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(create_screen_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
            .addGroup(start_panelLayout.createSequentialGroup()
                .addComponent(stat_room_label, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(start_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE))
            .addGroup(start_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE))
            .addGroup(start_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(start_panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(room_label, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(699, Short.MAX_VALUE)))
            .addGroup(start_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(start_panelLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
                    .addGap(6, 6, 6)))
        );
        start_panelLayout.setVerticalGroup(
            start_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, start_panelLayout.createSequentialGroup()
                .addComponent(stat_room_label, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(create_screen_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                .addGroup(start_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(choose_button)
                    .addComponent(create_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(start_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(start_panelLayout.createSequentialGroup()
                    .addGap(330, 330, 330)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(56, Short.MAX_VALUE)))
            .addGroup(start_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(start_panelLayout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(317, Short.MAX_VALUE)))
            .addGroup(start_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(start_panelLayout.createSequentialGroup()
                    .addGap(277, 277, 277)
                    .addComponent(room_label, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(261, Short.MAX_VALUE)))
            .addGroup(start_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(start_panelLayout.createSequentialGroup()
                    .addGap(278, 278, 278)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(300, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(start_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(message_screen_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(start_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(message_screen_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void choose_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choose_buttonActionPerformed

        int row_room = room_table.getSelectedRow();
        int row_user = user_table.getSelectedRow();

        if (row_room != -1) {
            stat_current_room_label.setText("Current Room : ");
            Screen.chosen_chatbox = (Chatbox) room_table.getValueAt(room_table.getSelectedRow(), 0);
            DefaultListModel chosen_model = chosen_chatbox.list_model;
            message_list.setModel(chosen_model);
            if (!Client.joined_rooms.contains(chosen_chatbox.croom.room_id)) {
                text_field.setText("It looks like you are not a member of this group. Please enter a username here to continue :");
                current_room_label.setText(chosen_chatbox.chatbox_name);
            } else {
                text_field.setText("");
                current_room_label.setText(chosen_chatbox.chatbox_name);
            }

            message_screen_panel.setVisible(true);
            start_panel.setVisible(false);
        } else if (row_user != -1) {
            stat_current_room_label.setText("Current User : ");
            Screen.chosen_chatbox = (Chatbox) user_table.getValueAt(user_table.getSelectedRow(), 0);
            message_list.setModel(chosen_chatbox.list_model);
            message_screen_panel.setVisible(true);
            start_panel.setVisible(false);
            current_room_label.setText(chosen_chatbox.cclient.client_nickname);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Please select a chat from either of the tables", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_choose_buttonActionPerformed

    private void back_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttonActionPerformed
        message_screen_panel.setVisible(false);
        start_panel.setVisible(true);
    }//GEN-LAST:event_back_buttonActionPerformed

    private void send_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send_buttonActionPerformed
        if (chosen_chatbox.chat_type == Message.Chat_Type.ROOM_MESSAGE && Client.joined_rooms.contains(chosen_chatbox.croom.room_id)) {
            Message message = new Message(Message.Type.TEXT);
            message.chat_type = Screen.chosen_chatbox.chat_type;
            message.content = text_field.getText();
            message.receiver = Screen.chosen_chatbox.getReceiver();
            message.nickname = Screen.chosen_chatbox.getNickName();
            Client.Send(message);
            Screen.chosen_chatbox.list_model.addElement("You : " + text_field.getText());
            text_field.setText("");
        } else if (chosen_chatbox.chat_type == Message.Chat_Type.PVP_MESSAGE) {
            Message message = new Message(Message.Type.TEXT);
            message.chat_type = Screen.chosen_chatbox.chat_type;
            message.content = text_field.getText();
            message.receiver = Screen.chosen_chatbox.getReceiver();
            message.nickname = Screen.chosen_chatbox.getNickName();
            Client.Send(message);
            Screen.chosen_chatbox.list_model.addElement("You : " + text_field.getText());
            text_field.setText("");
        } else {
            Message m = new Message(Message.Type.CONN_REQ);
            m.nickname = text_field.getText();
            m.chat_type = Message.Chat_Type.ROOM_MESSAGE;
            m.content = chosen_chatbox.croom;
            Client.Send(m);
            text_field.setText("");
        }

    }//GEN-LAST:event_send_buttonActionPerformed


    private void create_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_buttonActionPerformed

        create_screen_panel.setVisible(true);
        start_panel.setEnabled(false);

    }//GEN-LAST:event_create_buttonActionPerformed

    private void create_screen_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_screen_buttonActionPerformed
        Message m = new Message(Message.Type.CREATE);
        m.content = room_create_text.getText();
        room_create_text.setText("");
        m.nickname = nickname_field.getText();
        nickname_field.setText("");
        Client.Send(m);
        create_screen_panel.setVisible(false);
        start_panel.setEnabled(true);
    }//GEN-LAST:event_create_screen_buttonActionPerformed

    private void cancel_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_buttonActionPerformed
        create_screen_panel.setVisible(false);
        start_panel.setEnabled(true);
    }//GEN-LAST:event_cancel_buttonActionPerformed

    private void room_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_room_tableMouseClicked
        user_table.getSelectionModel().clearSelection();
    }//GEN-LAST:event_room_tableMouseClicked

    private void user_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tableMouseClicked
        room_table.getSelectionModel().clearSelection();
    }//GEN-LAST:event_user_tableMouseClicked

    private void send_file_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send_file_buttonActionPerformed

        if (chosen_chatbox.chat_type == Message.Chat_Type.ROOM_MESSAGE && Client.joined_rooms.contains(chosen_chatbox.croom.room_id)) {
            Message message = new Message(Message.Type.FILE);
            message.chat_type = Screen.chosen_chatbox.chat_type;

            try {
            String filename = "/home/medit/NetBeansProjects/MessageApp/src/messageapp/spongebob_PNG1.png";
            message.content=filename;
            File file_to_send = new File(filename);
            int filesize = (int)file_to_send.length();
            message.file_byte = new byte[filesize];
            message.filesize=filesize;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file_to_send));
            bis.read(message.file_byte, 0, message.file_byte.length);
            message.receiver = Screen.chosen_chatbox.getReceiver();
            message.nickname = Screen.chosen_chatbox.getNickName();
            Client.Send(message);
            Screen.chosen_chatbox.list_model.addElement("You have sent a file ");
            bis.close();
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (chosen_chatbox.chat_type == Message.Chat_Type.PVP_MESSAGE) {
            BufferedInputStream bis = null;
            Message message = new Message(Message.Type.FILE);
            
            try {
                String filename = "/home/medit/NetBeansProjects/MessageApp/src/messageapp/spongebob_PNG1.png";
                message.content=filename;
                File file_to_send = new File(filename);
                int filesize = (int)file_to_send.length();
                message.file_byte = new byte[filesize];
                message.filesize=filesize;
                bis = new BufferedInputStream(new FileInputStream(file_to_send));
                bis.read(message.file_byte, 0, message.file_byte.length);
                message.receiver = Screen.chosen_chatbox.getReceiver();
                message.nickname = Screen.chosen_chatbox.getNickName();
                message.chat_type = Screen.chosen_chatbox.chat_type;
                Client.Send(message);
                Screen.chosen_chatbox.list_model.addElement("You have sent a file ");
                bis.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    bis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_send_file_buttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Screen s = new Screen();
                s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                s.setVisible(true);
            }
        });
    }

    public static Chatbox getChatbox(Message message) {
        if (message.chat_type == Message.Chat_Type.ROOM_MESSAGE) {
            return IntToRoomChatMap.get(((CRoom) message.receiver).room_id);
        } else if (message.chat_type == Message.Chat_Type.PVP_MESSAGE) {
            return IntToClientChatMap.get(((CClient) message.sender).client_id);
        }
        return null;
    }

    public static Chatbox getRoomChatbox(CRoom cRoom) {

        return IntToRoomChatMap.get(cRoom.room_id);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_button;
    private javax.swing.JToggleButton cancel_button;
    private javax.swing.JButton choose_button;
    private javax.swing.JButton create_button;
    private javax.swing.JButton create_screen_button;
    private javax.swing.JPanel create_screen_panel;
    private javax.swing.JLabel current_room_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JList<String> message_list;
    private javax.swing.JPanel message_screen_panel;
    private javax.swing.JTextField nickname_field;
    private javax.swing.JTextField room_create_text;
    private javax.swing.JLabel room_label;
    private javax.swing.JTable room_table;
    private javax.swing.JButton send_button;
    private javax.swing.JButton send_file_button;
    private javax.swing.JPanel start_panel;
    private javax.swing.JLabel stat_current_room_label;
    private javax.swing.JLabel stat_room_label;
    private javax.swing.JTextField text_field;
    private javax.swing.JTable user_table;
    // End of variables declaration//GEN-END:variables
}
