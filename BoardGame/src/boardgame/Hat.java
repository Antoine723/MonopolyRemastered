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
public class Hat extends Player implements Citizen {
    
    public Hat(String name,int playerNumber){
        this.setName(name);
        this.setPlayerNumber(playerNumber);
    }
    
    
    public void reduc(Avenue avenue){
        avenue.setBoughtPrice((int)(avenue.getBoughtPrice()*0.8)); //Attention, ne pas oublier de remettre le prix de base pour les autres joueurs
    }
    
    public void doubleRent(){
        
    }
    
    
    
}
