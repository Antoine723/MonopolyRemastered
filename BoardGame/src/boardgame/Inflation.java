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
public class Inflation extends Attack {
    
    public Inflation(){
        this.setName("Inflation");
        this.setEffect("Vous pouvez imposer à un joueur de payer 2 fois plus cher le prochain terrain qu'il veut acheter");
        
    }
    
    public void payDoubleRent(Property property, Player attackedPlayer)
    {
           property.setBoughtPrice(property.getBoughtPrice() * 2); // on double le prix d'achat
           property.setAssociatedPlayer(attackedPlayer); // on associe le joueur attaqué à la propriété doublée
           this.setIsUsed(true);
    }
    
    @Override
    public void effect(){
        System.out.println("Inflation");
    }
}
