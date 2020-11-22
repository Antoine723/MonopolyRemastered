/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author thibb
 */

/**
 * La classe Robber correspond à la carte attaque robber qui permet de voler l'avenue d'un joueur
 * <br>
 * <br>
 * Cette classe est caractérisée par les informations suivantes :
 * <br>
 * Le random rand qui permet de génerer un entier aléatoire compris entre 0 et 1 pour que la carte Robber fonctionne une fois sur deux
 * <br>
 * On retrouve les fonctions permettant de déclencher l'effet de la carte et celle permettant d'utiliser cette carte
 * @author thibb
 */
public final class Robber extends Attack
{
    Random rand = new Random();
    
    
    /**
     * <b> Constructeur de Robber </b>
     * <br>
     * On attribut un nom à la carte attaque et on indique son effet à l'utilisateur
     */
    public Robber()
    {
        this.setName("Robber");
        this.setEffect("Vous pouvez voler une rue d'un autre joueur");   
    }
    
    /**
     * Cette méthode permet de voler l'avenue d'un autre joueur
     * @param avenue
     *      Le paramètre correspond à l'avenue que le voleur souhaite voler
     * @param attackedPlayer
     *      Le paramètre correspond au joueur attaqué
     * @param robber
     *      Le paramètre correspond au joueur qui lance l'attaque
     */
    public void stealAvenue(Avenue avenue, Player attackedPlayer,Player robber)
    {
        boolean correct = false;
        while(!correct)
            if(avenue.getAssociatedPlayer().equals(attackedPlayer))          // on vérifie que le terrain sélectionné appartient bien au joueur attaqué
            {
                switch(rand.nextInt(2))                                         // le voleur a une chance sur deux de réussir
                {
                    case 1:
                        System.out.println("Quel fieffé voleur ! Vous vous êtes emparé du terrain de votre cible");
                        attackedPlayer.getAvenues().remove(avenue);
                        robber.getAvenues().add(avenue);
                        avenue.setAssociatedPlayer(robber);
                        correct = true;
                        break;

                    case 0:
                        System.out.println("Vous n'avez pas réussi à voler le terrain et vous avez été pris sur le fait.");
                        System.out.println("Vous allez en prison !");
                        robber.setIsInJail(true);
                        correct =true;
                        break;      
                }
            }
            else
            {
                System.out.println("Le terrain choisi n'appartient pas à " + attackedPlayer.getName());
                System.out.println("Veuillez sélectionner un autre terrain");
            }
        
            
    }
    
    
    /**
     * Cette méthode permet de gérer l'interaction avec l'utilisateur lorsque celui-ci utilise la carte attaque Robber
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
        Scanner attacked_player_scanner=new Scanner(System.in);
        Scanner avenue_scanner=new Scanner(System.in);
        System.out.println("Quel joueur voulez-vous attaquer ?");
        for(int i=0;i<players.size();i++){
            if(!players.get(i).equals(attacker))System.out.print(players.get(i).getName());
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
            System.out.println("Quelle avenue voulez-vous voler ? ");
            for(int i=0;i<attackedPlayer.getProperties().size();i++){
                if(attackedPlayer.getProperties().get(i) instanceof Avenue) System.out.println(attackedPlayer.getProperties().get(i).getName());
            }
            String chosen_avenue_name=avenue_scanner.nextLine();
            Avenue chosen_avenue=null;
            for(int i=0;i<attackedPlayer.getProperties().size();i++){
                if(chosen_avenue_name.equals(attackedPlayer.getProperties().get(i).getName())) chosen_avenue=(Avenue)(attackedPlayer.getProperties().get(i));
            }

            stealAvenue(chosen_avenue,attackedPlayer,attacker);
            return true;
        }
        
    }
}
