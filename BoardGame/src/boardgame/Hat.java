/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Antoine
 */
public final class Hat extends Player implements Citizen {
    
    public Hat(String name,int playerNumber){
        this.setName(name);
        this.setPlayerNumber(playerNumber);
    }
    
    
    public void scam(ArrayList <Player> players){
        int totalAmount=0;
        int amountPerPlayer=0;
        Random rand=new Random();
        for(int i=0;i<players.size();i++){
            if(players.get(i)!=this){
                amountPerPlayer=rand.nextInt(2000);
                totalAmount+=amountPerPlayer;
                players.get(i).setCapital(players.get(i).getCapital()-amountPerPlayer);
                players.get(i).setScamed(true);
                players.get(i).setAmountScamed(amountPerPlayer);
            }
        }
        this.setCapital(this.getCapital()+totalAmount);
        System.out.println("Vous avez réussi à amasser "+totalAmount+" Francs en arnaquant les autres joueurs");
    }
    
    @Override
    public void doubleRent(Avenue avenue)
    {
        if(avenue.getColor().equals(ColorAvenue.ROUGE))                                   // SI COULEUR CORRESPOND AU BONUS
        {  
            avenue.setRent(avenue.getRent() * 2);                               // ON DOUBLE
        }
        else
        {
            avenue.setRent(avenue.getRent());                                   // ON NE CHANGE RIEN
        }
    }
    
    
    
}
