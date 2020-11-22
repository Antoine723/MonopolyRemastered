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

/**
 * La classe Inflation permet d'appliquer l'effet de la carte attaque Inflation
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * On retrouve les fonctions permettant de déclencher l'effet de la carte et les fonctions permettant d'utiliser cette carte
 * @author thibb
 */

public final class Inflation extends Attack {
    
    
    /**
     * <b> Constructeur de Inflation </b>
     * <br>
     * On attribut un nom à la carte attaque et on indique son effet à l'utilisateur
     */
    
    public Inflation(){
        this.setName("Inflation");
        this.setEffect("Vous pouvez imposer à un joueur de payer 2 fois plus cher le prochain terrain qu'il veut acheter");
        
    }
    
    /**
     * Cette méthode permet de doubler le loyer payé par un joueur adverse lorsque celui-ci arrive sur la propriété du joueur possédant la carte
     * @param attackedPlayer
     *      Le paramètre correspond au joueur attaqué
     */
    public void payDoubleRent(Player attackedPlayer)
    {
           attackedPlayer.setInflated(true);
           this.setIsUsed(true);
    }
    
    
    /**
     * Cette méthode permet d'implémenter l'effet de la carte Inflation
     * @param players
     *      Le paramètre correspond à la liste des joueurs
     * @param attacker
     *      Le paramètre correspond au joueur attaqué
     * @param board
     *      Le paramètre correspond au plateau de jeu
     * @return   La méthode retourne vrai si l'opération s'est bien passée sinon elle retourne faux dans le cas où l'attaquant s'est trompé dans l'utilisation de la carte
     */
    
    @Override
    public boolean effect(ArrayList <Player> players, Player attacker,ArrayList <Case> board){
        Scanner attackedPlayerScanner=new Scanner(System.in);
        System.out.println("Quel joueur voulez-vous attaquer ?");
        for(int i=0;i<players.size();i++){
            if(!players.get(i).equals(attacker)) System.out.print(players.get(i).getName());
        }
        System.out.println("");
        String attackedPlayerName=attackedPlayerScanner.nextLine();
        Player attackedPlayer=null;
        for(int i=0;i<players.size();i++){
            if(players.get(i).getName().equals(attackedPlayerName)) attackedPlayer=players.get(i);
        }
        payDoubleRent(attackedPlayer);
        return true;
    }
}
