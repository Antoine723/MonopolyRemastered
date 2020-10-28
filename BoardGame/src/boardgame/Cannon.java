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
public class Cannon extends Player implements Citizen {
    
    public Cannon(String name,int playerNumber){
        this.setName(name);
        this.setPlayerNumber(playerNumber);
    }
    
    public int shoot(Avenue avenue){ //A voir pour caler dans la méthode la boucle for avec le choix du joueur+propriété visée
        if(avenue.getHouse()>0){
            avenue.setHouse(avenue.getHouse()-1);
            return 1;
        }
        else {
            System.out.println("Veuillez sélectionner une propriété qui dispose d'au moins 1 maison");
            return -1;
        }
    }
    
    public void doubleRent(){
        
    }
    
    
}
