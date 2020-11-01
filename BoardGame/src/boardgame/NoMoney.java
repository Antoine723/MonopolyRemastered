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
    
    public NoMoney(){
        this.setName("NoMoney");
        this.setEffect("Le joueur visé ne touchera pas l'argent de la case départ pendant 3 tours");
        
    }
    
    public void declineMoney(Player attackedPlayer) //A modifier
    {
        if(attackedPlayer.getNumberCase() == 0) //Pour un certain nombre de tours (pour l'instant 3 tours
        {
            attackedPlayer.setCapital(attackedPlayer.getCapital());             // implémneter la case départ pour ne pas avoir le bonus ici
        }
        this.setIsUsed(true);
    }
    
}
