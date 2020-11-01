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
public class Earthquake extends Event {
    Random rand=new Random();
    
    public Earthquake(){
        this.setName("Earthquake");
    }
    
    public void collapse(Avenue avenue)            // gérer la notion de tour
    {
         if(avenue.getHouse() > 0)               // l'event ne concerne que les maisons
        {
            int magnitude = rand.nextInt(9)+1;
            if (magnitude == 9)
            {
                avenue.setHouse(0);                // toutes les maisons sont détruites
                System.out.println("Quelle catastrophe, un séisme de magnitude " + magnitude + " a frappé le territoire");
            }
            else if ((magnitude >= 4) && magnitude < 9)
            {
                avenue.setHouse(avenue.getHouse() > 1 ? avenue.getHouse() - 2 : 0);     // si on a plus d'une maison on peut retirer deux maisons sinon on en retire 1, il n'y a donc plus de maisons
                System.out.println("Quelle catastrophe, un séisme de magnitude " + magnitude + " a frappé le territoire");
            }
            else
            {
                avenue.setHouse(avenue.getHouse() - 1);
                System.out.println("Quelle catastrophe, un séisme de magnitude " + magnitude + " a frappé le territoire");
            }
        }
    }
    
}
