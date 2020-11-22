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

/**
 * La classe Hat correspond au pouvoir Hat permettant d'arnaquer les autres joueurs pour obtenir de l'argent
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * On retrouve dans cette classe les fonctions permettant d'arnaquer les joueurs et de bénéficier du bonus de couleur d'avenue
 * @author thibb
 */
public final class Hat extends Player implements Citizen {
    
    
    /**
    *  <b> Constructeur de Hat </b>
    * <br>
    * @param name
    *       Le paramètre indique le nom du joueur qui a choisi le pouvoir Hat
    * @param playerNumber 
    *       Le paramètre indique le numéro du joueur qui a choisi le pouvoir Hat
    */
    public Hat(String name,int playerNumber){
        this.setName(name);
        this.setPlayerNumber(playerNumber);
    }
    
    /**
    * Cette méthode permet d'arnaquer les joueurs en leur soutirant de l'argent
    * @param players 
    *      Le paramètre correspond à la liste des joueurs afin de pouvoir modifier leurs capitaux
    */    
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
    
    /**
    * Cette méthode permet de doubler le loyer des avenues dont la couleur correspond au bonus du pouvoir
    * @param avenue
    *      Le paramètre permet de doubler le loyer de l'avenue si celle-ci est d'une couleur identique à celle du bonus
    */
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
