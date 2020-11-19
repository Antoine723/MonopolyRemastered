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
    
    public void shoot(Avenue avenue){ //A voir pour caler dans la méthode la boucle for avec le choix du joueur+propriété visée
        boolean isHouseDestroyed = false;
        while(isHouseDestroyed == false)
        {
            if(avenue.getHouse()>0)
            {
                avenue.setHouse(avenue.getHouse()-1);
                System.out.println("Quel malheur ! Un joueur vient de détruire une maison sur " + avenue.getName());
                isHouseDestroyed = true;
            }
            else 
            {
                System.out.println("Veuillez sélectionner une propriété qui dispose d'au moins 1 maison");
                isHouseDestroyed = false;
            }
        }
    }
    
    @Override
    public void doubleRent(Avenue avenue)
    { 
        if(avenue.getColor().ROSE.equals(avenue))                               // SI COULEUR CORRESPOND AU BONUS
        {                                                        
            avenue.setRent(avenue.getRent() * 2);                               // ON DOUBLE
        }
        else
        {
            avenue.setRent(avenue.getRent());                                   // ON NE CHANGE RIEN
        }
    }
    
    
}
