/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author medit
 */
// This class is used for representing rooms in client side
public class CRoom implements Serializable{
    public int room_id;
    public String room_name;
    public String room_creator;
    @Override
    public String toString(){
        return this.room_name;
    }
}
