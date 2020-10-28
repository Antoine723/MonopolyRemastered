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
public class Inflation extends Attack {
    
    public Inflation(String name, String effect, String associatedPlayer){
        this.setName(name);
        this.setEffect(effect);
        this.setAssociatedPlayer(associatedPlayer);
        
    }
    
    public void payDoubleRent(){
        
    }
}
