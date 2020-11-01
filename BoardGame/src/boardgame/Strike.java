/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;
import java.util.Random;
/**
 *
 * @author Antoine
 */
public class Strike extends Attack {
    Random rand = new Random();
    
    public Strike(){
        this.setName("Strike");
    }
    
    public void closeRailRoad(Property property,Player attackedPlayer)              // ajouter la notion de tour
    {
        property.setAssociatedPlayer(attackedPlayer.getName());  //soit on associe le joueur attaqué ici ou on le gère dans board
        if(property instanceof RailRoad)
        {
            int strike =rand.nextInt(6);
            System.out.println("Quel dommage ! Il fait si beau aujourd'hui que la SNCF a décidé de faire grève pendant " + strike + " jours");
            property.setRent(0);
        }
    }
}
