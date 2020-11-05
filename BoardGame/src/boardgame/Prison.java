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
public class Prison extends Case {
    
    private String playerName=null;
    Scanner choice = new Scanner(System.in);

    public Prison(String name, int caseNumber){
        this.setName(name);
        this.setCaseNumber(caseNumber);
        
    }
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
     public void getOutOfJail(Player player)
    {
        while (player.isIsInJail())                             // tant que le joueur est en prison
        {
            switch(choice.nextLine())                           // on lit la réponse du joueur
            {
                case "freecard":                
                    
                    if(player.getFree_card() > 0)               // si le joueur posséde une carte, il sort de prison
                    {
                        player.setIsInJail(false);
                        player.setFree_card(player.getFree_card() - 1);     // on actualise le nombre de cartes
                        System.out.println("Il vous reste à présent " + player.getFree_card() + " cartes pour sortir de prison");
                    }
                    break;
                    
                case "pay":
                    
                    if(player.getCapital() >= 50)                           // si le joueur souhaite payer la caution
                    {
                        player.setIsInJail(false);
                        player.setCapital(player.getCapital() - 50);        // on actualise le capital
                        System.out.println("Vous disposez à présent de " + player.getCapital() + " euros");
                    }
                    break;
                    
                case "roll":
                    if (player.rollsDice() % 2 == 0)            // si le joueur a fait un double
                    {
                        player.setIsInJail(false);
                        System.out.println("Félicitation ! Votre double vous permet de sortir de prison");
                    }
                    else
                    {
                        System.out.println("Dommage ! Vous restez en prison");
                        player.setIsInJail(true);
                    }
                    break;
            }
        }
    }
    
}
