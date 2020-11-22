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
public final class Cannon extends Player implements Citizen {
    
    public Cannon(String name,int playerNumber){
        this.setName(name);
        this.setPlayerNumber(playerNumber);
    }
    
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
