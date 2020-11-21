/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgame;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Antoine
 */
public final class Earthquake extends Event {
    Random rand=new Random();
    
    public Earthquake(){
        this.setName("Earthquake");
    }
    private boolean done=false;

    public boolean isDone() {
        return done;
    }
    
    public void collapse(ArrayList <Player> players)            // gérer la notion de tour
    {
        boolean hasEffect=false;
        int magnitude = rand.nextInt(9)+1;
        for(int i=0;i<players.size();i++){
            if(!players.get(i).getAvenues().isEmpty()){
                for(int j=0;j<players.get(i).getAvenues().size();j++){
                    if(players.get(i).getAvenues().get(j).getHouse()>0){
                        
                        if (magnitude == 9)
                        {
                            players.get(i).getAvenues().get(j).setHouse(0);                // toutes les maisons sont détruites
                            hasEffect=true;
                        }
                        else if (magnitude >= 4 && magnitude < 9)
                        {
                            players.get(i).getAvenues().get(j).setHouse(players.get(i).getAvenues().get(j).getHouse() > 1 ? players.get(i).getAvenues().get(j).getHouse() - 2 : 0);     // si on a plus d'une maison on peut retirer deux maisons sinon on en retire 1, il n'y a donc plus de maisons
                            hasEffect=true;
                        }
                        else
                        {
                            players.get(i).getAvenues().get(j).setHouse(players.get(i).getAvenues().get(j).getHouse() - 1);
                            hasEffect=true;
                        }
                    }
                }
            }
        }
        if(!hasEffect) System.out.println("Il y a eu un séisme, mais heureusement il n'y avait aucune maison. Il n'a donc fait aucun dégât");
        else{
            if(magnitude==9) System.out.println("Quelle catastrophe, un séisme de magnitude " + magnitude + " a frappé le territoire");
            else if(magnitude >= 4 && magnitude < 9) System.out.println("Quelle catastrophe, un séisme de magnitude " + magnitude + " a frappé le territoire");
            else System.out.println("Quelle catastrophe, un séisme de magnitude " + magnitude + " a frappé le territoire");

        }
        this.done=true;
    }

    
}
