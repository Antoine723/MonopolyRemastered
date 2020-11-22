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
 * La classe Bonus permet de différencier les cases bonus des autres
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * L'entier amount qui correspond au montant que le joueur peut gagner ou perdre
 * <br>
 * <br>
 * Le random rand pour générer un effet aléatoire parmi les effets possibles des cartes
 * <br>
 * On retrouve la fonction permettant de produire les différents effets des cartes chances et caisse de communauté
 * @author thibb
 */
public final class Bonus extends Case{
    private int amount;
    Random rand=new Random();
    
    /**
     * <b> Constructeur de Bonus </b>
     * <br>
     * On attribut un nom à la case ainsi qu'un numéro de case
     * @param caseName
     *      Le paramètre correspond au nom de la case
     * @param numCase 
     *      Le paramètre correspond au numéro de la case
     */
    public Bonus(String caseName, int numCase){
        this.setCaseNumber(numCase);
        this.setName(caseName);
    }
    
    
    /**
     * Cette méthode permet de produire les différents effets des cartes chance et caisse de communauté
     * @param player
     *      Le paramètre correspond à la liste des joueurs
     * @param board
     *      Le paramètre correspond au plateau de jeu
     */
    public void effect(Player player,ArrayList <Case> board){
        int numEffect=rand.nextInt(4)+1;
        amount=rand.nextInt(200-50+1)+50;
        Case newCase;
        if(this.getName().equals("Chance")){
            switch(numEffect){
                case 1://Carte chance = joueur reçoit de l'argent
                    player.setCapital(player.getCapital()+amount);
                    System.out.println("Félicitations ! Vous trouvez "+amount+" € lors d'une banale promenade avec Medor");
                    break;
                case 2://Carte chance = joueur perd de l'argent
                    player.setCapital(player.getCapital()-amount);
                    System.out.println("Dommage, une racaille vous est tombée dessus et vous a volé "+amount+" €");
                    break;
                case 3://Carte chance = joueur est déplacé sur une autre case
                    do{
                        newCase=board.get(rand.nextInt(board.size()));
                    }while(newCase==player.getPlayerCase());
                    
                    if(newCase.getCaseNumber()<player.getPlayerCase().getCaseNumber()) player.setCapital(player.getCapital()+200); //Si le joueur passe par la case départ, il touche l'argent
                    player.setPlayerCase(newCase);
                    System.out.println("Vous avez été déplacé, vous êtes à présent sur la case "+player.getPlayerCase().getName());
                    break;
                case 4://Carte chance = joueur est emmené en prison
                    System.out.println("Vous allez en prison");
                    player.inJail(board);
                    break;    
            }
            
            
        }
        else if(this.getName().equals("Caisse de communauté")){
            switch(numEffect){
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
                    player.setFreeCard(player.getFreeCard()+1);
                    break;
                case 4://Carte caisse de communauté = joueur est emmené en prison
                    System.out.println("Vous allez en prison");
                    player.inJail(board);
                    break;    
            }
            
        }
    }
}
