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
public class GiveAway extends Attack {
    Scanner scanner = new Scanner(System.in);
    
    public GiveAway(){
        this.setName("GiveAway");
        this.setEffect("Vous pouvez forcer un joueur à vendre son terrain");   
    }
    
    public void sellAvenue(Avenue avenue ,Player attackedPlayer)
    {
            attackedPlayer.setCapital(attackedPlayer.getCapital() + avenue.getBoughtPrice()/2 + avenue.getPriceOfHouseAndHotels()* (int) avenue.getSoldAvenueCoeff());     // On modifie le capital et on ajoute le prix d'achat + prix maisons et/ou hotels à définir
            attackedPlayer.getAvenues().remove(avenue);             // on retire au joueur attaqué sa propriété
            avenue.setHotel(0);                                                                     // on supprime maisons et hotels
            avenue.setHouse(0);                      // nb maisons/hotel à predndre en compte
            avenue.setAssociatedPlayer(null);
            avenue.setIsBought(false);
            this.setIsUsed(true);
    }
    
    @Override
    public boolean effect(ArrayList <Player> players, Player attacker,ArrayList <Case> board){
        Scanner attacked_player_scanner=new Scanner(System.in);
        Scanner avenue_scanner=new Scanner(System.in);
        System.out.println("Quel joueur voulez-vous attaquer ?");
        for(int i=0;i<players.size();i++){
            if(!players.get(i).equals(attacker)) System.out.print(players.get(i).getName());
        }
        System.out.println("");
        String attackedPlayerName=attacked_player_scanner.nextLine();
        Player attackedPlayer=null;
        for(int i=0;i<players.size();i++){
            if(players.get(i).getName().equals(attackedPlayerName)) attackedPlayer=players.get(i);
        }
        if(attackedPlayer.getProperties().isEmpty()){
            System.out.println("Ce joueur n'a pas de propriétés");
            return false;
        }
        else{
            System.out.println("Quelle avenue voulez-vous que "+attackedPlayerName+ " vende ?");
            for(int i=0;i<attackedPlayer.getProperties().size();i++){
                if(attackedPlayer.getProperties().get(i) instanceof Avenue) System.out.println(attackedPlayer.getProperties().get(i).getName());
            }
            String chosen_avenue_name=avenue_scanner.nextLine();
            Avenue chosen_avenue=null;
            for(int i=0;i<attackedPlayer.getProperties().size();i++){
                if(chosen_avenue_name.equals(attackedPlayer.getProperties().get(i).getName())) chosen_avenue=(Avenue)(attackedPlayer.getProperties().get(i));
            }

            sellAvenue(chosen_avenue,attackedPlayer);
            return true;
        }
        
    }
}
