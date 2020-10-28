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
public class Mayor extends Player {
    
    public Mayor(String name,int playerNumber){
        this.setName(name);
        this.setPlayerNumber(playerNumber);
    }
    
    public void makeProfits(){
        this.setCapital(this.getCapital()+100*(this.getNumberOfAvenues()));
        
    }
    
    public void buildHotel(Avenue avenue){
        avenue.setHotel(1);
        
    }
    
}
