/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;
import java.util.Scanner;

/**
 *
 * @author Antoine
 */
public class GiveAway extends Attack {
    Scanner scanner = new Scanner(System.in);
    
    public GiveAway(String name, String effect, String associatedPlayer){
        this.setName(name);
        this.setEffect(effect);
        this.setAssociatedPlayer(associatedPlayer);
        
    }
    
    public void sellAvenue(Avenue avenue ,Player attackedPlayer) // le choix du joueur attaqué et le terrain choisi sera géré via boardgame
    {
            avenue.setHotel(0);                                                                     // on supprime maisons et hotels
            avenue.setHouse(0);
            avenue.setRent(avenue.computeRent());                                                    // on retourne le loyer de base gérer par la fonction computeRent
            attackedPlayer.setCapital(attackedPlayer.getCapital() + avenue.getBoughtPrice()/2);     // On modifie le capital et on ajoute le prix d'achat + prix maisons et/ou hotels à définir
            attackedPlayer.setNumberOfAvenues(attackedPlayer.getNumberOfAvenues() - 1);             // on retire au joueur attaqué sa propriété
    }
    
}