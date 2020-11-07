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
    
    public GiveAway(){
        this.setName("GiveAway");
        this.setEffect("Vous pouvez forcer un joueur à vendre son terrain");   
    }
    
    public void sellAvenue(Avenue avenue ,Player attackedPlayer) // le choix du joueur attaqué et le terrain choisi sera géré via boardgame
    {
            attackedPlayer.setCapital(attackedPlayer.getCapital() + avenue.getBoughtPrice()/2 + avenue.getPriceOfHouseAndHotels()* (int) avenue.getSoldAvenueCoeff());     // On modifie le capital et on ajoute le prix d'achat + prix maisons et/ou hotels à définir
            attackedPlayer.setNumberOfAvenues(attackedPlayer.getNumberOfAvenues() - 1);             // on retire au joueur attaqué sa propriété
            avenue.setHotel(0);                                                                     // on supprime maisons et hotels
            avenue.setHouse(0);                      // nb maisons/hotel à predndre en compte
            this.setIsUsed(true);
    }
    
}
