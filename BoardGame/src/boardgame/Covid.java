/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;

/**
 *
 * @author Antoine
 */
public class Covid extends Event{
    
    public Covid(String name, String effect){
        this.setName(name);
        this.setEffect(effect);
    }
    
    public void closeHotel(){
        
    }
    
}
