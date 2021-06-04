/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageapp;

import java.io.Serializable;

/**
 *
 * @author medit
 */
// This class is used for representing client in client side
public class CClient implements Serializable{
    public int client_id;
    public String client_nickname;
   
    public CClient(int clientid,String nickname){
        this.client_id=clientid;
        this.client_nickname=nickname;
    }
    
     @Override
    public String toString(){
        return this.client_nickname;
    }
}
