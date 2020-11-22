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
 * Cette classe correspond à la carte attaque GiveAway permettant de faire faire vendre la propriété d'un joueur
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * <br>
 * On retrouve dans cette classe les fonctions permettant de faire faire vendre une avenue et d'utiliser la crte attaque GiveAway
 * @author thibb
 */
public final class GiveAway extends Attack {
    
    /**
     * <b> Constructeur de GiveAway </b>
     * <br>
     * On attribut un nom à la carte attaque et on indique son effet à l'utilisateur
     *
     */
    public GiveAway(){
        this.setName("GiveAway");
        this.setEffect("Vous pouvez forcer un joueur à vendre son terrain");   
    }
    
    /**
     *Cette méthode permet de vendre une avenue
     * @param avenue
     *        Le paramètre correspond à l'avenue qui doit être vendue
     * @param attackedPlayer
     *        Le paramètre correspond au joueur qui subit l'effet de la carte attaque
     */
    public void sellAvenue(Avenue avenue ,Player attackedPlayer)
    {
            attackedPlayer.setCapital(attackedPlayer.getCapital() + avenue.getBoughtPrice()/2 + avenue.getPriceOfHouseAndHotels()* (int) avenue.getSoldAvenueCoeff());     // On modifie le capital et on ajoute le prix d'achat + prix maisons et/ou hotels à définir
            attackedPlayer.removeAvenue(avenue);             // on retire au joueur attaqué sa propriété
            attackedPlayer.removeProperty(avenue);
            avenue.setHotel(0);                                                                     // on supprime maisons et hotels
            avenue.setHouse(0);                      // nb maisons/hotel à prendre en compte
            avenue.setAssociatedPlayer(null);
            avenue.setIsBought(false);
            this.setIsUsed(true);
    }
    
    /**
     * Cette méthode permet de gérer l'interaction avec l'utilisateur lorsque celui-ci utilise la carte attaque GiveAway
     * @param players
     *      Le paramètre correspond à une liste de joueurs pour ainsi accéder à l'ensemble de leurs propriétés
     * @param attacker
     *      Le paramètre correspond au joueur initiateur de l'attaque
     * @param board
     *      Le paramètre correspond au plateau de jeu pour pouvoir accéder aux différentes cases
     * @return
     *      La méthode retourne vrai si l'opération s'est bien passée sinon elle retourne faux dans le cas où l'attaquant s'est trompé dans l'utilisation de la carte
     */
    @Override
    public boolean effect(ArrayList <Player> players, Player attacker,ArrayList <Case> board){
        Scanner attackedPlayerScanner=new Scanner(System.in);
        Scanner avenueScanner=new Scanner(System.in);
        System.out.println("Quel joueur voulez-vous attaquer ?");
        for(int i=0;i<players.size();i++){
            if(!players.get(i).equals(attacker)) System.out.print(players.get(i).getName());
        }
        System.out.println("");
        String attackedPlayerName=attackedPlayerScanner.nextLine();
        Player attackedPlayer=null;
        for(int i=0;i<players.size();i++){
            if(players.get(i).getName().equals(attackedPlayerName)) attackedPlayer=players.get(i);
        }
        if(attackedPlayer.getAvenues().isEmpty()){
            System.out.println("Ce joueur n'a pas d'avenue");
            return false;
        }
        else{
            System.out.println("Quelle avenue voulez-vous que "+attackedPlayerName+ " vende ?");
            for(int i=0;i<attackedPlayer.getAvenues().size();i++){
                System.out.println(attackedPlayer.getAvenues().get(i).getName());
            }
            String chosenAvenueName=avenueScanner.nextLine();
            Avenue chosenAvenue=null;
            for(int i=0;i<attackedPlayer.getAvenues().size();i++){
                if(chosenAvenueName.equals(attackedPlayer.getAvenues().get(i).getName())) chosenAvenue=attackedPlayer.getAvenues().get(i);
            }

            sellAvenue(chosenAvenue,attackedPlayer);
            return true;
        }
        
    }
}
