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
public class NoMoney extends Attack{
    
    public NoMoney(String name, String effect, String associatedPlayer){
        this.setName(name);
        this.setEffect(effect);
        this.setAssociatedPlayer(associatedPlayer);
        
    }
    
    public void declineMoney(Player attackedPlayer)
    {
        if(attackedPlayer.getNumberCase() == 0)
        {
            attackedPlayer.setCapital(attackedPlayer.getCapital());             // implémneter la case départ pour ne pas avoir le bonus ici
        }
    }
    
}
