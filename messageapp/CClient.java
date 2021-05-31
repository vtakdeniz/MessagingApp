/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageapp;

/**
 *
 * @author medit
 */
public class CClient {
    public int client_id;
    public String client_nickname;
     @Override
    public String toString(){
        return this.client_nickname;
    }
}
