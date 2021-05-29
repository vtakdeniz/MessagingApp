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
public class SRoom implements Serializable{
    public int room_id;
    public String room_name;
    
    @Override
    public String toString(){
        return this.room_name;
    }
}
