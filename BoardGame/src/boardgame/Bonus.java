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
public class Bonus extends Case{
    private int amount;
    Random rand=new Random();
    public Bonus(String caseName, int numCase){
        this.setCaseNumber(numCase);
        this.setName(caseName);
    }
    
    public void effect(Player player,ArrayList <Case> board){
        int num_effect=rand.nextInt(4)+1;
        amount=rand.nextInt(200-50+1)+50;
        int newCase=rand.nextInt(39)+1;
        if(this.getName().equals("Chance")){
            switch(num_effect){
                case 1://Carte chance = joueur reçoit de l'argent
                    player.setCapital(player.getCapital()+amount);
                    System.out.println("Félicitations ! Vous trouvez "+amount+" € lors d'un banale promenade avec Medor");
                    break;
                case 2://Carte chance = joueur perd de l'argent
                    player.setCapital(player.getCapital()-amount);
                    System.out.println("Dommage, une racaille vous es tombée dessus et vous a volé "+amount+" €");
                    break;
                case 3://Carte chance = joueur est déplacé sur une autre case
                    while(newCase==player.getNumberCase()){
                        newCase=rand.nextInt(40);
                    }
                    if(newCase<player.getNumberCase()) player.setCapital(player.getCapital()+200); //Si le joueur passe par la case départ, il touche l'argent
                    player.setNumberCase(newCase);
                    System.out.println("Vous êtes à présent sur la case "+board.get(newCase).getName());
                    break;
                case 4://Carte chance = joueur est emmené en prison
                    System.out.println("Vous allez en prison");
                    player.inJail();
                    break;    
            }
            
            
        }
        else if(this.getName().equals("Caisse de communauté")){
            switch(num_effect){
                case 1://Carte caisse de communauté = joueur reçoit de l'argent
                    player.setCapital(player.getCapital()+amount);
                    System.out.println("Félicitations ! Votre hospitalité vous fait gagner "+amount+" € !");
                    break;
                case 2://Carte caisse de communauté = joueur perd de l'argent
                    player.setCapital(player.getCapital()-amount);
                    System.out.println("Dommage, votre manque de communication vous fait perdre "+amount+" € !");
                    break;
                case 3://Carte caisse de communauté = joueur est libéré de prison
                    System.out.println("Vous disposez d'une carte vous libérant de prison");
                    player.setFree_card(player.getFree_card()+1);
                    break;
                case 4://Carte caisse de communauté = joueur est emmené en prison
                    System.out.println("Vous allez en prison");
                    player.inJail();
                    break;    
            }
            
        }
    }
    
}
