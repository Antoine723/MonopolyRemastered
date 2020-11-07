/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;
import java.util.Random;

/**
 *
 * @author thibb
 */
public class Robber extends Attack
{
    Random rand = new Random();
    
    public Robber()
    {
        this.setName("Robber");
        this.setEffect("Vous pouvez voler une rue d'un autre joueur");   
    }
    
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
                        attackedPlayer.setNumberOfAvenues(attackedPlayer.getNumberOfAvenues() - 1);
                        robber.setNumberOfAvenues(robber.getNumberOfAvenues() + 1);
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
}
