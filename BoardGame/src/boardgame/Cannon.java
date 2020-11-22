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
 * La classe Cannon correspond au pouvoir Cannon permettant de détruire la maison d'un autre joueur
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * On retrouve dans cette classe les fonctions permettant de détruire les maisons et de bénéficier du bonus de couleur d'avenue
 * @author thibb
 */
public final class Cannon extends Player implements Citizen {
    
    
    /**
     * <b> Constructeur de Cannon </b>
     * <br>
     * @param name
     *       Le paramètre indique le nom du joueur qui a choisi le pouvoir Cannon
     * @param playerNumber 
     *       Le paramètre indique le numéro du joueur qui a choisi le pouvoir Cannon
     */
    public Cannon(String name,int playerNumber){
        this.setName(name);
        this.setPlayerNumber(playerNumber);
    }
    
    
    /**
     * Cette méthode permet d'utiliser le pouvoir du pion cannon soit de détruire des maisons
     * @param avenues
     *      Le paramètre correspond à la liste des avenues du plateau de jeu
     */
    public void shoot(ArrayList <Avenue> avenues){
        Scanner avenue_scanner=new Scanner(System.in);
        String avenue_name;
        Avenue avenue=null;
        boolean isHouseDestroyed = false;
        System.out.println("Sur quelle avenue voulez-vous détruire une maison");
            for(int i=0;i<avenues.size();i++){
                System.out.println(avenues.get(i).getName());
            }
        do{
            avenue_name=avenue_scanner.nextLine();
            for(int i=0;i<avenues.size();i++){
                if(avenues.get(i).getName().equals(avenue_name)) avenue=(Avenue)(avenues.get(i));
            }
            if(avenue!=null){
                avenue.setHouse(avenue.getHouse()-1);
                avenue.computing(avenue, avenue.getAssociatedPlayer());
                System.out.println("Quel malheur ! Un joueur vient de détruire une maison sur " + avenue.getName());
                isHouseDestroyed = true;
            }
            else{
                System.out.println("Veuillez saisir un nom d'avenue correct");
            }
        }while(!isHouseDestroyed);
    }
    
    
    /**
     Cette méthode permet de doubler le loyer des avenues dont la couleur correspond au bonus du pouvoir
     * @param avenue
     *      Le paramètre permet de doubler le loyer de l'avenue si celle-ci est d'une couleur identique à celle du bonus
     */
    @Override
    public void doubleRent(Avenue avenue)
    { 
        if(avenue.getColor().equals(ColorAvenue.ROSE))                               // SI COULEUR CORRESPOND AU BONUS
        {                                                        
            avenue.setRent(avenue.getRent() * 2);                               // ON DOUBLE
        }
        else
        {
            avenue.setRent(avenue.getRent());                                   // ON NE CHANGE RIEN
        }
    }
    
    
}
