/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Antoine
 */
public final class Inflation extends Attack {
    
    public Inflation(){
        this.setName("Inflation");
        this.setEffect("Vous pouvez imposer à un joueur de payer 2 fois plus cher le prochain terrain qu'il veut acheter");
        
    }
    
    public void payDoubleRent(Player attackedPlayer)
    {
           attackedPlayer.setInflated(true);
           this.setIsUsed(true);
    }
    
    @Override
    public boolean effect(ArrayList <Player> players, Player attacker,ArrayList <Case> board){
        Scanner attacked_player_scanner=new Scanner(System.in);
        System.out.println("Quel joueur voulez-vous attaquer ?");
        for(int i=0;i<players.size();i++){
            if(!players.get(i).equals(attacker)) System.out.print(players.get(i).getName());
        }
        System.out.println("");
        String attackedPlayerName=attacked_player_scanner.nextLine();
        Player attackedPlayer=null;
        for(int i=0;i<players.size();i++){
            if(players.get(i).getName().equals(attackedPlayerName)) attackedPlayer=players.get(i);
        }
        payDoubleRent(attackedPlayer);
        return true;
    }
}
